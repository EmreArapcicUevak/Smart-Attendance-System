//
//  DetailedCourseView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct DetailedCourseView: View {
    let students : [StudentModel] = [
        StudentModel(major: "Computer Science", firstName: "Amar", secondName: "Hodžić", email: "amar.hodzic@university.edu", student_id: "20230001"),
        StudentModel(major: "Software Engineering", firstName: "Lejla", secondName: "Mujkić", email: "lejla.mujkic@university.edu", student_id: "20230002"),
        StudentModel(major: "Electrical Engineering", firstName: "Faruk", secondName: "Delić", email: "faruk.delic@university.edu", student_id: "20230003"),
        StudentModel(major: "Mechanical Engineering", firstName: "Sara", secondName: "Alić", email: "sara.alic@university.edu", student_id: "20230004"),
        StudentModel(major: "Information Systems", firstName: "Emir", secondName: "Kovačević", email: "emir.kovacevic@university.edu", student_id: "20230005"),
        StudentModel(major: "Computer Science", firstName: "Ajla", secondName: "Begović", email: "ajla.begovic@university.edu", student_id: "20230006"),
        StudentModel(major: "Software Engineering", firstName: "Haris", secondName: "Zukić", email: "haris.zukic@university.edu", student_id: "20230007"),
        StudentModel(major: "Electrical Engineering", firstName: "Maja", secondName: "Tomić", email: "maja.tomic@university.edu", student_id: "20230008"),
        StudentModel(major: "Mechanical Engineering", firstName: "Dino", secondName: "Sarić", email: "dino.saric@university.edu", student_id: "20230009"),
        StudentModel(major: "Information Systems", firstName: "Nina", secondName: "Jurić", email: "nina.juric@university.edu", student_id: "20230010")
    ]
    
    @State var filterText : String = ""
    @Binding var path : NavigationPath
    let course : CourseModel
    
    var body: some View {
        ZStack {
            Color.surface
                .ignoresSafeArea()
            
            VStack {
                SearchBarView(searchBarText: $filterText, placeholder: "Filter Student")
                    .padding()
                
                ScrollView {
                    ForEach(studentFilter(students, filterText)) { student in
                        StudentCardView(student: student)
                            .padding()
                    }
                }
            }
        }
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItem(placement: .topBarLeading) {
                BackArrowView(path: $path)
            }
            
            ToolbarItem(placement: .topBarTrailing) {
                UserSettingsIcon(path: $path)
            }
            
            ToolbarItem(placement: .principal) {
                Text("\(course.courseCode) Details")
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
    }
}

#Preview {
    @Previewable @State var path = NavigationPath()
    NavigationStack(path: $path) {
        DetailedCourseView(
            path: $path, course: .init(
                courseName: "Introduction To Computer Science",
                courseFaculty: "FENS",
                courseCode: "CS101"
            )
        )
    }
}
