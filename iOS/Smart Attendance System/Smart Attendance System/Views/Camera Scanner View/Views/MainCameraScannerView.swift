//
//  MainCameraScannerView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 25. 5. 2025..
//

import SwiftUI
import CodeScanner

struct MainCameraScannerView: View {
    @State private var controller : MainCameraScannerViewModel
    
    init (_ data: CameraRouteModel) { self.controller = .init(data) }
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            if controller.showSubmittingScreen {
                SubmitScanView(
                    scannedStudents: $controller.scannedStudents,
                    selectedComponent: controller.selectedComponent,
                    selectedWeek: controller.selectedWeek,
                    course: controller.course
                )
                .padding()
            } else {
                ScannerInquiryView(
                    selectedWeek: $controller.selectedWeek,
                    selectedComponent: $controller.selectedComponent,
                    activationFunc: controller.startScanning
                )
                .padding()
            }

            
            ErrorAndNotificationView(
                notificationMessage: $controller.errorAndNotificationController.popUpMessage,
                errorMessage: $controller.errorAndNotificationController.errorMessage,
                notificationVisible: $controller.errorAndNotificationController.showPopUp,
                errorVisible: $controller.errorAndNotificationController.showError
            )
        }
        .sheet(isPresented: $controller.showScanner) {
            CodeScannerView(
                codeTypes: [.code39],
                scanMode: .oncePerCode,
                showViewfinder: false,
                simulatedData: "220302289",
                completion: controller.handleScan
            )
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
                    Text("Attendance Scanner")
                        .font(.title2)
                        .fontWeight(.bold)
                        .foregroundStyle(Color.mySecondary)
                    
                    Text("For \(controller.course.courseName)")
                        .font(.subheadline)
                        .fontWeight(.light)
                        .foregroundStyle(Color.mySecondary)
                        .offset(y: -3)
                }
            }
        }
        .navigationTitle("")
        .navigationBarTitleDisplayMode(.inline)
        .toolbarBackgroundVisibility(.visible, for: .navigationBar)
        .toolbarBackground(Color.surfaceBright, for: .navigationBar)
        .navigationBarBackButtonHidden(true)
    }
}

#Preview {
    
    NavigationStack {
        MainCameraScannerView(
            .init(
                students: [
                    .init(
                        email: "EmreArapcicUevak@gmail.com",
                        id: 220302289,
                        fullName: "Emre Arapcic-Uevak"
                    ),
                    .init(
                        email: "VedadSiljic@gmail.com",
                        id: 220302211,
                        fullName: "Vedad Siljic"
                    )
                ],
                course: .init(
                    courseName: "Software Engineering",
                    courseFaculty: "FENS",
                    courseCode: "CS303",
                    hasTutorial: true,
                    hasLab: false,
                    courseId: 19
                )
            )
        )
    }
}
