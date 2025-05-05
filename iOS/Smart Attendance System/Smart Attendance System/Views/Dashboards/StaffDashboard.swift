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
            
            
            Text("Staff Dashboard")
                .font(.title)
                .fontWeight(.bold)
                .foregroundStyle(Color.mySecondary)
                .frame(maxWidth: .infinity, maxHeight: .infinity,alignment: .topLeading)
                .padding(.leading)
            
            StaffDashboardToolBarView(path: $path)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topTrailing)
                .padding()
                .offset(y: -9)
                .padding(.trailing)
            
            ScrollView() {
                ForEach(courses) { course in
                    NavigationLink(value: course) {
                        CourseCardView(courseModel: course)
                            .padding()
                    }
                }
            }.offset(y: 50)
        }
        .navigationBarBackButtonHidden(true)
    }
    
}

#Preview {
    @Previewable @State var path = NavigationPath()
    StaffDashboard(path: $path)
}
