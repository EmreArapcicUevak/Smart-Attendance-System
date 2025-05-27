"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

interface Course {
  id: number;
  courseName: string;
  courseCode: string;
}

export default function ViewCoursesPage() {
  const router = useRouter();
  const [courses, setCourses] = useState<Course[]>([]);
  const [searchTerm] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/courses")
      .then((res) => res.json())
      .then((data) => {
        console.log("API Response:", data); // Debug API response
        interface ApiCourse {
          id: number;
          courseCode: string;
          courseName: string;
        }

        const mappedCourses = data.map((c: ApiCourse) => ({
          id: c.id,
          courseCode: c.courseCode,
          courseName: c.courseName, // Ensure correct field names
        }));
        console.log("Mapped Courses:", mappedCourses); // Debug mapped courses
        setCourses(mappedCourses);
      })
      .catch((error) => {
        console.error("Failed to fetch courses:", error);
        setCourses([]);
      });
  }, []);

  const filteredCourses = courses.filter((course) =>
    course.courseName?.toLowerCase().includes(searchTerm.toLowerCase())
  );
  console.log("Filtered Courses:", filteredCourses); // Debug filtered courses

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-10 text-black font-sans">
      <h1 className="text-4xl font-bold text-[#3553B5] mb-6">📘 Course List</h1>

      <div className="flex justify-between items-center mb-6">
        <h1 className="text-4xl font-bold text-[#3553B5]">Courses</h1>
        <button
          onClick={() => router.push("/admin/dashboard")}
          className="px-4 py-2 bg-[#3553B5] text-white rounded hover:bg-blue-700"
        >
          Back to Dashboard
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredCourses.map((course) => (
          <div
            key={course.id} // Use `id` from the API response as the unique key
            className="bg-white rounded-xl shadow-md border p-5 transition hover:shadow-lg"
          >
            <h3 className="text-xl font-bold text-[#3553B5]">
              {course.courseName}
            </h3>
            <p className="text-sm text-gray-600">Code: {course.courseCode}</p>
          </div>
        ))}

        {filteredCourses.length === 0 && (
          <div className="text-gray-500 text-sm col-span-full">
            No courses match your search criteria.
          </div>
        )}
      </div>
    </div>
  );
}
