//
//  AddButtonView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct AddButtonView: View {
    @Binding var path : NavigationPath
    var triggerFunction : (Binding<NavigationPath>) -> Void
    
    var body: some View {
        Button {
            triggerFunction($path)
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
    AddButtonView(path: $path) { path in
        path.wrappedValue.append(UserModel())
    }
}
