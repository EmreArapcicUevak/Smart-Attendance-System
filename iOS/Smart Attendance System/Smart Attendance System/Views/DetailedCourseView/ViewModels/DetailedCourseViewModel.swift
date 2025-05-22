//
//  DetailedCourseViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 22. 5. 2025..
//

import Foundation
import SwiftUI

@Observable
class DetailedCourseViewModel {
    var studnets : [StudentModel] = []
    let course : CourseModel
    let JWT : String

    let errorAndNotficationController = ErrorAndNotificationSimpleParamModel()
    var isLoading : Bool = true
    
    
    init(course: CourseModel, JWT : String) {
        self.course = course
        self.JWT = JWT
    }
    
    func loadStudents() async throws {
        /* wait for Ismail to finish
        let endpoint = "\(Constants.API_URL)/api/auth"
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
        guard let response = response as? HTTPURLResponse, response.statusCode == 201 else {
            self.errorAndNotficationController.displayErrorMessage("Invalid response code")
            throw AuthError.invalidResponse
        }
        
        return try JSONDecoder().decode(AuthResponseModel.self, from: data).accessToken
         */
    }
    
    func loadAttendance(for student: StudentModel) async throws {
        let endpoint = "\(Constants.API_URL)/api/students/\(student.student_id)/attendance"
        guard let url = URL(string: endpoint) else {
            self.errorAndNotficationController.displayErrorMessage("Invalid URL \"\(endpoint)\"")
            throw AuthError.invalidURL
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET" // We want to use the GET method
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON
        request.setValue("Bearer \(self.JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        
        let (data, response) = try await URLSession.shared.data(for: request)
        guard let response = response as? HTTPURLResponse else { throw AttendanceLoadingErrors.invalidResponse }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 401 {
                self.errorAndNotficationController.displayErrorMessage("Your session expired or you aren't authorized for this action. Please log in again.")
            } else {
                self.errorAndNotficationController.displayErrorMessage("An unknown error occurred...\nstatus code: \(response.statusCode)")
            }
            
            throw AttendanceLoadingErrors.invalidResponse
        }
        
        let dataDecoded = try JSONDecoder().decode(GetAttendanceResponse.self, from: data)
        
        for attendanceDetail : AttendanceModel in dataDecoded.data {
            
        }
    }
    
    func loadView() async {
        
    }
}
