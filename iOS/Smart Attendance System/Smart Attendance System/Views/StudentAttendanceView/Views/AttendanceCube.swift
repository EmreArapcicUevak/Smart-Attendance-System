//
//  AttendanceCube.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 24. 5. 2025..
//

import SwiftUI

struct AttendanceCube: View {
    let number : Int
    let status : String
    
    var containerColor : Color {
        if status == Constants.attendance_status_missed {
            return Color.errorContainer
        } else if status == Constants.attendance_status_attended {
            return Color.green
        } else {
            return Color.surfaceContainerHigh
        }
    }
    
    var textColor : Color {
        if status == Constants.attendance_status_missed {
            return Color.white
        } else if status == Constants.attendance_status_attended {
            return Color.white
        } else {
            return Color.onSurfaceVariant
        }
    }
    
    
    var body: some View {
        Text(number.description)
            .foregroundStyle(textColor)
            .font(.title3)
            .fontWeight(.bold)
            .padding()
            .background(
                RoundedRectangleWithOutlineView(fillColor: containerColor, cornerRadius: 10)
                    .frame(width: 50,height: 50)
                    
            )
    }
}

#Preview {
    AttendanceCube(
        number: 1,
        status: Constants.attendance_status_not_marked
    )
}
