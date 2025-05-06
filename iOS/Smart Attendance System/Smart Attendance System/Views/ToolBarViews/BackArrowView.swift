//
//  BackArrowView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct BackArrowView: View {
    @Binding var path : NavigationPath
    var body: some View {
        Button {
            if !path.isEmpty {
                path.removeLast()
            }
        } label: {
            Image(systemName: "chevron.left")
                .foregroundStyle(Color.mySecondary)
        }

    }
}

#Preview {
    @Previewable @State var path = NavigationPath()
    
    BackArrowView(path: $path)
}
