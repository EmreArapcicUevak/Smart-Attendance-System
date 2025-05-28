//
//  PopUpViewModel.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import Foundation
import SwiftUI

@Observable
class PopUpViewModel {
    let displayMessage: String
    let buttonText: String
    let headingLabelText: String
    let headingLabelSystemImage: String
    let containerColor: Color
    let onContainerColor: Color
    let textColor: Color
    
    init(displayMessage: String, buttonText: String, headingLabelText: String, headingLabelSystemImage: String, containerColor: Color, onContainerColor: Color, textColor: Color) {
        self.displayMessage = displayMessage
        self.buttonText = buttonText
        self.headingLabelText = headingLabelText
        self.headingLabelSystemImage = headingLabelSystemImage
        self.containerColor = containerColor
        self.onContainerColor = onContainerColor
        self.textColor = textColor
    }
}
