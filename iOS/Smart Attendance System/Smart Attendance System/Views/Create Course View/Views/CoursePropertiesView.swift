//
//  CoursePropertiesView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct CoursePropertiesView: View {
    @Binding var course : CourseModel
    var body: some View {
        VStack {
            CustomTextField(fieldText: "Course Name", text_field: $course.courseName)
                .padding(.bottom)

            HStack {
                CustomTextField(fieldText: "Course Code", text_field: $course.courseCode)
                
                CustomTextField(fieldText: "Course Faculty", text_field: $course.courseFaculty)
            }
                .padding(.bottom)

            
            CustomToggleView(
                labelText: "Do you want tutorials?",
                toggleValue: $course.hasTutorial
            )
            
            CustomToggleView(
                labelText: "Do you want labs?",
                toggleValue: $course.hasLab
            )
        }
        .padding()
        .background(
            RoundedRectangleWithOutlineView(fillColor: Color.surfaceContainer, cornerRadius: 10)
        )
    }
}

#Preview {
    @Previewable @State var course : CourseModel = .init(
        courseName: "Vector Calculus",
        courseFaculty: "FENS",
        courseCode: "MATH 202",
        hasTutorial: true,
        hasLab: true,
        courseId: 10
    )
    
    ZStack {
        Color
            .surface
            .ignoresSafeArea()
        
        CoursePropertiesView(course: $course)
            .padding()
    }
}
