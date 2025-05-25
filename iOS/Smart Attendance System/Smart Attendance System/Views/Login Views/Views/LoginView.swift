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
                    
                    CustomTextField(
                        fieldText: "Email",
                        text_field: $controller.userModel.email
                    )
                    .padding(.bottom)
                    
                    CustomSecureField(
                        fieldText: "Password",
                        text_field: $controller.userModel.password
                    )
                    
                    
                    Button("Forgot Password") {
                        controller.errorAndNotficationController.displayPopUpMessage("Please contact your organization administrator if you forgot your password")
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
                            .foregroundStyle(Color.myPrimary)
                            .background(
                                RoundedRectangle(cornerRadius: 15)
                                    .fill(Color.primaryContainer)
                            )
                            .padding(.top)
                    }

                }
                .padding()
                .background(Color.surfaceContainer)
                .clipShape(.rect(cornerRadius: 5))
                .frame(maxWidth: 370)
                
                LoadingView(isLoading: $controller.isLoading)
                ErrorAndNotificationView(
                    notificationMessage: $controller.errorAndNotficationController.popUpMessage,
                    errorMessage: $controller.errorAndNotficationController.errorMessage,
                    notificationVisible: $controller.errorAndNotficationController.showPopUp,
                    errorVisible: $controller.errorAndNotficationController.showError
                )
            }
            .onAppear() {
                SessionExpirationManager.shared.path = $controller.path
                SessionExpirationManager.shared.errorAndNotificationController = controller.errorAndNotficationController
            }
            .navigationDestination(for: IdentityModel.self) { idenModel in
                if idenModel.role == "TEACHER" {
                    StaffDashboard()
                } else if idenModel.role == "STUDENT" {
                    StudentDashboardView()
                } else {
                    ErrorPopUp(
                        errorMessage: 
                                .constant("There is current no view made for this role (\(idenModel.role))"),
                        errorShown: 
                                .constant(true)
                    )
                }
            }
        }
        
    }
}

#Preview {
    LoginView()
}
