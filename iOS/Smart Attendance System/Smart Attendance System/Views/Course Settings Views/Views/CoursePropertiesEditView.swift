//
//  CoursePropertiesEditView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct CoursePropertiesEditView: View {
    @State private var controller : EditCourseViewModel
    init(course : CourseModel) { self.controller = .init(courseToEdit: course) }
    
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            VStack {
                CoursePropertiesView(course: $controller.courseToEdit)
                
                HStack(spacing: 25) {
                    PrimaryButtonView(
                        text: "Delete",
                        isEnable: .constant(true),
                        action: controller.deleteCourse,
                        textColor: Color.onErrorContainer,
                        foregroundActiveColor: Color.errorContainer
                    )
                    
                    PrimaryButtonView(
                        text: "Edit",
                        isEnable: $controller.canEditCourse,
                        action: controller.editCourse
                    )
                }
                .padding(.top)
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
        .onChange(
            of: controller.courseToEdit,
            controller.checkIfCanEditCourse
        )
        .onAppear() {
            controller.checkIfCanEditCourse()
        }
    }
}

#Preview {
    @Previewable @State var course : CourseModel = .init(
        courseName: "Test",
        courseFaculty: "Test",
        courseCode: "Test",
        hasTutorial: false,
        hasLab: false,
        courseId: 2
    )
    
    NavigationStack {
        CoursePropertiesEditView(course: course)
    }
}
