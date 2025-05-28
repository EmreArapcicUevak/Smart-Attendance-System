//
//  DetailedCourseView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct DetailedCourseView: View {
    @State private var controller : DetailedCourseViewModel
    
    init(course : CourseModel) {
        self.controller = DetailedCourseViewModel(course: course)
    }
    
    @State var filterText : String = ""
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            VStack {
                SearchBarView(searchBarText: $filterText, placeholder: "Filter Student")
                    .padding()
                
                ScrollView {
                    ForEach(studentFilter(self.controller.students, filterText)) { student in
                        Button {
                            controller.studentCardPressed(for: student)
                        } label: {
                            StudentCardView(student: student)
                                .padding()
                        }

                    }
                }
            }
            
            FloatingCameraButtonView(action: self.controller.openScannerView)
            .frame(maxWidth: .infinity, maxHeight: .infinity,alignment: .bottomTrailing)
            .padding()
            .padding(.trailing)

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
                Text("\(self.controller.course.courseCode) Details")
                    .font(.title2)
                    .fontWeight(.bold)
                    .foregroundStyle(Color.mySecondary)
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
        .navigationDestination(for: AttendanceRouteModel.self) { routeModel in
            Student_Attendance_View(student: routeModel.student, course: routeModel.course)
        }
        .navigationDestination(for: CameraRouteModel.self) { cameraRouteModel in
            MainCameraScannerView(cameraRouteModel)
        }
    }
}

#Preview {
    @Previewable @State var path = NavigationPath()
    NavigationStack(path: $path) {
        DetailedCourseView(
            course: .init(
                courseName: "Introduction To Computer Science",
                courseFaculty: "FENS",
                courseCode: "CS101",
                hasTutorial: true,
                hasLab: true,
                courseId: 1
            )
        )
    }
}
