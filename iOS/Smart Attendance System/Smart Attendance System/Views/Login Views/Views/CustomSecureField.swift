//
//  LoginSecureField.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI


struct CustomSecureField: View {
    let fieldText: String
    @Binding var text_field: String
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(fieldText)
                .foregroundStyle(.mySecondary)
                .font(.subheadline)
                .fontWeight(.light)
            
            
            SecureField(fieldText, text: $text_field)
                .padding()
                .overlay {
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color.outline, lineWidth: 1)
                }
                .foregroundStyle(.myPrimary)
        }
    }
}

#Preview {
    @Previewable @State var text_field: String = ""
    
    CustomSecureField(
        fieldText: "Password",
        text_field: $text_field
    )
}

