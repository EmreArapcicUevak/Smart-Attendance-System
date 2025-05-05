//
//  LoginSecureField.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct LoginSecureField: View {
    let fieldText: String
    @Binding var text_field: String
    
    
    var body: some View {
        SecureField(fieldText, text: $text_field)
            .padding()
            .overlay {
                RoundedRectangle(cornerRadius: 8)
                    .stroke(Color.outline, lineWidth: 1)
            }
            .foregroundStyle(Color.outline)
    }
}

#Preview {
    LoginSecureField(
        fieldText: "Password",
        text_field: .constant(
            ""
        )
    )
}
