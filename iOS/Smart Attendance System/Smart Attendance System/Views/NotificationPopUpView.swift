//
//  NotificationPopUpView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct NotificationPopUpView: View {
    let notificationMessage: String
    let buttonText: String
    
    @Binding var viewShown: Bool

    init(notificationMessage: String, buttonText: String = "Okay", viewShown: Binding<Bool>) {
        self.notificationMessage = notificationMessage
        self.buttonText = buttonText
        self._viewShown = viewShown
    }
    
    
    var body: some View {
        PopUpView(viewShown: $viewShown)
            .environment(
                PopUpViewModel(
                    displayMessage: notificationMessage,
                    buttonText: buttonText,
                    headingLabelText: "Notification",
                    headingLabelSystemImage: "info.circle.fill",
                    containerColor: Color.tertiaryContainer,
                    onContainerColor: Color.onTertiaryContainer,
                    textColor: Color.tertiary
                )
            )
    }
}

#Preview {
    NotificationPopUpView(
        notificationMessage: "Notification Message Goes Here",
        viewShown: .constant(true)
    )
}
