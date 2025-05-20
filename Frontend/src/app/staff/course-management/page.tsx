'use client';
import { useRouter } from 'next/navigation';
import React, { useState } from 'react';
import ClassSettings from '../../../../components/ClassSettings';

interface Student {
  id: number;
  name: string;
}

interface CourseComponent {
  id: number;
  name: string;
  instructor: string;
  schedule: string;
  students: Student[];
  sessionSettings: string;
}

const mockCourses: CourseComponent[] = Array.from({ length: 10 }, (_, i) => ({
  id: i + 1,
  name: `Course ${i + 1}`,
  instructor: `Instructor ${i + 1}`,
  schedule: `Day ${((i % 7) + 1)} - ${(8 + (i % 5))}:00 - ${(9 + (i % 5))}:00`,
  students: [
   
  ],
  sessionSettings: 'Weekly - Room 101',
}));

const ITEMS_PER_PAGE = 5;

export default function CourseManagement() {
  const [currentPage, setCurrentPage] = useState(1);
  const [courses, setCourses] = useState<CourseComponent[]>(mockCourses);
  const [isSettingsOpen, setIsSettingsOpen] = useState(false);
  const [selectedCourse, setSelectedCourse] = useState<CourseComponent | null>(null);
  const router = useRouter();

  // Pagination logic
  const totalPages = Math.ceil(courses.length / ITEMS_PER_PAGE);
  const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
  const endIndex = startIndex + ITEMS_PER_PAGE;
  const currentCourses = courses.slice(startIndex, endIndex);

  // Open Settings Modal
  const handleOpenSettings = (course: CourseComponent) => {
    setSelectedCourse(course);
    setIsSettingsOpen(true);
  };

  // Save Updated Course
  const handleSaveCourse = (updatedCourse: CourseComponent) => {
    setCourses((prev) =>
      prev.map((course) => (course.id === updatedCourse.id ? updatedCourse : course))
    );
    setIsSettingsOpen(false);
  };

  // Delete Course
  const handleDeleteCourse = (id: number) => {
    setCourses(courses.filter((course) => course.id !== id));
  };

  return (
    <div className="min-h-screen p-8 bg-[#EFF1FA] text-black">
      <div className="max-w-5xl mx-auto bg-white p-6 rounded-lg shadow-md">
        <h2 className="text-3xl font-semibold text-[#3553B5] mb-6">Course Management</h2>

      <button
    onClick={() => router.push('/staff/dashboard')}
    className="mb-4 px-4 py-2 bg-[#3553B5] text-white rounded hover:bg-blue-700"
  >
    ← Back to Dashboard
  </button>

        {/* Course List */}
        <table className="min-w-full bg-white text-left border border-gray-300">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 border-b">Course Name</th>
              <th className="px-4 py-2 border-b">Instructor</th>
              <th className="px-4 py-2 border-b">Schedule</th>
              <th className="px-4 py-2 border-b">Actions</th>
            </tr>
          </thead>
          <tbody>
            {currentCourses.map((course) => (
              <tr key={course.id} className="hover:bg-gray-50">
                <td className="px-4 py-3 border-b">{course.name}</td>
                <td className="px-4 py-3 border-b">{course.instructor}</td>
                <td className="px-4 py-3 border-b">{course.schedule}</td>
                <td className="px-4 py-3 border-b">
                  
  <div className="flex gap-4">
    <button
      onClick={() => handleOpenSettings(course)}
      className="px-2 py-1 bg-blue-500 text-gray rounded-md hover:bg-blue-600"
    >
      Settings
    </button>

    <button
      onClick={() => handleDeleteCourse(course.id)}
      className="px-2 py-1 bg-red-500 text-white rounded-md hover:bg-red-600"
    >
      Delete
    </button>
  </div>
  
</td>

              </tr>
            ))}
          </tbody>
        </table>

        {/* Pagination */}
        <div className="flex justify-between items-center mt-4">
          <button
            onClick={() => setCurrentPage((prev) => Math.max(prev - 1, 1))}
            className="px-4 py-2 bg-gray-200 text-black rounded-md hover:bg-gray-300"
            disabled={currentPage === 1}
          >
            Previous
          </button>
          <p className="text-gray-700">Page {currentPage} of {totalPages}</p>
          <button
            onClick={() => setCurrentPage((prev) => Math.min(prev + 1, totalPages))}
            className="px-4 py-2 bg-gray-200 text-black rounded-md hover:bg-gray-300"
            disabled={currentPage === totalPages}
          >
            Next
          </button>
        </div>
      </div>

      {/* Class Settings Modal */}
      {isSettingsOpen && selectedCourse && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-3xl">
            <h3 className="text-xl font-semibold mb-4">Class Settings: {selectedCourse.name}</h3>
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
