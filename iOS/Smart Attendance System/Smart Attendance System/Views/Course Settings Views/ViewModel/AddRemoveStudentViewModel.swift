//
//  AddRemoveStudentViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import Foundation
import SwiftUI

@Observable
class AddRemoveStudentViewModel {
    var students : [StudentModel] = []
    let courseId : Int
    var studentId : String = ""
    
    var errorAndNotificationController = ErrorAndNotificationSimpleParamModel()
    var isLoading : Bool = true
    
    init(courseId: Int) { self.courseId = courseId }
    
    
    func sendDisenrollRequest(student: StudentModel) async throws {
        let endpoint = "\(Constants.API_URL)/course/\(courseId.description)/withdraw"
        guard let url = URL(string: endpoint) else { throw loadingErrors.invalidURL }
        guard let JWT = SessionExpirationManager.shared.identityModel?.JWT else { throw loadingErrors.invalidToken}
        
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST" // We want to use the POST method
        request.setValue("Bearer \(JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON
        request.setValue("application/json", forHTTPHeaderField: "Content-Type") // In the HTTP header alert the server that we are sending JSON data
        
        let bodyData = try JSONEncoder().encode(EnrollRequest(studentId: student.id))
        
        let (data, response) = try await URLSession.shared.upload(for: request, from: bodyData)
        guard let response = response as? HTTPURLResponse else { throw loadingErrors.invalidURL }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 406 {
                throw loadingErrors.invalidData
            } else {
                throw loadingErrors.invalidToken
            }
        }
        
        students.removeAll { $0 == student }
    }
    
    func removeStudent(student : StudentModel) {
        Task {
            self.isLoading = true
            
            do {
                try await sendDisenrollRequest(student: student)
            } catch(loadingErrors.invalidToken) {
                SessionExpirationManager.shared.errorAndNotificationController?.displayErrorMessage("You token isn't valid anymore.")
                SessionExpirationManager.shared.popToRoot()
            } catch(loadingErrors.invalidURL) {
                self.errorAndNotificationController.displayErrorMessage("Invalid URL endpoint.")
            } catch(loadingErrors.noServerResponse) {
                self.errorAndNotificationController.displayErrorMessage("Couldn't get response from the server.")
            } catch(loadingErrors.invalidData) {
                self.errorAndNotificationController.displayPopUpMessage("No student with this ID was found.")
            } catch {
                self.errorAndNotificationController.displayErrorMessage(error.localizedDescription)
            }
            
            
            self.isLoading = false
        }
    }
    
    func sendEnrollRequest() async throws {
        let endpoint = "\(Constants.API_URL)/course/\(courseId.description)/enroll"
        guard let url = URL(string: endpoint) else { throw loadingErrors.invalidURL }
        guard let JWT = SessionExpirationManager.shared.identityModel?.JWT else { throw loadingErrors.invalidToken}
        
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST" // We want to use the POST method
        request.setValue("Bearer \(JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON
        request.setValue("application/json", forHTTPHeaderField: "Content-Type") // In the HTTP header alert the server that we are sending JSON data
        
        let bodyData = try JSONEncoder().encode(EnrollRequest(studentId: Int(studentId) ?? -1))
        
        let (data, response) = try await URLSession.shared.upload(for: request, from: bodyData)
        guard let response = response as? HTTPURLResponse else { throw loadingErrors.invalidURL }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 406 {
                throw loadingErrors.invalidData
            } else {
                throw loadingErrors.invalidToken
            }
        }
        
        await loadView()
    }
    
    func enrollStudent() {
        Task {
            guard studentId.isEmpty else { return }
            self.isLoading = true
            
            do {
                try await sendEnrollRequest()
            } catch(loadingErrors.invalidToken) {
                SessionExpirationManager.shared.errorAndNotificationController?.displayErrorMessage("You token isn't valid anymore.")
                SessionExpirationManager.shared.popToRoot()
            } catch(loadingErrors.invalidURL) {
                self.errorAndNotificationController.displayErrorMessage("Invalid URL endpoint.")
            } catch(loadingErrors.noServerResponse) {
                self.errorAndNotificationController.displayErrorMessage("Couldn't get response from the server.")
            } catch(loadingErrors.invalidData) {
                self.errorAndNotificationController.displayPopUpMessage("No student with this ID was found.")
            } catch {
                self.errorAndNotificationController.displayErrorMessage(error.localizedDescription)
            }
            
            
            self.isLoading = false
        }
    }
    
    func loadStudents() async throws {
        let endpoint = "\(Constants.API_URL)/course/\(courseId.description)/students"
        guard let url = URL(string: endpoint) else { throw loadingErrors.invalidURL }
        guard let JWT = SessionExpirationManager.shared.identityModel?.JWT else { throw loadingErrors.invalidToken}
        
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET" // We want to use the POST method
        request.setValue("Bearer \(JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON
        //request.setValue("application/json", forHTTPHeaderField: "Content-Type") // In the HTTP header alert the server that we are sending JSON data
        
        let (data, response) = try await URLSession.shared.data(for: request)
        guard let response = response as? HTTPURLResponse else { throw loadingErrors.invalidURL }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 404 {
                throw loadingErrors.noData
            } else {
                throw loadingErrors.invalidToken
            }
        }
        
        self.students = try JSONDecoder().decode(StudentListResponse.self, from: data).students
    }
    
    func loadView() async {
        guard students.isEmpty else { return }
        
        self.isLoading = true
        
        do {
            try await loadStudents()
        } catch(loadingErrors.invalidToken) {
            SessionExpirationManager.shared.errorAndNotificationController?.displayErrorMessage("You token isn't valid anymore.")
            SessionExpirationManager.shared.popToRoot()
        } catch(loadingErrors.invalidURL) {
            self.errorAndNotificationController.displayErrorMessage("Invalid URL endpoint.")
        } catch(loadingErrors.noServerResponse) {
            self.errorAndNotificationController.displayErrorMessage("Couldn't get response from the server.")
        } catch(loadingErrors.noData) {
            self.errorAndNotificationController.displayPopUpMessage("There are no students in this course.")
        } catch {
            self.errorAndNotificationController.displayErrorMessage(error.localizedDescription)
        }
        
        
        self.isLoading = false
    }
}
