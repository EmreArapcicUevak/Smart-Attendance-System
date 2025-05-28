//
//  Login_View_Controller_Test.swift
//  Login View Controller Test
//
//  Created by Emre Arapcic-Uevak on 20. 5. 2025..
//

import Testing
@testable import Smart_Attendance_System

struct LoginViewControllerTest {
    let loginViewModel = LoginViewModel()

    @Test("Email Validation") func checkEmailValidation() async throws {
        let emailChecks: [(String, Bool)] = [
            ("test@example.com", true),
            ("invalid-email", false),
            ("user@domain.co.uk", true),
            ("@no-local-part.com", false),
            ("", false),
            (" ", false),
            ("user.name@domain.co.uk", true),
            ("user_name123@sub.domain.com", true),
            ("user+category@gmail.com", true),
            ("user-name@domain.io", true),
            ("first.last@iana.org", true),
            ("x@x.au", true),
            ("example-indeed@strange-example.com", true),
            ("plainaddress", false),                  // no @
            ("@missingusername.com", false),          // missing local-part
            ("username@", false),                     // missing domain
            ("username@.com", false),                 // domain starts with .
            ("username@domain..com", false),          // domain has double dot
            ("username@domain,com", false),           // comma instead of dot
            ("username@domain", false),               // missing TLD
            ("username@.com.", false),                // starts and ends with dot
            (".username@yahoo.com", false),           // starts with dot
            ("username@yahoo.com.", false),           // ends with dot
            ("user..name@example.com", false),        // double dot in local-part
            ("user.@example.com", false),             // dot at end of local-part
            ("user@-example.com", false),             // domain starts with hyphen
            ("user@exam_ple.com", false),             // underscore in domain
            ("user@.com.com", false),                 // domain starts with dot
            ("user@%*.com", false),                   // invalid characters in domain
            ("user@[192.168.0.1]", true),             // IP-literal domain (rare but valid)
            ("user@[300.300.300.300]", false),        // invalid IP
            ("user@[290.102.256.261]", false),        // invalid IP
            ("very.unusual.”@”.unusual.com@example.com", false), // malformed quoted strings
            ("user@@example.com", false),             // double @
            (" user@example.com", false),             // leading space
            ("user@example.com ", false)              // trailing space
        ]
        
        for (email, expectedValue) in emailChecks {
            #expect(
                self.loginViewModel.checkValidEmail(email) == expectedValue,
                "❌ Email validation failed for '\(email)' — expected \(expectedValue)"
            )
        }
    }
    
}
