//
//  LoadingView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 21. 5. 2025..
//

import SwiftUI

struct LoadingView: View {
    @Binding var isLoading: Bool
    var body: some View {
        if isLoading {
            ZStack {
                BlurView()
                
                ProgressView()
            }
        }
    }
}

#Preview {
    @Previewable @State var isLoading: Bool = true
    
    ZStack {
        Color
            .surface
            .ignoresSafeArea()
        
        VStack {
            Text("Hello")
            Text("Hello")
            Text("Hello")
            Text("Hello")
        }
        LoadingView(isLoading: $isLoading);
    }
}
