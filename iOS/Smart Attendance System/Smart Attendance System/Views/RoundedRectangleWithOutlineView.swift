//
//  RoundedRectangleWithOutlineView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 5. 5. 2025..
//

import SwiftUI

struct RoundedRectangleWithOutlineView: View {
    let outlineColor: Color
    let fillColor: Color
    let cornerRadius: CGFloat
    let lineWidth: CGFloat
    
    init(
        outlineColor: Color = Color.outline,
        fillColor: Color,
        cornerRadius: CGFloat = 5,
        lineWidth: CGFloat = 1
    ) {
        self.outlineColor = outlineColor
        self.fillColor = fillColor
        self.cornerRadius = cornerRadius
        self.lineWidth = lineWidth
    }
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 5)
                .fill(fillColor)
            
            RoundedRectangle(cornerRadius: 5)
                .stroke(outlineColor, lineWidth: lineWidth)
        }
    }
}

#Preview {
    RoundedRectangleWithOutlineView(fillColor: Color.errorContainer)
}
