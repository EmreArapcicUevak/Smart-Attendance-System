//
//  PopUpButtonView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 21. 5. 2025..
//

import SwiftUI

struct PopUpButtonView: View {
    @Environment(PopUpViewModel.self) var popUpViewModel
    @Binding var viewShown: Bool
    
    var body: some View {
        Button {
            withAnimation {
                viewShown = false
            }
        } label: {
            Text(popUpViewModel.buttonText)
                .padding()
                .foregroundStyle(popUpViewModel.containerColor)
                .frame(minWidth: 200)
                .background(
                    RoundedRectangleWithOutlineView(fillColor: popUpViewModel.onContainerColor)
                )
                .padding(.top)
        }
        
    }
}

#Preview {
    PopUpButtonView(viewShown: .constant(true))
        .environment(PopUpViewModel.init(displayMessage: "yes", buttonText: "yes", headingLabelText: "yes", headingLabelSystemImage: "square.and.arrow.up", containerColor: Color.surfContainer, onContainerColor: Color.onSurfaceVariant, textColor: .myPrimary))
}
