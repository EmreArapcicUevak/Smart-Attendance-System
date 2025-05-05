//
//  ContentView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct LoginView: View {
    @State private var userModel: UserModel = .init()
    @State private var notificationVisible = false
    @State private var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
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
                    
                    LoginSecureField(
                        fieldText: "Password",
                        text_field: $userModel.password
                    )
                    
                    
                    Button("Forgot Password") {
                        notificationVisible = true
                    }
                    .padding(.top)
                    .foregroundStyle(Color.secondary)
                    
                    NavigationLink(value: userModel) {
                        Text("Login")
                            .frame(maxWidth: .infinity)
                            .padding()
                            .foregroundStyle(Color.primary)
                            .background(
                                RoundedRectangle(cornerRadius: 15)
                                    .fill(Color.primaryContainer)
                            )
                            .padding(.top)
                    }
                }
                .padding()
                .background(Color.surfContainer)
                .clipShape(.rect(cornerRadius: 5))
                .frame(maxWidth: 370)
                
                NotificationPopUpView(
                    notificationMessage: "Please contact your organization administrator if you forgot your password",
                    viewShown: $notificationVisible
                )
            }
            .navigationDestination(for: UserModel.self) { userModel in
                StaffDashboard(path: $path)
            }
        }
        
    }
}

#Preview {
    LoginView()
}
