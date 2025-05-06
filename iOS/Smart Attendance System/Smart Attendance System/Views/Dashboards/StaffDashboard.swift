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
                HStack {
                    Text("Staff Dashboard")
                        .font(.title)
                        .fontWeight(.bold)
                        .foregroundStyle(Color.mySecondary)
                        .padding(.leading)
                    
                    Spacer()
                    
                    StaffDashboardToolBarView(path: $path)
                        .padding()
                        .padding(.trailing)
                }
                .background(Color.surfaceBright)
                
                ScrollView() {
                    ForEach(courses) { course in
                        NavigationLink(value: course) {
                            CourseCardView(courseModel: course)
                                .padding()
                        }
                    }
                }
            }
            
        }
        .navigationBarBackButtonHidden(true)
    }
    
}

#Preview {
    @Previewable @State var path = NavigationPath()
    StaffDashboard(path: $path)
}
