//
//  StudentDashboardView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 25. 5. 2025..
//

import SwiftUI

struct StudentDashboardView: View {
    @State private var controller : StudentDashboardViewController = StudentDashboardViewController()
    
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            VStack {
                ScrollView() {
                    
                    ForEach(self.controller.courses) { course in
                        NavigationLink(value: course) {
                            CourseCardView(courseModel: course, isForStudent: true)
                                .padding()
                        }
                    }
                }
                .scenePadding(.bottom)
            }
            
            LoadingView(isLoading: $controller.isLoading)
            ErrorAndNotificationView(
                notificationMessage: $controller.errorAndNotificationController.popUpMessage,
                errorMessage: $controller.errorAndNotificationController.errorMessage,
                notificationVisible: $controller.errorAndNotificationController.showPopUp,
                errorVisible: $controller.errorAndNotificationController.showError
            )
        }
        .navigationDestination(for: CourseModel.self, destination: { courseModel in
            DetailedCourseView(course: courseModel)
        })
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                UserSettingsIcon()
            }
            
            ToolbarItem(placement: .principal) {
                Text("Student Dashboard")
                    .font(.title2)
                    .fontWeight(.bold)
                    .foregroundStyle(Color.mySecondary)
            }
        }
        .navigationTitle("")
        .navigationBarTitleDisplayMode(.inline)
        .toolbarBackground(Color.surfaceBright, for: .navigationBar)
        .toolbarBackgroundVisibility(.visible, for: .navigationBar)
        .onAppear() {
            Task {
                await controller.loadView()
            }
        }
    }
    
}

#Preview {
    NavigationStack {
        StudentDashboardView()
    }
}
