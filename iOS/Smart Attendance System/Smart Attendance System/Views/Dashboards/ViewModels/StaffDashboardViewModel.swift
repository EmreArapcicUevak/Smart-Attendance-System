//
//  CourseCardViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 23. 5. 2025..
//

import SwiftUI

@Observable
class StaffDashboardViewModel {
    var courses : [CourseModel] = []
    var isLoading : Bool = true
    var errorAndNotificationController = ErrorAndNotificationSimpleParamModel()
    
    func fetchCourses() async throws {
        let endpoint = "\(Constants.API_URL)/course/staff-courses"
        guard let url = URL(string: endpoint) else {
            throw loadingErrors.invalidURL
        }
        
        guard let identityModel = SessionExpirationManager.shared.identityModel else {
            throw loadingErrors.invalidToken
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET" // We want to use the GET method
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON
        request.setValue("Bearer \(identityModel.JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        
        let (data, response) = try await URLSession.shared.data(for: request)
        guard let response = response as? HTTPURLResponse else {
            throw loadingErrors.noServerResponse
        }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 404 {
                throw loadingErrors.noData
            } else {
                throw loadingErrors.invalidToken
            }
        }
        
        self.courses = try JSONDecoder().decode(AttendanceResponse.self, from: data).courses
    }
    
    func loadView() async {
        self.isLoading = true
        
        do {
            try await fetchCourses()
        } catch(loadingErrors.invalidToken) {
            SessionExpirationManager.shared.errorAndNotificationController?.displayErrorMessage("You token isn't valid anymore.")
            SessionExpirationManager.shared.popToRoot()
        } catch(loadingErrors.invalidURL) {
            self.errorAndNotificationController.displayErrorMessage("Invalid URL endpoint.")
        } catch(loadingErrors.noServerResponse) {
            self.errorAndNotificationController.displayErrorMessage("Couldn't get response from the server.")
        } catch(loadingErrors.noData) {
            self.errorAndNotificationController.displayPopUpMessage("There are currently no courses on this account.")
        } catch {
            self.errorAndNotificationController.displayErrorMessage(error.localizedDescription)
        }
        
        self.isLoading = false
    }
}
