//
//  CourseInformationView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 23. 5. 2025..
//

import SwiftUI

struct CourseInformationView: View {
    @Binding var courseModel : CourseModel
    var body: some View {
        
        CustomTextField(
            fieldText: "Course Name",
            text_field: $courseModel.courseName
        )
    }
}

#Preview {
    @Previewable @State var courseModel : CourseModel = .init(
        courseName: "Computer Science",
        courseFaculty: "FENS",
        courseCode: "CS101",
        hasTutorial: true,
        hasLab: true,
        courseId: 2
    )
    
    ZStack {
        Color
            .surface
            .ignoresSafeArea()
        
        CourseInformationView(courseModel: $courseModel)
            .padding()
    }
}
