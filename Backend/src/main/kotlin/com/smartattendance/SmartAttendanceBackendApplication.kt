package main.kotlin.com.smartattendance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SmartAttendanceBackendApplication

fun main(args: Array<String>) {
    runApplication<SmartAttendanceBackendApplication>(*args)
} 
