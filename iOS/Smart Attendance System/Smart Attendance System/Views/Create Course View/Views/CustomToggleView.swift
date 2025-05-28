//
//  CustomToggleView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct CustomToggleView: View {
    let labelText : String
    @Binding var toggleValue : Bool
    
    var textColor : Color {
        toggleValue ? .myPrimary : .mySecondary
    }
    
    var body: some View {
        Toggle(labelText, isOn: $toggleValue)
            .foregroundStyle(textColor)
            .toggleStyle(.switch)
    }
}

#Preview {
    @Previewable @State var value = false
    CustomToggleView(
        labelText: "Something",
        toggleValue: $value
    )
}
