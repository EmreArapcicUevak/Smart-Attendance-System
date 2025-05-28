//
//  StudentFilter.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import Foundation

func studentFilter(_ students: [StudentModel], _ filter: String) -> [StudentModel] {
    let trimmedFilter = filter.trimmingCharacters(in: .whitespaces).lowercased()
    return students.filter { student in
        return filter == "" || student.fullName.lowercased().contains(trimmedFilter) || student.id.description.contains(trimmedFilter)
    }
}
