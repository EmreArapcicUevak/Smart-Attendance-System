//
//  LoginViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 20. 5. 2025..
//

import Foundation

class LoginViewModel {
    func checkValidEmail(_ email: String) -> Bool {
        let emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        return email.range(of: emailRegex, options: .regularExpression, range: nil, locale: nil) != nil
    }
}
