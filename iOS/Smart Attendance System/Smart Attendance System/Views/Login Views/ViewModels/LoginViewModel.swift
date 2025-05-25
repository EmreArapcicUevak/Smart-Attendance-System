//
//  LoginViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 20. 5. 2025..
//

import Foundation
import SwiftUI
import JWTDecode

@Observable
class LoginViewModel {
    var isLoading: Bool = false
    
    var errorAndNotficationController = ErrorAndNotificationSimpleParamModel()
    
    var userModel: UserModel = .init(email: "profvedad@mail.ba", password: "daddy")
    var path : NavigationPath = NavigationPath()
    
    func checkValidEmail(_ email: String) -> Bool {
        let emailRegex = #"^[A-Za-z0-9._%+-]+@(([A-Za-z0-9.-]+\.[A-Za-z]{2,})|(\[[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}\]))$"#
        let predicate = NSPredicate(format: "SELF MATCHES %@", emailRegex)
        guard predicate.evaluate(with: email) else {
            return false
        }
        
        let parts = email.split(separator: "@", maxSplits: 1)
        guard parts.count == 2 else { return false }
        
        let localPart = parts[0]
        let hostPart = parts[1]

        let invalidSymbolPredicate = NSPredicate(
            format: "SELF MATCHES %@",
            #".*((^(\.|-|\+|_))|(\.$)|(\.{2,})).*"#
        )
        
        guard invalidSymbolPredicate.evaluate(with: localPart) == false && invalidSymbolPredicate.evaluate(with: hostPart) == false else {
            return false
        }
        
        if hostPart.contains("[") {
            let ipNumbers = hostPart.split(separator: "[").last!.split(separator: "]").first!.split(separator: ".")
            
            for ipNumber in ipNumbers {
                guard let ipValue = Int(ipNumber), ipValue >= 0 && ipValue <= 255 else {
                    return false
                }
            }
        }
        
        return true
    }
    
    func checkValidPassword(_ password: String) -> Bool {
        return password.count >= 8
    }
    
    func getAuthToken() async throws -> String {
        let endpoint = "\(Constants.API_URL)/auth/login"
        guard let url = URL(string: endpoint) else {
            self.errorAndNotficationController.displayErrorMessage("Invalid URL \"\(endpoint)\"")
            throw AuthError.invalidURL
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST" // We want to use the POST method
        request.setValue("application/json", forHTTPHeaderField: "Content-Type") // In the HTTP header alert the server that we are sending JSON data
        
        let body = AuthRequestModel(email: self.userModel.email, password: self.userModel.password)
        let bodyData = try JSONEncoder().encode(body)
        
        let (data, response) = try await URLSession.shared.upload(for: request, from: bodyData)
        guard let response = response as? HTTPURLResponse else {
            self.errorAndNotficationController.displayErrorMessage("Invalid response code")
            throw AuthError.invalidResponse
        }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 401 {
                throw AuthError.invalidEmailOrPassword
            }
            
            self.errorAndNotficationController.displayErrorMessage("Invalid status code: \(response.statusCode)")
            throw AuthError.invalidResponse
        }
        
        return try JSONDecoder().decode(AuthResponseModel.self, from: data).accessToken
    }
    
    func login() async {
        guard checkValidEmail(self.userModel.email) else {
            self.errorAndNotficationController.displayErrorMessage("Invalid Email Format")
            return
        }
        
        
        self.isLoading = true
        
        do {
            let accessToken = try await getAuthToken()
            let jwt = try decode(jwt: accessToken)
            
            guard let issuesAt = jwt["iat"].integer else { throw jwtError.invalidToken }
            guard let expiresAt = jwt["exp"].integer else { throw jwtError.invalidToken }
            guard let role = jwt["role"].string else { throw jwtError.invalidToken }
            
            let idenModel = IdentityModel(
                JWT: accessToken,
                expiresAt: expiresAt,
                issuesAt: issuesAt,
                role: role
            )
            
            SessionExpirationManager.shared.identityModel = idenModel
            self.path.append(idenModel)
        } catch jwtError.invalidToken {
            self.errorAndNotficationController.displayErrorMessage("Failed whilst decoding the JWT token")
        } catch AuthError.invalidEmailOrPassword {
            self.errorAndNotficationController.displayErrorMessage("Invalid Email or Password")
        } catch {
            self.errorAndNotficationController.displayErrorMessage("Failed to login (\(error.localizedDescription))\nPlease try again later")
            print("Some other error occurred: \(error.localizedDescription)")
        }
        
        self.isLoading = false
        
    }
}
