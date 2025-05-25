//
//  AddRemoveStudentsView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct AddRemoveStudentsView: View {
    @State private var controller : AddRemoveStudentViewModel
    
    init(courseId : Int) { self.controller = .init(courseId: courseId)}
    
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            VStack {
                HStack() {
                    CustomTextField(fieldText: "Student ID", text_field: $controller.studentId)
                    
                    AddButtonView(triggerFunction: controller.enrollStudent)
                    .padding(.horizontal)
                    .offset(y: 10)
                }
                
                Divider()
                    .padding(.vertical)
                
                ScrollView {
                    ForEach(controller.students) { student in
                        HStack {
                            StudentCardView(student: student)
                            
                            RemoveButtonView {
                                controller.removeStudent(student: student)
                            }
                            .padding(.horizontal)
                        }
                        .padding(.bottom)
                    }
                }
                .padding(.top)
                .scrollIndicators(.hidden)
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
        .onAppear() {
            Task {
                await controller.loadView()
            }
        }
    }
}

#Preview {
    AddRemoveStudentsView(courseId: 231)
}
