//
//  StaffDashboardToolBarView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct StaffDashboardToolBarView: View {
    @Binding var path: NavigationPath
    var body: some View {
        HStack(spacing: 20) {
            Button {
                print("Open view for adding more courses")
            } label: {
                Image(systemName: "plus")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(maxWidth: 20, maxHeight: 20)
                    .foregroundStyle(Color.mySecondary)
            }
            
            UserSettingsIcon(path: $path)
        }
    }
}

#Preview {
    @Previewable @State var path = NavigationPath()
    StaffDashboardToolBarView(path: $path )
}
