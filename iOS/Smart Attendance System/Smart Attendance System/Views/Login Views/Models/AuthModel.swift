//
//  AuthModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 20. 5. 2025..
//

import Foundation

struct AuthResponseModel : Codable { // The response
    let accessToken: String
}

struct AuthRequestModel : Codable { // For making requests
    let email: String
    let password: String
}

enum AuthError: Error {
    case invalidURL
    case invalidResponse
    case invalidData
    case invalidEmailOrPassword
}
