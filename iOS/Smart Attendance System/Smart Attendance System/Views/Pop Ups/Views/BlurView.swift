//
//  BlurView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 21. 5. 2025..
//

import SwiftUI

struct BlurView: View {
    @State var blur : Double = 0.95
    var body: some View {
        // Blur
        Rectangle()
            .fill(.ultraThinMaterial)
            .ignoresSafeArea()
            .opacity(0.95)
    }
    
    init(_ blur : Double = 0.95) {
        self.blur = blur
    }
}

#Preview {
    ZStack {
        Color
            .surface
            .ignoresSafeArea()
        
        Text("Some text")
        BlurView()
    }
}
