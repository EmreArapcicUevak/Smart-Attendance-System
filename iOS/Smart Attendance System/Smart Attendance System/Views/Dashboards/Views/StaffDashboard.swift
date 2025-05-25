//
//  StaffDashboard.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct StaffDashboard: View {
    @State private var controller : StaffDashboardViewModel = StaffDashboardViewModel()
    
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            VStack {
                ScrollView() {
                    
                    ForEach(self.controller.courses) { course in
                        NavigationLink(value: course) {
                            CourseCardView(courseModel: course)
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
        .navigationDestination(for: CreateCourseRouteModel.self, destination: { routeModel in
            CreateCourseView()
        })
        .navigationDestination(for: CourseEditRoute.self, destination: { courseEditRouteModel in
            CourseSettingsView(course: courseEditRouteModel.course)
        })
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItemGroup(placement: .topBarTrailing) {
                AddButtonView(
                    triggerFunction: addCreateCourseView
                )
                
                UserSettingsIcon()
            }
            
            ToolbarItem(placement: .principal) {
                Text("Staff Dashboard")
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
    @Previewable @State var path = NavigationPath()
    NavigationStack(path: $path) {
        StaffDashboard()
    }
}
