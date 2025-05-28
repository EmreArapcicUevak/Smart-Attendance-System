//
//  AttendanceCardsView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct AttendanceCardsView: View {
    let title: String
    @Binding var attendance : [String]?
    
    
    var body: some View {
        if let attendance = attendance {
            VStack {
                HStack {
                    Spacer()
                    
                    Text(title)
                        .font(.title3)
                        .fontWeight(.semibold)
                        .foregroundStyle(Color.myPrimary)
                    
                    Spacer()
                }
                
                Grid {
                    ForEach(1...3, id: \.self) { row in
                        GridRow {
                            ForEach(1...5, id: \.self) { collumn in
                                let num = (row - 1) * 5 + collumn
                                AttendanceCube(number: num, status: attendance[num - 1])
                            }
                        }
                    }
                }
            }
            .padding()
            .background(
                RoundedRectangleWithOutlineView(fillColor: Color.surfaceContainer)
            )
        }
    }
}


#Preview {
    @Previewable @State var attendance : [String]? =  [
        Constants.attendance_status_attended,
        Constants.attendance_status_missed,
        Constants.attendance_status_attended,
        Constants.attendance_status_attended,
        Constants.attendance_status_attended,
        Constants.attendance_status_not_marked,
        Constants.attendance_status_attended,
        Constants.attendance_status_missed,
        Constants.attendance_status_attended,
        Constants.attendance_status_attended,
        Constants.attendance_status_not_marked,
        Constants.attendance_status_missed,
        Constants.attendance_status_attended,
        Constants.attendance_status_attended,
        Constants.attendance_status_not_marked
    ]
    
    AttendanceCardsView(
        title: "Vector Calculus Lecture",
        attendance: $attendance
    )
}


