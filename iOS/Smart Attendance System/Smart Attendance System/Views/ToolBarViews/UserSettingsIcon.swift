//
//  UserSettingsIcon.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct UserSettingsIcon: View {
    var body: some View {
        Menu {
            Button {
                print("do user settings")
            } label: {
                Label("Settings", systemImage: "gearshape")
            }
            
            Button {
                SessionExpirationManager.shared.popToRoot()
            } label: {
                Label("Logout", systemImage: "rectangle.portrait.and.arrow.forward")
            }
        } label: {
            Image(systemName: "person.circle.fill")
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(maxWidth: 20, maxHeight: 20)
                .foregroundStyle(Color.mySecondary)
                
        }
    }
}

#Preview {
    UserSettingsIcon()
}
