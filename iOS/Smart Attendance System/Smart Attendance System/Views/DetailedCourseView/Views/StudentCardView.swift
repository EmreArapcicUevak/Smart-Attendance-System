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
                    Text(student.fullName)
                        .font(.title3)
                        .fontWeight(.semibold)
                        .foregroundStyle(Color.myPrimary)
                    
                    Text(student.id.description)
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
        }
        .padding()
        .background(
            RoundedRectangleWithOutlineView(fillColor: Color.surfaceContainer)
        )
    }
}

#Preview {
    StudentCardView(
        student: .init(
            email: "EmreArapcicUevak@gmail.com",
            id: 220302289,
            fullName: "Emre Arapcic-Uevak"
        )
    )
    .padding()
}
