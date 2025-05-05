//
//  ContentView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct LoginView: View {
    @State private var userModel: UserModel = .init()
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            VStack(alignment: .leading) {
                Text("Login")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                    .foregroundStyle(Color.onSurface)
                
                Divider()

                LoginTextField(
                    fieldText: "Email",
                    text_field: $userModel.email
                )
                    .padding(.bottom)

                LoginTextField(fieldText: "Password", text_field: $userModel.password)

                Button("Forgot Password") {
                    
                }
                .padding(.top)
                .foregroundStyle(Color.secondary)
                
                Button("Login") {
                    
                }
                .frame(maxWidth: .infinity)
                .padding()
                .foregroundStyle(Color.primary)
                .background(
                    RoundedRectangle(cornerRadius: 15)
                        .fill(Color.primaryContainer)
                )
                .padding(.top)
            }
            .padding()
            .background(Color.surfContainer)
            .clipShape(.rect(cornerRadius: 5))
            .frame(maxWidth: 370)
        }
    }
}

#Preview {
    LoginView()
}
