//
//  LoginTextField.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct CustomTextField: View {
    let fieldText: String
    @Binding var text_field: String
    let validationFunction : (String) -> Bool
    
    private var validInput: Bool {
        validationFunction(text_field)
    }
    
    private var textColor: Color {
        text_field.isEmpty || validInput ? Color.mySecondary : Color.error
    }
    
    private var outlineColor: Color {
        text_field.isEmpty || validInput ? Color.outline : Color.errorContainer
    }
    
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(fieldText)
                .foregroundStyle(textColor)
                .font(.subheadline)
            
            
            TextField(fieldText, text: $text_field)
                .padding()
                .overlay {
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(outlineColor, lineWidth: 1)
                }
                .foregroundStyle(outlineColor)
        }
    }
}


#Preview {
    @Previewable @State var text_field: String = ""
    
    CustomTextField(
        fieldText: "Email",
        text_field: $text_field
    ) { text in
        return true
    }
}
