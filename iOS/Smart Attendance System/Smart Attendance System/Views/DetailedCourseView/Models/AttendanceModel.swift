//
//  AttendanceModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 22. 5. 2025..
//

import Foundation

struct AttendanceModel : Codable {
    let id :  Int
    let courseId : Int
    let componentType : String
    let date : String // Only god knows which format he took
    let status : String // "Present" or "Absent
}

struct GetAttendanceResponse : Codable {
    let data : [AttendanceModel]
}

extension Constants {
    static let attendance_status_present : String = "Present"
    static let attendance_status_absent : String = "Absent"
}
