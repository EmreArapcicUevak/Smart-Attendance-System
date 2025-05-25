//
//  SubmitScanViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 25. 5. 2025..
//

import Foundation
import CodeScanner
import SwiftUI

@Observable
class SubmitScanViewModel {
    var scannedStudents: Binding<[StudentModel]>
    let selectedComponent : String
    let selectedWeek : Double
    let course : CourseModel
    
    var errorAndNotificationController = ErrorAndNotificationSimpleParamModel()
    var isLoading : Bool = false
    
    init(scannedStudents: Binding<[StudentModel]>, selectedComponent: String, selectedWeek: Double, course: CourseModel) {
        self.scannedStudents = scannedStudents
        self.selectedComponent = selectedComponent
        self.selectedWeek = selectedWeek
        self.course = course
    }
    
    func discardScan() {
        self.scannedStudents.wrappedValue.removeAll()
    }
    
    func requestSubmit() async throws {
        let endpoint = "\(Constants.API_URL)/attendance/mark"
        guard let url = URL(string: endpoint) else { throw loadingErrors.invalidURL }
        guard let JWT = SessionExpirationManager.shared.identityModel?.JWT else { throw loadingErrors.invalidToken}
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST" // We want to use the POST method
        request.setValue("application/json", forHTTPHeaderField: "Content-Type") // In the HTTP header alert the server that we are sending JSON data
        request.setValue("Bearer \(JWT)", forHTTPHeaderField: "Authorization") // Use the JWT for the authorization
        request.setValue("application/json", forHTTPHeaderField: "Accept") // We accept JSON

        let body = SubmitAttendanceModel(
            courseId: course.id,
            week: Int(selectedWeek),
            courseType: selectedComponent,
            status: "ATTENDED",
            attendees: scannedStudents.map() { $0.id }
        )
        let bodyData = try JSONEncoder().encode(body)
        
        let (_, response) = try await URLSession.shared.upload(for: request, from: bodyData)
        guard let response = response as? HTTPURLResponse else { throw loadingErrors.invalidURL }
        
        guard response.statusCode == 200 else {
            if response.statusCode == 406 {
                throw loadingErrors.invalidData
            } else {
                throw loadingErrors.invalidToken
            }
        }
        
        discardScan()
    }
    
    func submitScan() {
        Task {
            self.isLoading = true
            
            do {
                try await requestSubmit()
            } catch(loadingErrors.invalidToken) {
                SessionExpirationManager.shared.errorAndNotificationController?.displayErrorMessage("You token isn't valid anymore.")
                SessionExpirationManager.shared.popToRoot()
            } catch(loadingErrors.invalidURL) {
                self.errorAndNotificationController.displayErrorMessage("Invalid URL endpoint.")
            } catch(loadingErrors.noServerResponse) {
                self.errorAndNotificationController.displayErrorMessage("Couldn't get response from the server.")
            } catch(loadingErrors.invalidData) {
                self.errorAndNotificationController.displayErrorMessage("Invalid data sent to the server.")
            } catch {
                self.errorAndNotificationController.displayErrorMessage(error.localizedDescription)
            }
            
            
            self.isLoading = false
        }
    }
}
