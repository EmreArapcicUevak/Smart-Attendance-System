//
//  CourseModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import Foundation

struct CourseModel : Identifiable, Hashable, Codable {
    var courseName: String
    var courseFaculty: String
    var courseCode: String
    var hasTutorial: Bool
    var hasLab: Bool
    let courseId: Int
    
    var id : Int {
        courseId
    }
}
