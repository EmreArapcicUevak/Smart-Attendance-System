"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

interface Course {
  id: number; // Add id field to represent courseId
  courseCode: string;
  courseName: string;
  faculty: string;
}

export default function ClassManagementPage() {
  const router = useRouter();

  const [classes, setClasses] = useState<Course[]>([]);

  useEffect(() => {
    const userId = localStorage.getItem("userId");
    fetch(`http://localhost:8080/api/courses/staff/${userId}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("authToken") || ""}`,
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setClasses(data);
      })
      .catch((error) => {
        console.error("Failed to fetch classes:", error);
      });
  }, []);

  return (
    <div className="min-h-screen flex bg-white text-black font-sans">
      {/* Sidebar */}
      <aside className="w-64 bg-[#3553B5] text-white p-6 flex flex-col gap-6">
        <h2 className="text-2xl font-bold">Class Management</h2>
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-6">
        <h1 className="text-3xl font-bold mb-6">Your Classes</h1>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {classes.map((course) => (
            <div
              key={course.id}
              className="bg-white rounded-xl shadow-md border p-5 transition hover:shadow-lg"
            >
              <h3 className="text-xl font-bold text-[#3553B5]">
                {course.courseName}
              </h3>
              <p className="text-sm text-gray-600">Code: {course.courseCode}</p>
              <p className="text-sm text-gray-600">Faculty: {course.faculty}</p>
              <button
                onClick={() =>
                  router.push(
                    `/staff/class-management/class-details/${course.id}`
                  )
                }
                className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
              >
                View Attendance
              </button>
            </div>
          ))}
        </div>
      </main>
    </div>
  );
}
