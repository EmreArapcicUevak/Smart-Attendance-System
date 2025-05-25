//
//  ManageSessionExpiration.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 23. 5. 2025..
//

import SwiftUI

@Observable
class SessionExpirationManager {
    static let shared : SessionExpirationManager = SessionExpirationManager() // Singleton
    init() { }
    
    var path : Binding<NavigationPath>? = nil
    var identityModel : IdentityModel? = nil
    var errorAndNotificationController : ErrorAndNotificationSimpleParamModel? = nil
    
    func popToRoot() {
        self.path?.wrappedValue.removeLast(self.path?.wrappedValue.count ?? 0)
    }
}
