//
//  UserModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import Foundation

@Observable
class UserModel {
    var isLoggedIn: Bool = false
    var email: String
    var password: String
    
    init(isLoggedIn: Bool = false, email: String = "", password: String = "") {
        self.isLoggedIn = isLoggedIn
        self.email = email
        self.password = password
    }
}
