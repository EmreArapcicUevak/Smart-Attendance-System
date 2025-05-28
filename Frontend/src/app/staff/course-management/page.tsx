"use client";
import { useRouter } from "next/navigation";
import React, { useState, useEffect } from "react";
import ClassSettings from "../../../../components/ClassSettings";

interface CourseComponent {
  id: number;
  courseCode: string;
  courseName: string;
  students?: string[];
}

const ITEMS_PER_PAGE = 5;

export default function CourseManagement() {
  const [currentPage, setCurrentPage] = useState(1);
  const [courses, setCourses] = useState<CourseComponent[]>([]);
  const [isSettingsOpen, setIsSettingsOpen] = useState(false);
  const [selectedCourse, setSelectedCourse] = useState<CourseComponent | null>(
    null
  );
  const router = useRouter();

  useEffect(() => {
    const userId = localStorage.getItem("userId");
    fetch(`http://localhost:8080/api/courses/staff/${userId}`)
      .then((res) => res.json())
      .then((data) => {
        console.log("API Response:", data); // Debug API response
        const mappedCourses = data.map((course: any) => ({
          id: course.id,
          courseName: course.courseName,
          courseCode: course.courseCode,
        }));
        console.log("Mapped Courses:", mappedCourses); // Debug mapped courses
        setCourses(mappedCourses);
      })
      .catch((error) => {
        console.error("Failed to fetch courses:", error);
        setCourses([]);
      });
  }, []);

  const totalPages = Math.ceil(courses.length / ITEMS_PER_PAGE);
  const currentCourses = courses.slice(
    (currentPage - 1) * ITEMS_PER_PAGE,
    currentPage * ITEMS_PER_PAGE
  );

  const handleOpenSettings = (course: CourseComponent) => {
    setSelectedCourse(course);
    setIsSettingsOpen(true);
  };

  const handleSaveCourse = (updatedCourse: CourseComponent) => {
    setCourses((prev) =>
      prev.map((course) =>
        course.id === updatedCourse.id ? updatedCourse : course
      )
    );
    setIsSettingsOpen(false);
  };

  const handleDeleteCourse = (id: number) => {
    setCourses(courses.filter((course) => course.id !== id));
  };

  return (
    <div className="min-h-screen p-8 bg-[#EFF1FA] text-black">
      <div className="max-w-5xl mx-auto bg-white p-6 rounded-lg shadow-md">
        <h2 className="text-3xl font-semibold text-[#3553B5] mb-6">
          Course Management
        </h2>

        <button
          onClick={() => router.push("/staff/dashboard")}
          className="mb-4 px-4 py-2 bg-[#3553B5] text-white rounded hover:bg-blue-700"
        >
          ← Back to Dashboard
        </button>

        <table className="min-w-full bg-white text-left border border-gray-300 rounded-lg shadow-md">
          <thead className="bg-blue-100 text-blue-800 font-semibold">
            <tr>
              <th className="px-4 py-2 border-b">Code</th>
              <th className="px-4 py-2 border-b">Name</th>
              <th className="px-4 py-2 border-b">Actions</th>
            </tr>
          </thead>
          <tbody>
            {currentCourses.length > 0 ? (
              currentCourses.map((course, index) => (
                <tr
                  key={course.id}
                  className={`hover:bg-gray-100 ${
                    index % 2 === 0 ? "bg-gray-50" : "bg-white"
                  }`}
                >
                  <td className="px-4 py-3 border-b">{course.courseCode}</td>
                  <td className="px-4 py-3 border-b">{course.courseName}</td>
                  <td className="px-4 py-3 border-b">
                    <div className="flex gap-4">
                      <button
                        onClick={() => handleOpenSettings(course)}
                        className="px-3 py-1 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition"
                        title="Edit course settings"
                      >
                        Settings
                      </button>
                      <button
                        onClick={() => handleDeleteCourse(course.id)}
                        className="px-3 py-1 bg-red-500 text-white rounded-md hover:bg-red-600 transition"
                        title="Delete this course"
                      >
                        Delete
                      </button>
                    </div>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td
                  colSpan={3}
                  className="px-4 py-3 text-center text-gray-500 border-b"
                >
                  No courses available.
                </td>
              </tr>
            )}
          </tbody>
        </table>

        <div className="flex justify-between items-center mt-4">
          <button
            onClick={() => setCurrentPage((prev) => Math.max(prev - 1, 1))}
            className="px-4 py-2 bg-gray-200 text-black rounded-md hover:bg-gray-300"
            disabled={currentPage === 1}
          >
            Previous
          </button>
          <p className="text-gray-700">
            Page {currentPage} of {totalPages}
          </p>
          <button
            onClick={() =>
              setCurrentPage((prev) => Math.min(prev + 1, totalPages))
            }
            className="px-4 py-2 bg-gray-200 text-black rounded-md hover:bg-gray-300"
            disabled={currentPage === totalPages}
          >
            Next
          </button>
        </div>
      </div>

      {isSettingsOpen && selectedCourse && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-3xl">
            <h3 className="text-xl font-semibold mb-4">
              Class Settings: {selectedCourse.courseName}
            </h3>
            <ClassSettings
              course={selectedCourse}
              onDelete={() => handleDeleteCourse(selectedCourse.id)}
              onSave={handleSaveCourse}
            />
            <div className="flex justify-end gap-2 mt-4">
              <button
                onClick={() => setIsSettingsOpen(false)}
                className="px-4 py-2 bg-gray-300 text-black rounded-md hover:bg-gray-400"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
