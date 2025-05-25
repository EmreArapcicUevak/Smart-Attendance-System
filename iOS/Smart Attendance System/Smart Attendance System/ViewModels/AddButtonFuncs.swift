//
//  AddDashboardFunc.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import Foundation
import SwiftUI

func addCreateCourseView() {
    print("Finish adding course")
    SessionExpirationManager.shared.path?.wrappedValue.append(CreateCourseRouteModel.createCourse)
    //path.wrappedValue.append(UserModel()) // Change later!!!
}
