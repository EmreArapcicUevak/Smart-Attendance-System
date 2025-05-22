//
//  IdentityModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 21. 5. 2025..
//

import Foundation

struct IdentityModel : Hashable {
    let JWT : String
    let expiresAt : Int
    let issuesAt : Int
    let role : String
}

enum jwtError : Error {
    case invalidToken
    
}
