//
//  ContentView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct LoginView: View {
    @State private var controller = LoginViewModel()
    
    var body: some View {
        NavigationStack(path: $controller.path) {
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
                        text_field: $controller.userModel.email
                    )
                    .padding(.bottom)
                    
                    LoginSecureField(
                        fieldText: "Password",
                        text_field: $controller.userModel.password
                    )
                    
                    
                    Button("Forgot Password") {
                        controller.displayPopUpMessage("Please contact your organization administrator if you forgot your password")
                    }
                    .padding(.top)
                    .foregroundStyle(Color.secondary)
                    
                    Button {
                        Task {
                            await controller.login()
                        }
                    } label: {
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
                
                LoadingView(isLoading: $controller.isLoading)
                ErrorAndNotificationView(
                    notificationMessage: $controller.popUpMessage,
                    errorMessage: $controller.errorMessage,
                    notificationVisible: $controller.showPopUp,
                    errorVisible: $controller.showError
                )
            }
            .navigationDestination(for: UserModel.self) { userModel in
                StaffDashboard(path: $controller.path)
            }
        }
        
    }
}

#Preview {
    LoginView()
}
