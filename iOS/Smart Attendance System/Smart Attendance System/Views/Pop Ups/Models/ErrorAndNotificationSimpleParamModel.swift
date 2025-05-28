//
//  ErrorAndNotificationSimpleParamModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 22. 5. 2025..
//

import Foundation
import SwiftUI

@Observable
class ErrorAndNotificationSimpleParamModel {
    var showPopUp: Bool = false
    var popUpMessage: String = ""
    
    var showError: Bool = false
    var errorMessage: String = ""
    
    func displayErrorMessage(_ message: String) {
        DispatchQueue.main.async {
            self.errorMessage = message
            self.showError = true
        }
    }
    
    func displayPopUpMessage(_ message: String) {
        DispatchQueue.main.async {
            self.popUpMessage = message
            self.showPopUp = true
        }
    }
}
