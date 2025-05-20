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
  const [editingIndex, setEditingIndex] = useState<number | null>(null);
  const [editData, setEditData] = useState<Course>({ code: '', name: '', faculty: '' });

  useEffect(() => {
    fetch('/api/courses')
      .then(res => res.json())
      .then(setCourses)
      .catch(() => {
        setCourses([
          { code: 'CS101', name: 'Intro to CS', faculty: 'Engineering' },
        
        ]);
      });
  }, []);

  const filteredCourses = courses.filter(course =>
    course.name.toLowerCase().includes(search.toLowerCase())
  );

  const startEditing = (index: number) => {
    setEditingIndex(index);
    setEditData(courses[index]);
  };

  const saveEdit = () => {
    if (editingIndex === null) return;
    const updatedCourses = [...courses];
    updatedCourses[editingIndex] = editData;
    setCourses(updatedCourses);
    setEditingIndex(null);
  };

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-6 md:p-10 text-black font-sans">
      <div className="max-w-7xl mx-auto">
  <div className="flex flex-col items-center text-center mb-8">
    <h1 className="text-4xl font-bold text-[#3553B5] mb-2">Organization Settings</h1>
    <p className="text-sm text-gray-600 mb-4">
      View and manage all courses in the organization.
    </p>
    <input
      type="text"
      placeholder="Search courses..."
      value={search}
      onChange={(e) => setSearch(e.target.value)}
      className="w-full md:w-1/2 border px-4 py-2 rounded shadow-sm bg-white"
    />
  </div>


        {/* Course Grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredCourses.map((course, idx) => (
            <div
              key={idx}
              className="bg-white rounded-xl shadow-md border p-5 transition hover:shadow-lg"
            >
              {editingIndex === idx ? (
                <>
                  <input
                    type="text"
                    value={editData.code}
                    onChange={(e) => setEditData({ ...editData, code: e.target.value })}
                    className="w-full mb-2 border px-3 py-2 rounded text-sm"
                    placeholder="Course Code"
                  />
                  <input
                    type="text"
                    value={editData.name}
                    onChange={(e) => setEditData({ ...editData, name: e.target.value })}
                    className="w-full mb-2 border px-3 py-2 rounded text-sm"
                    placeholder="Course Name"
                  />
                  <button
                    onClick={saveEdit}
                    className="text-sm text-green-600 hover:text-green-800 underline"
                  >
                    Save
                  </button>
                </>
              ) : (
                <>
                  <div className="mb-2">
                    <h3 className="text-xl font-bold text-[#3553B5]">{course.code}</h3>
                    <p className="text-md text-gray-800 font-medium">{course.name}</p>
                  </div>
                  <p className="text-sm text-gray-600 mb-3">Faculty: {course.faculty}</p>
                  <button
                    className="text-sm text-blue-600 hover:text-blue-800 underline"
                    onClick={() => startEditing(idx)}
                  >
                    Edit Settings
                  </button>
                </>
              )}
            </div>
          ))}
        </div>

        {filteredCourses.length === 0 && (
          <div className="text-center text-gray-500 text-sm mt-6">
            No courses match your search.
          </div>
        )}
      </div>
    </div>
  );
}
