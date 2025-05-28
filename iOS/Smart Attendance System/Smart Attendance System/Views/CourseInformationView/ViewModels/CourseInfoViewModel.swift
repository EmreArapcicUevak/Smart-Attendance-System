//
//  CourseInfoViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 23. 5. 2025..
//

import Foundation

@Observable
class CourseInfoViewModel {
    let courseNameErrorMessage: String = "The course name has to be shorter then 200 characters."
    
    func courseNameValidation(_ courseName: String) -> Bool {
        return courseName.count < 200
    }
    
    
}
