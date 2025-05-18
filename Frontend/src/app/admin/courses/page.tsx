'use client';

import { useEffect, useState } from 'react';

interface Course {
  code: string;
  name: string;
  faculty: string;
}

export default function ViewCoursesPage() {
  const [courses, setCourses] = useState<Course[]>([]);
  const [facultyFilter, setFacultyFilter] = useState<string>('All');

  useEffect(() => {
    fetch('/api/courses')
      .then(res => res.json())
      .then(setCourses)
      .catch(() => {
        setCourses([
          { code: 'CS101', name: 'Intro to CS', faculty: 'Engineering' },
          { code: 'BUS201', name: 'Marketing Basics', faculty: 'Business' },
          { code: 'MATH301', name: 'Linear Algebra', faculty: 'Science' },
          { code: 'HIST110', name: 'Modern History', faculty: 'Arts' },
        ]);
      });
  }, []);

  // Extract unique faculty names for the filter
  const facultyList = ['All', ...Array.from(new Set(courses.map(c => c.faculty)))];

  // Filter courses based on selected faculty
  const filteredCourses =
    facultyFilter === 'All'
      ? courses
      : courses.filter(course => course.faculty === facultyFilter);

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-10 text-black font-sans">
      <h1 className="text-4xl font-bold text-[#3553B5] mb-6">📘 Course List</h1>

      {/* Faculty Filter */}
      <div className="mb-6">
        <label htmlFor="facultyFilter" className="block mb-2 font-medium text-gray-700">
          Filter by Faculty
        </label>
        <select
          id="facultyFilter"
          value={facultyFilter}
          onChange={(e) => setFacultyFilter(e.target.value)}
          className="w-full sm:w-64 px-4 py-2 border border-gray-300 rounded-lg text-sm bg-white"
        >
          {facultyList.map((faculty, idx) => (
            <option key={idx} value={faculty}>
              {faculty}
            </option>
          ))}
        </select>
      </div>

      {/* Course Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredCourses.map((course, idx) => (
          <div
            key={idx}
            className="bg-white rounded-xl shadow-lg border border-gray-200 p-6 hover:shadow-xl transition-all duration-200"
          >
            <h3 className="text-2xl font-semibold text-[#3553B5] mb-1">{course.code}</h3>
            <p className="text-md font-medium text-gray-800">{course.name}</p>
            <p className="text-sm text-gray-600 mb-4">Faculty: {course.faculty}</p>
            
          </div>
        ))}

        {filteredCourses.length === 0 && (
          <div className="text-gray-500 text-sm col-span-full">
            No courses available for the selected faculty.
          </div>
        )}
      </div>
    </div>
  );
}
