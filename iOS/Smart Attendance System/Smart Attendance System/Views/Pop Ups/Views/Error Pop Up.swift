//
//  Error Pop Up.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct ErrorPopUp: View {
    @Binding var errorMessage: String
    @Binding var errorButtonText: String
    
    @Binding var errorShown: Bool

    init(
        errorMessage: Binding<String>,
        errorButtonText: Binding<String> = .constant("Okay"),
        errorShown: Binding<Bool>
    ) {
        self._errorMessage = errorMessage
        self._errorButtonText = errorButtonText
        self._errorShown = errorShown
    }
    
    
    var body: some View {
        PopUpView(viewShown: $errorShown)
            .environment(
                PopUpViewModel(
                    displayMessage: errorMessage,
                    buttonText: errorButtonText,
                    headingLabelText: "Error",
                    headingLabelSystemImage: "exclamationmark.triangle",
                    containerColor: Color.errorContainer,
                    onContainerColor: Color.onErrorContainer,
                    textColor: Color.error
                )
            )
    }
}

#Preview {
    ZStack {
        Color
            .surface
            .ignoresSafeArea()
        
        ErrorPopUp(
            errorMessage: .constant("Something went wrong"),
            errorShown: .constant(true)
        )
    }
}
