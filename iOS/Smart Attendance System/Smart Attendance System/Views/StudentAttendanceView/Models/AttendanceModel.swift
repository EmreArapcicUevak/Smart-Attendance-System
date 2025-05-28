//
//  AttendanceModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import Foundation

struct AttendanceModel: Codable {
    var lecture : [String]? = nil
    var tutorial : [String]? = nil
    var lab : [String]? = nil
}

struct AttendanceRouteModel: Hashable {
    var course : CourseModel
    var student : StudentModel
}

extension Constants {
    static let attendance_status_attended = "ATTENDED"
    static let attendance_status_missed = "MISSED"
    static let attendance_status_not_marked = "NOT_MARKED"
}
