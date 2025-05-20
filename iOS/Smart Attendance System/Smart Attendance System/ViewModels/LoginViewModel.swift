//
//  LoginViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 20. 5. 2025..
//

import Foundation

class LoginViewModel {
    func checkValidEmail(_ email: String) -> Bool {
        let emailRegex = #"^[A-Za-z0-9._%+-]+@(([A-Za-z0-9.-]+\.[A-Za-z]{2,})|(\[[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}\]))$"#
        let predicate = NSPredicate(format: "SELF MATCHES %@", emailRegex)
        guard predicate.evaluate(with: email) else {
            return false
        }
        
        let parts = email.split(separator: "@", maxSplits: 1)
        guard parts.count == 2 else { return false }
        
        let localPart = parts[0]
        let hostPart = parts[1]

        let invalidSymbolPredicate = NSPredicate(
            format: "SELF MATCHES %@",
            #".*((^(\.|-|\+|_))|(\.$)|(\.{2,})).*"#
        )
        
        guard invalidSymbolPredicate.evaluate(with: localPart) == false && invalidSymbolPredicate.evaluate(with: hostPart) == false else {
            return false
        }
        
        if hostPart.contains("[") {
            let ipNumbers = hostPart.split(separator: "[").last!.split(separator: "]").first!.split(separator: ".")
            
            for ipNumber in ipNumbers {
                guard let ipValue = Int(ipNumber), ipValue >= 0 && ipValue <= 255 else {
                    return false
                }
            }
        }
        
        return true
    }
}
