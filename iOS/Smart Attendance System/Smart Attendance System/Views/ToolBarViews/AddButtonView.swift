//
//  AddButtonView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct AddButtonView: View {
    var triggerFunction : () -> Void
    
    var body: some View {
        Button {
            triggerFunction()
        } label: {
            Image(systemName: "plus")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(maxWidth: 20, maxHeight: 20)
                .foregroundStyle(Color.mySecondary)
        }
    }
}

#Preview {
    @Previewable @State var path = NavigationPath()
    
    AddButtonView() {
        SessionExpirationManager.shared.path?.wrappedValue.append(UserModel())
    }
}
