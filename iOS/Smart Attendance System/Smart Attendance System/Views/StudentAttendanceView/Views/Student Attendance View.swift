//
//  Student Attendance View.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct Student_Attendance_View: View {
    @State private var controller : StudentAttendanceViewModel
    
    init(student : StudentModel, course : CourseModel) {
            controller = StudentAttendanceViewModel(student: student, course: course)
    }
    
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            ScrollView() {
                AttendanceCardsView(
                    title: "\(self.controller.course.courseName) Lecture",
                    attendance: $controller.attendanceModel.lecture
                )
                
                AttendanceCardsView(
                    title: "\(self.controller.course.courseName) Tutorial",
                    attendance: $controller.attendanceModel.tutorial
                )
                
                AttendanceCardsView(
                    title: "\(self.controller.course.courseName) Lab",
                    attendance: $controller.attendanceModel.lab
                )
            }
            .padding()
            .scrollIndicators(.hidden)
            
            ErrorAndNotificationView(
                notificationMessage: $controller.errorAndNotificationController.popUpMessage,
                errorMessage: $controller.errorAndNotificationController.errorMessage,
                notificationVisible: $controller.errorAndNotificationController.showPopUp,
                errorVisible: $controller.errorAndNotificationController.showError
            )
            
            LoadingView(isLoading: $controller.isLoading)
        }
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItem(placement: .topBarLeading) {
                BackArrowView()
            }
            
            ToolbarItem(placement: .topBarTrailing) {
                UserSettingsIcon()
            }
            
            ToolbarItem(placement: .principal) {
                VStack {
                    Text("\(self.controller.student.fullName)'s")
                        .font(.title2)
                        .fontWeight(.bold)
                        .foregroundStyle(Color.mySecondary)
                    
                    Text("Attendance for \(self.controller.course.courseName)")
                        .font(.subheadline)
                        .fontWeight(.medium )
                        .foregroundStyle(Color.mySecondary)
                        .offset(y: -2)
                }
            }
        }
        .navigationTitle("")
        .navigationBarTitleDisplayMode(.inline)
        .toolbarBackgroundVisibility(.visible, for: .navigationBar)
        .toolbarBackground(Color.surfaceBright, for: .navigationBar)
        .navigationBarBackButtonHidden(true)
        .onAppear() {
            Task {
                await self.controller.loadView()
            }
        }
    }
}

#Preview {
    NavigationStack {
        Student_Attendance_View(
            student: .init(
                email: "EmreArapcicUevak@gmail.com",
                id: 10,
                fullName: "Emre Arapcic-Uevak"
            ),
            course: .init(
                courseName: "Vector Calculus",
                courseFaculty: "FENS",
                courseCode: "MATH202",
                hasTutorial: true,
                hasLab: true,
                courseId: 999
            )
        )
    }
}
