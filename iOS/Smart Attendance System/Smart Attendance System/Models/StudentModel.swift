//
//  StudentModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import Foundation

struct StudentModel : Hashable, Identifiable {
    var major: String
    var firstName: String
    var secondName: String
    var email: String
    var student_id: String
    
    var id: String { student_id }
    var name: String { "\(firstName) \(secondName)" }
}
