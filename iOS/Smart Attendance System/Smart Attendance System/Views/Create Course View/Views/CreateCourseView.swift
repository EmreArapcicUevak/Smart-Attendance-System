//
//  CreateCourseView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct CreateCourseView: View {
    @State private var controller = CreateCourseViewModel()
    
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            VStack {
                CoursePropertiesView(course: $controller.courseToCreate)
                
                PrimaryButtonView(
                    text: "Create",
                    isEnable: $controller.canCreateCourse,
                    action: controller.createCourse
                )
            }
            .padding()
            
            ErrorAndNotificationView(
                notificationMessage: $controller.errorAndNotificationController.popUpMessage,
                errorMessage: $controller.errorAndNotificationController.errorMessage,
                notificationVisible: $controller.errorAndNotificationController.showPopUp,
                errorVisible: $controller.errorAndNotificationController.showError
            )
            
            LoadingView(isLoading: $controller.isLoading)
        }
        .onChange(of: controller.courseToCreate, controller.checkIfCanCreateCourse)
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItemGroup(placement: .topBarTrailing) {
                UserSettingsIcon()
            }
            
            ToolbarItem(placement: .principal) {
                Text("Create Course")
                    .font(.title2)
                    .fontWeight(.bold)
                    .foregroundStyle(Color.mySecondary)
            }
            
            ToolbarItem(placement: .topBarLeading) {
                BackArrowView()
            }
        }
        .navigationTitle("")
        .navigationBarTitleDisplayMode(.inline)
        .toolbarBackground(Color.surfaceBright, for: .navigationBar)
        .toolbarBackgroundVisibility(.visible, for: .navigationBar)
    }
}

#Preview {
    NavigationStack {
        CreateCourseView()
    }
}
