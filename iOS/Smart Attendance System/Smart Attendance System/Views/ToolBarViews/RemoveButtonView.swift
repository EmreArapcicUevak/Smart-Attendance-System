//
//  RemoveButtonView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct RemoveButtonView: View {
    var triggerFunction : () -> Void
    
    var body: some View {
        Button {
            triggerFunction()
        } label: {
            Image(systemName: "minus.circle")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(maxWidth: 30, maxHeight: 30)
                .foregroundStyle(Color.mySecondary)
        }
    }
}

#Preview {
    @Previewable @State var path = NavigationPath()
    
    RemoveButtonView() {
        SessionExpirationManager.shared.path?.wrappedValue.append(UserModel())
    }
}
