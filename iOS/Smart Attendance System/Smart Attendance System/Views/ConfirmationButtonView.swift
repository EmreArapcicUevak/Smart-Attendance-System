//
//  ConfirmationButtonView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct ConfirmationButtonView: View {
    let buttonText: String
    @Binding var viewOpen: Bool
    
    var body: some View {
        Button(buttonText) {
            withAnimation(){
                viewOpen = false
            }
        }
    }
}

#Preview {
    ConfirmationButtonView(
        buttonText: "Test",
        viewOpen: .constant(true)
    )
}
