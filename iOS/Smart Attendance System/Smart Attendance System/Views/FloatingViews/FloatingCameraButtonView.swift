//
//  FloatingButton.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct FloatingCameraButtonView: View {
    let action: () -> Void
    
    var body: some View {
        Button {
            action()
        } label: {
            Image(systemName: "camera.fill")
                .foregroundStyle(Color.myPrimary)
                .font(.system(size: 20))
                .background(
                    RoundedRectangleWithOutlineView(
                        fillColor: Color.surfaceContainerHighest,
                        cornerRadius: 12
                    )
                    .frame(width: 56, height: 56)
                )
                
        }

    }
}

#Preview {
    FloatingCameraButtonView() {
        print("Nice")
    }
}
