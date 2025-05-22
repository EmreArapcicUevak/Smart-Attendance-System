//
//  SearchBar.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 6. 5. 2025..
//

import SwiftUI

struct SearchBarView: View {
    @Binding var searchBarText: String
    var placeholder: String
    let outlineColor: Color
    let fillColor: Color
    let cornerRadius: CGFloat
    let lineWidth: CGFloat
    
    init(
        searchBarText: Binding<String>,
        placeholder: String,
        outlineColor: Color = Color.outline,
        fillColor: Color = Color.surfContainer,
        cornerRadius: CGFloat = 5,
        lineWidth: CGFloat = 1
    ) {
        self._searchBarText = searchBarText
        self.placeholder = placeholder
        self.outlineColor = outlineColor
        self.fillColor = fillColor
        self.cornerRadius = cornerRadius
        self.lineWidth = lineWidth
    }
    var body: some View {
        HStack {
            TextField(placeholder, text: $searchBarText)
            Image(systemName: "magnifyingglass")
        }
        .padding()
        .background(
            RoundedRectangleWithOutlineView(
                outlineColor: outlineColor,
                fillColor: fillColor,
                cornerRadius: cornerRadius,
                lineWidth: lineWidth
            )
        )
    }
}

#Preview {
    SearchBarView(searchBarText: .constant(""), placeholder: "Filter by name")
        .padding()
}
