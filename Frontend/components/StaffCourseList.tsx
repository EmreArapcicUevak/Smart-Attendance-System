'use client';

import { useState } from 'react';

type Course = {
  id: number;
  code: string;
  name: string;
  faculty: string;
};

// Mock data with new structure
const mockCourses: Course[] = [
  {
    id: 1,
    code: 'CS101',
    name: 'Software Engineering',
    faculty: 'Faculty of Engineering and Natural Sciences',
  }
];

export default function StaffCourseList() {
  const [courses] = useState<Course[]>(mockCourses);

  return (
    <section className="mt-10">
      <h2 className="text-2xl font-bold mb-4">My Courses</h2>

      <div className="min-w-full bg-white text-left text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
        <div className="w-full overflow-x-auto">
          <table className="w-full bg-white text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
            <thead className="bg-[#D9D9D9] text-black">
              <tr>
                <th className="px-6 py-3">Course Code</th>
                <th className="px-6 py-3">Course Name</th>
                <th className="px-6 py-3">Faculty</th>
              </tr>
            </thead>
            <tbody>
              {courses.length ? (
                courses.map((course) => (
                  <tr
                    key={course.id}
                    className="hover:bg-[#EFF1FA] border-t border-gray-200"
                  >
                    <td className="px-6 py-4">{course.code}</td>
                    <td className="px-6 py-4">{course.name}</td>
                    <td className="px-6 py-4">{course.faculty}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan={3} className="py-6 text-center text-gray-500">
                    No courses available.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </section>
  );
}
