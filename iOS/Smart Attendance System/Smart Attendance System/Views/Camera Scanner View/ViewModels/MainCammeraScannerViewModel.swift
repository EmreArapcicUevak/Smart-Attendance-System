//
//  MainCammeraScannerViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 25. 5. 2025..
//

import Foundation
import CodeScanner

@Observable
class MainCameraScannerViewModel {
    let students: [StudentModel]
    let course : CourseModel
    
    var scannedStudents: [StudentModel] = []
    var errorAndNotificationController = ErrorAndNotificationSimpleParamModel()

    var showScanner: Bool = false
    var showSubmittingScreen: Bool {
        showScanner == false && scannedStudents.count > 0
    }
    
    var selectedWeek : Double = 1
    var selectedComponent : String = Constants.component_lecture

    var validOptions : Bool {
        if self.selectedComponent != Constants.component_lecture {
            if self.selectedComponent == Constants.component_lab && self.course.hasLab == false {
                errorAndNotificationController.displayErrorMessage("Invalid option selected, this course doesn't have labs")
                return false
            }
            
            if self.selectedComponent == Constants.component_tutorial && self.course.hasTutorial == false  {
                errorAndNotificationController.displayErrorMessage("Invalid option selected, this course doesn't have tutorials")
                return false
            }
        }
        
        return true
    }
    
    init(_ data : CameraRouteModel) {
        self.students = data.students
        self.course = data.course
    }
    
    func startScanning() {
        guard validOptions else { return }
        self.showScanner = true
    }
    func handleScan(result: Result<ScanResult, ScanError>) {
        
        switch result {
        case .success(let result):
            let studentId = result.string
            
            guard let convertedId = Int(studentId) else { return }
            if let student = students.first(where: { $0.id == convertedId }) {
                scannedStudents.append(student)
            }
        case .failure(let error):
            errorAndNotificationController.displayErrorMessage("Scanning failed: \(error.localizedDescription)")
            print("Scanning failed: \(error.localizedDescription)")
        }
    }
}
