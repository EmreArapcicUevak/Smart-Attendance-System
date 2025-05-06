//
//  StaffDashboard.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct StaffDashboard: View {
    @Binding var path: NavigationPath
    let courses: [CourseModel] = [
        .init(
            courseName: "Introduction to Computer Science",
            courseFaculty: "FENS",
            courseCode: "CS101"
        ),
        .init(
            courseName: "Psychology",
            courseFaculty: "FASS",
            courseCode: "PSY101"
        ),
        .init(
            courseName: "Business Managment",
            courseFaculty: "FAP",
            courseCode: "BUS201"
        ),
        .init(
            courseName: "Academic Writing",
            courseFaculty: "ELS",
            courseCode: "ENG102"
        ),
        .init(
            courseName: "Data Structures",
            courseFaculty: "FENS",
            courseCode: "CS202"
        )
    ]
    
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            VStack {
                
                ScrollView() {
                    ForEach(courses) { course in
                        NavigationLink(value: course) {
                            CourseCardView(courseModel: course)
                                .padding()
                        }
                    }
                }
                .scenePadding(.bottom)
            }
            
        }
        .navigationDestination(for: CourseModel.self, destination: { courseModel in
            DetailedCourseView(path: $path, course: courseModel)
        })
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItemGroup(placement: .topBarTrailing) {
                AddButtonView(
                    path: $path,
                    triggerFunction: addCreateCourseView
                )
                
                UserSettingsIcon(path: $path)
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
    }
    
}

#Preview {
    @Previewable @State var path = NavigationPath()
    NavigationStack(path: $path) {
        StaffDashboard(path: $path)
    }
}
