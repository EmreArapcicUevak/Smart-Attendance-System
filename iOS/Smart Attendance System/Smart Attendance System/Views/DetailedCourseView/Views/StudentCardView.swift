//
//  StudentCardView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct StudentCardView: View {
    var student: StudentModel
    
    var body: some View {
        VStack {
            HStack {
                // Name and ID
                VStack(alignment: .leading) {
                    Text(student.name)
                        .font(.title3)
                        .fontWeight(.semibold)
                        .foregroundStyle(Color.myPrimary)
                    
                    Text(student.student_id)
                        .font(.subheadline)
                        .foregroundStyle(Color.mySecondary)
                }
                
                Spacer()
                
                Image(systemName: "person.crop.square")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(maxWidth: 40)
                    .foregroundStyle(Color.mySecondary)
                    
            }
            
            Divider()
                .padding(.vertical)
                .foregroundStyle(Color.mySecondary)
            
            // Add attendance here!
        }
        .padding()
        .background(
            RoundedRectangleWithOutlineView(fillColor: Color.surfContainer)
        )
    }
}

#Preview {
    StudentCardView(
        student: .init(
            major: "CSE",
            firstName: "Emre",
            secondName: "Arapcic-Uevak",
            email: "EmreArapcicUevak@gmail.com",
            student_id: "220302289"
        )
    )
    .padding()
}
