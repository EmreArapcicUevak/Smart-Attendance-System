//
//  SubmitAttendanceModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 25. 5. 2025..
//

import Foundation

struct SubmitAttendanceModel: Codable {
    let courseId : Int
    let week : Int
    let courseType : String
    let status : String
    let attendees : [Int]
}
