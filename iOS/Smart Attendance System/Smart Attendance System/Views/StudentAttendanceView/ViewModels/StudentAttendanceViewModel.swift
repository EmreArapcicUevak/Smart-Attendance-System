//
//  StudentAttendanceViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import Foundation
import SwiftUI

@Observable
class StudentAttendanceViewModel {
    let student : StudentModel
    let course : CourseModel
    var attendanceModel : AttendanceModel = AttendanceModel()

    var errorAndNotificationController = ErrorAndNotificationSimpleParamModel()
    var isLoading : Bool = false

    
    init(student: StudentModel, course: CourseModel) {
        self.student = student
        self.course = course
    }
    
    
    func loadStudents() async throws {
        let endpoint = "\(Constants.API_URL)/attendance/\(self.course.courseId)/\(self.student.id)"
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
        
        self.attendanceModel = try JSONDecoder().decode(AttendanceModel.self, from: data)
    }
    
    func loadView() async {
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
            self.errorAndNotificationController.displayPopUpMessage("There is no attendance data for this student.")
        } catch {
            self.errorAndNotificationController.displayErrorMessage(error.localizedDescription)
        }
        
        
        self.isLoading = false
    }
}
