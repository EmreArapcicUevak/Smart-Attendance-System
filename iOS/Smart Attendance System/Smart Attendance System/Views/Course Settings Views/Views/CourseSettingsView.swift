//
//  CourseSettingsView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct CourseSettingsView: View {
    @State private var titleText = "Edit Course"
    var course : CourseModel
    
    var body: some View {
        TabView {
            Tab("Course Properties", systemImage: "pencil.and.list.clipboard") {
                CoursePropertiesEditView(course: course)
            }
            
            Tab("Add/Remove Students", systemImage: "person.2.badge.gearshape.fill") {
                AddRemoveStudentsView(courseId: course.courseId)
            }
        }
        .tint(Color.myPrimary)
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItemGroup(placement: .topBarTrailing) {
                UserSettingsIcon()
            }
            
            ToolbarItem(placement: .principal) {
                Text("Edit Course")
                    .font(.title2)
                    .fontWeight(.bold)
                    .foregroundStyle(Color.mySecondary)
            }
            
            ToolbarItem(placement: .topBarLeading) {
                BackArrowView()
            }
        }
        .navigationTitle("")
        .navigationBarTitleDisplayMode(.inline)
        .toolbarBackground(Color.surfaceBright, for: .navigationBar)
        .toolbarBackgroundVisibility(.visible, for: .navigationBar)
    }
}

#Preview {
    @Previewable @State var course : CourseModel = .init(
        courseName: "Vector Calculus",
        courseFaculty: "FENS",
        courseCode: "MATH 202",
        hasTutorial: true,
        hasLab: true,
        courseId: 0
    )
    
    NavigationStack {
        CourseSettingsView(course: course)
    }
}
