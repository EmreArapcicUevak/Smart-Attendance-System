//
//  LoadingErrors.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 23. 5. 2025..
//

import Foundation

enum loadingErrors : Error {
    case noData
    case decodeError
    case noServerResponse
    case invalidURL
    case invalidToken
    case invalidData
}
