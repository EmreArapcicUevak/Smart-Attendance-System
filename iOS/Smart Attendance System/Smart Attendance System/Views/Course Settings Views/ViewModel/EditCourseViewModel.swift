//
//  EditCourseViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import Foundation
import SwiftUI

@Observable
class EditCourseViewModel {
    var courseToEdit : CourseModel
    var errorAndNotificationController = ErrorAndNotificationSimpleParamModel()

    init(courseToEdit : CourseModel) { self.courseToEdit = courseToEdit }
    
    var canEditCourse : Bool = false
    func checkIfCanEditCourse() { canEditCourse = !courseToEdit.courseName.isEmpty && !courseToEdit.courseFaculty.isEmpty && !courseToEdit.courseCode.isEmpty }
    
    var isLoading : Bool = false
    

    func sendRequestToDeleteCourse() async throws {
        let endpoint = "\(Constants.API_URL)/course/\(self.courseToEdit.id)"
        guard let url = URL(string: endpoint) else { throw loadingErrors.invalidURL }
        guard let JWT = SessionExpirationManager.shared.identityModel?.JWT else { throw loadingErrors.invalidToken}
        
        
        var request = URLRequest(url: url)
        request.httpMethod = "DELETE" // We want to use the POST method
        request.setValue("Bearer \(JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON
        request.setValue("application/json", forHTTPHeaderField: "Content-Type") // In the HTTP header alert the server that we are sending JSON data
        
        let (_, response) = try await URLSession.shared.data(for: request)
        guard let response = response as? HTTPURLResponse else { throw loadingErrors.invalidURL }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 404 {
                throw loadingErrors.noData
            } else {
                throw loadingErrors.invalidToken
            }
        }
    }
    
    func deleteCourse() {
        Task {
            self.isLoading = true
            
            do {
                try await sendRequestToDeleteCourse()
                SessionExpirationManager.shared.path?.wrappedValue.removeLast()
            } catch(loadingErrors.invalidToken) {
                SessionExpirationManager.shared.errorAndNotificationController?.displayErrorMessage("You token isn't valid anymore.")
                SessionExpirationManager.shared.popToRoot()
            } catch(loadingErrors.invalidURL) {
                self.errorAndNotificationController.displayErrorMessage("Invalid URL endpoint.")
            } catch(loadingErrors.noServerResponse) {
                self.errorAndNotificationController.displayErrorMessage("Couldn't get response from the server.")
            } catch(loadingErrors.noData) {
                self.errorAndNotificationController.displayErrorMessage("The course doesn't exist or is already deleted.")
            } catch {
                self.errorAndNotificationController.displayErrorMessage(error.localizedDescription)
            }
            
            self.isLoading = false
        }
    }
    
    func sendRequestToEditCourse() async throws {
        let endpoint = "\(Constants.API_URL)/course/"
        guard let url = URL(string: endpoint) else { throw loadingErrors.invalidURL }
        guard let JWT = SessionExpirationManager.shared.identityModel?.JWT else { throw loadingErrors.invalidToken}
        
        
        var request = URLRequest(url: url)
        request.httpMethod = "PUT" // We want to use the POST method
        request.setValue("Bearer \(JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON
        request.setValue("application/json", forHTTPHeaderField: "Content-Type") // In the HTTP header alert the server that we are sending JSON data
        
        let bodyData = try JSONEncoder().encode(self.courseToEdit)
        
        let (_, response) = try await URLSession.shared.upload(for: request, from: bodyData)
        guard let response = response as? HTTPURLResponse else { throw loadingErrors.invalidURL }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 406 {
                throw loadingErrors.invalidData
            } else {
                throw loadingErrors.invalidToken
            }
        }
    }
    func editCourse() {
        guard canEditCourse else {
            self.errorAndNotificationController.displayPopUpMessage("Please make sure all the fields are filled.")
            return
        }
        
        Task {
            self.isLoading = true
            
            do {
                try await sendRequestToEditCourse()
                self.errorAndNotificationController.displayPopUpMessage("You have successfully edited the course feel.")
            } catch(loadingErrors.invalidToken) {
                SessionExpirationManager.shared.errorAndNotificationController?.displayErrorMessage("You token isn't valid anymore.")
                SessionExpirationManager.shared.popToRoot()
            } catch(loadingErrors.invalidURL) {
                self.errorAndNotificationController.displayErrorMessage("Invalid URL endpoint.")
            } catch(loadingErrors.noServerResponse) {
                self.errorAndNotificationController.displayErrorMessage("Couldn't get response from the server.")
            } catch(loadingErrors.invalidData) {
                self.errorAndNotificationController.displayErrorMessage("Invalid data sent to the server")
            } catch {
                self.errorAndNotificationController.displayErrorMessage(error.localizedDescription)
            }
            
            self.isLoading = false
        }
    }
}
