//
//  PrimaryButtonView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct PrimaryButtonView: View {
    let text: String
    @Binding var isEnable: Bool
    let action: () -> Void
    
    let textColor : Color
    let foregroundActiveColor : Color
    let foregroundDisableColor : Color
    var foregroundColor : Color {
        isEnable ? foregroundActiveColor : foregroundDisableColor
    }
    
    init(
        text: String,
        isEnable: Binding<Bool>,
        action: @escaping () -> Void,
        textColor : Color = .onPrimaryContainer,
        foregroundActiveColor : Color = .primaryContainer,
        foregroundDisableColor : Color = .secondaryContainer
    ) {
        self.text = text
        self._isEnable = isEnable
        self.action = action
        self.textColor = textColor
        self.foregroundActiveColor = foregroundActiveColor
        self.foregroundDisableColor = foregroundDisableColor
    }
    
    var body: some View {
        Button {
            action()
        } label: {
            Text(text)
                .foregroundStyle(textColor)
                .font(.title2)
                .frame(maxWidth: .infinity)
                .padding()
                .background(
                    RoundedRectangleWithOutlineView(fillColor: foregroundColor)
                )
        }
    }
}

#Preview {
    @Previewable @State var test : Bool = true
    
    PrimaryButtonView(
        text: "Create",
        isEnable: $test
    ) {
        print("test")
    }
        .padding()
}
