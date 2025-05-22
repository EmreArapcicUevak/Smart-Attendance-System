//
//  ErrorAndNotificationView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 21. 5. 2025..
//

import SwiftUI

struct ErrorAndNotificationView: View {
    @Binding var notificationMessage: String
    @Binding var errorMessage: String
    @Binding var notificationVisible: Bool
    @Binding var errorVisible: Bool
    
    var body: some View {
        ZStack {
            NotificationPopUpView(
                notificationMessage: $notificationMessage,
                viewShown: $notificationVisible
            )
            
            ErrorPopUp(
                errorMessage: $errorMessage,
                errorShown: $errorVisible
            )
        }
    }
}

#Preview {
    ErrorAndNotificationView(
        notificationMessage: .constant("Notification"),
        errorMessage: .constant("Yes"),
        notificationVisible: .constant(true),
        errorVisible: .constant(false)
    )
}
