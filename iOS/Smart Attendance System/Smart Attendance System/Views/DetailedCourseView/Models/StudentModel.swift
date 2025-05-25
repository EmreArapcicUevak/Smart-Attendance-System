//
//  StudentModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import Foundation

struct StudentModel : Hashable, Identifiable, Codable, Equatable {
    var email: String
    var id: Int
    var fullName: String
}

struct StudentListResponse: Codable {
    var students: [StudentModel]
}
