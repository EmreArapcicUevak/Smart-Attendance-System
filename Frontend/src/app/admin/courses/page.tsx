'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation'; 
interface Course {
  code: string;
  name: string;
  faculty: string;
}

export default function ViewCoursesPage() {
   const router = useRouter();
  const [courses, setCourses] = useState<Course[]>([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetch('/api/courses')
      .then(res => res.json())
      .then(setCourses)
      .catch(() => {
        setCourses([
        ]);
      });
  }, []);

  const filteredCourses = courses.filter(course =>
    course.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-10 text-black font-sans">
      
      <h1 className="text-4xl font-bold text-[#3553B5] mb-6">📘 Course List</h1>
    <div className="flex justify-end mb-6">
      <button
          onClick={() => router.push('/admin/dashboard')}
          className="mb-6 px-4 py-2 bg-[#3553B5] text-white rounded hover:bg-blue-700"
        >
          ← Back to Dashboard
        </button>
      </div>
      {/* Search Filter */}
      <div className="mb-6">
        <label htmlFor="search" className="block mb-2 font-medium text-gray-700">
          Search by Course Name
        </label>
        <input
          id="search"
          type="text"
          placeholder="e.g., Marketing"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="w-full sm:w-64 px-4 py-2 border border-gray-300 rounded-lg text-sm bg-white"
        />
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
            No courses match your search.
          </div>
        )}
      </div>
    </div>
  );
}
