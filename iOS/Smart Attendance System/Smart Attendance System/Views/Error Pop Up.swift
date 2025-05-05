//
//  Error Pop Up.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct ErrorPopUp: View {
    let errorMessage: String
    let errorButtonText: String
    
    @Binding var errorShown: Bool

    init(
        errorMessage: String,
        errorButtonText: String = "Okay",
        errorShown: Binding<Bool>
    ) {
        self.errorMessage = errorMessage
        self.errorButtonText = errorButtonText
        self._errorShown = errorShown
    }
    
    
    var body: some View {
        if errorShown {
            ZStack {
                Color
                    .surfaceBright
                    .ignoresSafeArea()
                    .blur(radius: 10)
                    .opacity(0.4)
                
                VStack {
                    HStack {
                        Label(
                            "Error",
                            systemImage: "exclamationmark.triangle.fill"
                        )
                        .font(.callout)
                        .foregroundStyle(Color.onErrorContainer)
                        .fontWeight(.bold)
                        
                        Spacer()
                    }
                    
                    Text(errorMessage)
                        .font(.headline)
                        .fontWeight(.bold)
                        .foregroundStyle(Color.error)
                        .padding()
                    
                    ErrorButtonView(
                        errorButtonText: errorButtonText,
                        errorShown: $errorShown
                    )
                }
                .padding()
                .background(
                    RoundedRectangleWithOutlineView(fillColor: Color.errorContainer)
                )
                .frame(maxWidth: 360, maxHeight: 260, alignment: .center)
                
                   
            }
        }
    }
}

#Preview {
    ZStack {
        Color
            .surface
            .ignoresSafeArea()
        
        ErrorPopUp(
            errorMessage: "Something went wrong!",
            errorShown: .constant(true)
        )
    }
}

struct ErrorButtonView: View {
    let errorButtonText: String;
    @Binding var errorShown: Bool
    
    var body: some View {
        ConfirmationButtonView(
            buttonText: errorButtonText,
            viewOpen: $errorShown
        )
        .padding()
        .foregroundStyle(Color.errorContainer)
        .frame(minWidth: 200)
        .background(
            RoundedRectangleWithOutlineView(fillColor: Color.onErrorContainer)
        )
        .padding(.top)
        
    }
}
