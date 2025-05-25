//
//  CreateCourseController.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import Foundation

@Observable
class CreateCourseViewModel {
    var courseToCreate : CourseModel = .init(courseName: "", courseFaculty: "", courseCode: "", hasTutorial: false, hasLab: false, courseId: -1)
    var errorAndNotificationController = ErrorAndNotificationSimpleParamModel()
    
    var canCreateCourse : Bool = false
    var isLoading : Bool = false
    
    func checkIfCanCreateCourse() { canCreateCourse = !courseToCreate.courseName.isEmpty && !courseToCreate.courseFaculty.isEmpty && !courseToCreate.courseCode.isEmpty }
    
    func sendRequestToCreateCourse() async throws {
        let endpoint = "\(Constants.API_URL)/course/"
        guard let url = URL(string: endpoint) else { throw loadingErrors.invalidURL }
        guard let JWT = SessionExpirationManager.shared.identityModel?.JWT else { throw loadingErrors.invalidToken}
        
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST" // We want to use the POST method
        request.setValue("Bearer \(JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON
        request.setValue("application/json", forHTTPHeaderField: "Content-Type") // In the HTTP header alert the server that we are sending JSON data
        
        let bodyData = try JSONEncoder().encode(self.courseToCreate)
        
        let (data, response) = try await URLSession.shared.upload(for: request, from: bodyData)
        guard let response = response as? HTTPURLResponse else { throw loadingErrors.invalidURL }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 406 {
                throw loadingErrors.invalidData
            } else {
                throw loadingErrors.invalidToken
            }
        }
    }
    
    
    func createCourse() {
        guard canCreateCourse else {
            self.errorAndNotificationController.displayPopUpMessage("Please make sure you fill out all of the fields.")
            return
        }
        
        Task {
            self.isLoading = true
            
            do {
                try await sendRequestToCreateCourse()
                self.errorAndNotificationController.displayPopUpMessage("You have successfully created the course feel free to go back to the main page!")
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

