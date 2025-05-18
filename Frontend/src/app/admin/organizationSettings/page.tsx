'use client';

import { useEffect, useState } from 'react';

interface Course {
  code: string;
  name: string;
  faculty: string;
}

export default function OrganizationSettings() {
  const [courses, setCourses] = useState<Course[]>([]);
  const [search, setSearch] = useState('');
  const [filter, setFilter] = useState('');
  const [newCourse, setNewCourse] = useState<Course>({ code: '', name: '', faculty: '' });

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

  const filteredCourses = courses
    .filter(course => course.name.toLowerCase().includes(search.toLowerCase()))
    .filter(course => !filter || course.faculty === filter);

  const handleAddCourse = () => {
    if (!newCourse.code || !newCourse.name || !newCourse.faculty) return;
    setCourses([...courses, newCourse]);
    setNewCourse({ code: '', name: '', faculty: '' });
  };

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-6 md:p-10 text-black font-sans">
      <div className="max-w-7xl mx-auto">
        <h1 className="text-4xl font-bold text-[#3553B5] mb-4 text-center">Organization Settings</h1>
        <p className="text-center text-sm text-gray-600 mb-8">
          View, filter, and manage all courses in the organization.
        </p>

        {/* Search and Filter Controls */}
        <div className="mb-8 flex flex-col md:flex-row md:items-center gap-4">
          <input
            type="text"
            placeholder="Search courses..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="flex-1 border px-4 py-2 rounded shadow-sm bg-white"
          />
          <select
            value={filter}
            onChange={(e) => setFilter(e.target.value)}
            className="w-full md:w-60 border px-4 py-2 rounded shadow-sm bg-white"
          >
            <option value="">All Faculties</option>
            {[...new Set(courses.map(course => course.faculty))].map((faculty, idx) => (
              <option key={idx} value={faculty}>{faculty}</option>
            ))}
          </select>
        </div>

        {/* Course Grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredCourses.map((course, idx) => (
            <div
              key={idx}
              className="bg-white rounded-xl shadow-md border p-5 transition hover:shadow-lg"
            >
              <div className="mb-2">
                <h3 className="text-xl font-bold text-[#3553B5]">{course.code}</h3>
                <p className="text-md text-gray-800 font-medium">{course.name}</p>
              </div>
              <p className="text-sm text-gray-600 mb-3">Faculty: {course.faculty}</p>
              <button className="text-sm text-blue-600 hover:text-blue-800 underline">
                Edit Settings
              </button>
            </div>
          ))}
        </div>

        {filteredCourses.length === 0 && (
          <div className="text-center text-gray-500 text-sm mt-6">
            No courses match your search or filter.
          </div>
        )}
      </div>
    </div>
  );
}
