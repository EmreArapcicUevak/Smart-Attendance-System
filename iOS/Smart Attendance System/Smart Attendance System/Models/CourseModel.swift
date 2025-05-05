//
//  CourseModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import Foundation

struct Course : Identifiable {
    let courseName: String
    let courseFaculty: String
    let courseCode: String
    let id = UUID()
}
