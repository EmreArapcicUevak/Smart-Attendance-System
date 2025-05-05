//
//  PopUpView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct PopUpView: View {
    @Environment(PopUpViewModel.self) var popUpViewModel
    @Binding var viewShown: Bool

    
    var body: some View {
        if viewShown {
            ZStack {
                // Blur
                Color
                    .surfaceBright
                    .ignoresSafeArea()
                    .blur(radius: 10)
                    .opacity(0.4)
                
                VStack {
                    HStack {
                        Label(
                            popUpViewModel.headingLabelText,
                            systemImage: popUpViewModel.headingLabelSystemImage
                        )
                        .font(.callout)
                        .foregroundStyle(popUpViewModel.textColor)
                        .fontWeight(.bold)
                        
                        Spacer()
                    }
                    
                    Text(popUpViewModel.displayMessage)
                        .font(.headline)
                        .fontWeight(.bold)
                        .foregroundStyle(popUpViewModel.textColor)
                        .padding()
                    
                    PopUpButtonView(viewShown: $viewShown)
                        .environment(popUpViewModel)
                }
                .padding()
                .background(
                    RoundedRectangleWithOutlineView(fillColor: popUpViewModel.containerColor)
                )
                .frame(maxWidth: 360, maxHeight: 260, alignment: .center)
                
                   
            }
        }
    }
}

struct PopUpButtonView : View {
    @Environment(PopUpViewModel.self) var popUpViewModel
    @Binding var viewShown: Bool
    
    var body: some View {
        Button(popUpViewModel.buttonText, action: {
            withAnimation {
                viewShown = false
            }
        })
        .padding()
        .foregroundStyle(popUpViewModel.containerColor)
        .frame(minWidth: 200)
        .background(
            RoundedRectangleWithOutlineView(fillColor: popUpViewModel.onContainerColor)
        )
        .padding(.top)
        
    }
}

#Preview {
    PopUpView(viewShown: .constant(true))
        .environment(
            PopUpViewModel(
                displayMessage: "Something went wrong",
                buttonText: "Okay",
                headingLabelText: "Error",
                headingLabelSystemImage: "exclamationmark.triangle",
                containerColor: Color.errorContainer,
                onContainerColor: Color.onErrorContainer,
                textColor: Color.error
            )
        )
}
