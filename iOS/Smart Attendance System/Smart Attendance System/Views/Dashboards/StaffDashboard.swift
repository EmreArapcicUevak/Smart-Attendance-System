//
//  StaffDashboard.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct StaffDashboard: View {
    @Binding var path: NavigationPath
    
    var body: some View {
        ZStack {
            Color
                .surface
                .ignoresSafeArea()
            
            
            Text("Staff Dashboard")
                .font(.title)
                .fontWeight(.bold)
                .foregroundStyle(Color.secondary)
                .frame(maxWidth: .infinity, maxHeight: .infinity,alignment: .topLeading)
                .padding(.leading)
            
            StaffDashboardToolBarView(path: $path)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topTrailing)
                .padding()
                .offset(y: -9)
                .padding(.trailing)
        }
        .navigationBarBackButtonHidden(true)
    }
    
}

#Preview {
    @Previewable @State var path = NavigationPath()
    StaffDashboard(path: $path)
}
