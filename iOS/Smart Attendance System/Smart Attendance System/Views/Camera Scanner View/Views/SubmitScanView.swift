//
//  SubmitScanView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 25. 5. 2025..
//

import SwiftUI

struct SubmitScanView: View {
    @State private var controller : SubmitScanViewModel
    
    init(scannedStudents: Binding<[StudentModel]>, selectedComponent: String, selectedWeek: Double, course: CourseModel) {
        controller = .init(
            scannedStudents: scannedStudents,
            selectedComponent: selectedComponent,
            selectedWeek: selectedWeek,
            course: course
        )
    }
    
    
    var body: some View {
        ZStack {
            
            VStack {
                ScrollView {
                    ForEach(controller.scannedStudents) { student in
                        StudentCardView(student: student.wrappedValue)
                            .padding(.bottom)
                    }
                }
                .scrollIndicators(.hidden)
                
                HStack{
                    PrimaryButtonView(
                        text: "Discard",
                        isEnable: .constant(true),
                        action: controller.discardScan,
                        textColor: .onErrorContainer,
                        foregroundActiveColor: .errorContainer
                    )
                    
                    PrimaryButtonView(
                        text: "Submit",
                        isEnable: .constant(true),
                        action: controller.submitScan
                    )
                }
                .padding(.bottom)
            }
            
            ErrorAndNotificationView(
                notificationMessage: $controller.errorAndNotificationController.popUpMessage,
                errorMessage: $controller.errorAndNotificationController.errorMessage,
                notificationVisible: $controller.errorAndNotificationController.showPopUp,
                errorVisible: $controller.errorAndNotificationController.showError
            )
            
            LoadingView(isLoading: $controller.isLoading)
        }
    }
}

#Preview {
    @Previewable @State var scannedStudents : [StudentModel] = [
        .init(
            email: "EmreArapcicUevak@gmail.com",
            id: 220302289,
            fullName: "Emre Arapcic-Uevak"
        ),
        .init(
            email: "VedadSiljic@gmail.com",
            id: 220302211,
            fullName: "Vedad Siljic"
        )]
    
    SubmitScanView(
        scannedStudents: $scannedStudents,
        selectedComponent: Constants.component_lecture,
        selectedWeek: 3.0,
        course: .init(
            courseName: "a",
            courseFaculty: "a",
            courseCode: "a",
            hasTutorial: true,
            hasLab: true,
            courseId: 2
        )
    )
    .padding()
}
