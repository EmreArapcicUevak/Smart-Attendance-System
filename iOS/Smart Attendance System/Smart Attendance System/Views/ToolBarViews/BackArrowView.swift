//
//  BackArrowView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct BackArrowView: View {
    var body: some View {
        Button {
            if let path = SessionExpirationManager.shared.path?.wrappedValue, !path.isEmpty {
                SessionExpirationManager.shared.path?.wrappedValue.removeLast()
            }
        } label: {
            Image(systemName: "chevron.left")
                .foregroundStyle(Color.mySecondary)
        }

    }
}

#Preview {
    BackArrowView()
}
