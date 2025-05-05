//
//  UserModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import Foundation

@Observable
class UserModel : Hashable {
    static func == (lhs: UserModel, rhs: UserModel) -> Bool {
        return lhs.email == rhs.email
    }
    
    func hash(into hasher: inout Hasher) {
        hasher.combine(email)
    }
    
    var email: String
    var password: String
    
    init(email: String = "", password: String = "") {
        self.email = email
        self.password = password
    }
}
