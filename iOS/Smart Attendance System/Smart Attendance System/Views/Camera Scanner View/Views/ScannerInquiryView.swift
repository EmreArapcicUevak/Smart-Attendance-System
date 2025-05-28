//
//  ScannerInquiryView.swift
//  Smart Attendance System
//
//  Created by Emre Arapcic-Uevak on 25. 5. 2025..
//

import SwiftUI

struct ScannerInquiryView: View {
    @Binding var selectedWeek: Double
    @Binding var selectedComponent: String
    var buttonPressFunc: () -> Void
    
    init(selectedWeek: Binding<Double>, selectedComponent: Binding<String>, activationFunc : @escaping () -> Void) {
        self._selectedWeek = selectedWeek
        self._selectedComponent = selectedComponent
        self.buttonPressFunc = activationFunc
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            
            Text("Selected Week \(Int(selectedWeek))")
                .padding(.bottom)
            VStack {
                Slider(value: $selectedWeek, in: 1...15, step: 1) {
                    Text("Hello")
                }
                

                  HStack {
                      ForEach(1...15, id: \.self) { week in
                          Text("\(week)")
                              .font(.caption2)
                              .frame(maxWidth: .infinity)
                      }
                  }.padding(.top, 5)
            }
            .padding(.bottom)
            
            Divider()
                .padding(.vertical)
            
            Text("Selected Component:")
                .padding(.bottom)
            
            Picker("Select Components:", selection: $selectedComponent) {
                Text("Lecture").tag(Constants.component_lecture)
                Text("Tutorials").tag(Constants.component_tutorial)
                Text("Lab").tag(Constants.component_lab)
            }
            .pickerStyle(.segmented)
            
            PrimaryButtonView(
                text: "Scan",
                isEnable: .constant(true),
                action: self.buttonPressFunc
            )
            .padding()
        }
        .padding()
        .background(
            RoundedRectangleWithOutlineView(fillColor: Color.surfaceContainer)
        )
    }
}

#Preview {
    @Previewable @State var bindWeek : Double = 1
    @Previewable @State var bindComponent: String = Constants.component_lecture

    ZStack {
        Color
            .surface
            .ignoresSafeArea()
        
        
        ScannerInquiryView(
            selectedWeek: $bindWeek,
            selectedComponent: $bindComponent
        ) {
                print("Hi")
        }
        .padding()
    }
}

extension Constants {
    static let component_lecture: String = "LECTURE"
    static let component_lab: String = "LAB"
    static let component_tutorial: String = "TUTORIAL"
}
