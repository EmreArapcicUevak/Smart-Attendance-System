//
//  CourseCardView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct CourseCardView: View {
    let courseModel: CourseModel
    let isForStudent: Bool
    
    init(courseModel: CourseModel, isForStudent : Bool = false) {
        self.courseModel = courseModel
        self.isForStudent = isForStudent
    }
    
    var body: some View {
        NavigationLink(value: courseModel) {
            VStack() {
                HStack() {
                    Text(courseModel.courseName)
                        .foregroundStyle(Color.onSurface)
                        .font(.headline)
                        .fontWeight(.semibold)
                    
                    
                    Spacer()
                    
                    if (isForStudent == false) {
                        NavigationLink(value: CourseEditRoute(course: courseModel)) {
                            Image(systemName: "gearshape.fill")
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .frame(width: 30, height: 30)
                                .foregroundStyle(.myPrimary)
                        }
                    }
                }.padding()
                
                HStack {
                    Text(courseModel.courseCode)
                        .foregroundStyle(Color.onSurfaceVariant)
                        .font(.system(size: 14, weight: .regular))
                    
                    Spacer()
                    
                    Text(courseModel.courseFaculty)
                        .foregroundStyle(Color.onSurfaceVariant)
                        .font(.system(size: 14, weight: .regular))
                }
                .padding()
            }
            .background(
                RoundedRectangleWithOutlineView(
                    fillColor : Color.surfaceContainer,
                    lineWidth: 0.5
                )
            )
            .shadow(radius: 5)
        }
    }
}

#Preview {
    CourseCardView(
        courseModel: .init(
            courseName: "Computer Science",
            courseFaculty: "FENS",
            courseCode: "CS101",
            hasTutorial: true,
            hasLab: true,
            courseId: 2
        )
    )
}
