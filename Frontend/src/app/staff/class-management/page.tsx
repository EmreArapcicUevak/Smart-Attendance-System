'use client';

import { useRouter } from 'next/navigation';
import React, { useState, useEffect } from 'react';

export default function ClassManagementPage() {
  const router = useRouter();

  const [classes, setClasses] = useState<{ code: string; name: string; faculty: string }[]>([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/courses')
      .then((res) => res.json())
      .then((data) => {
        setClasses(data);
      })
      .catch((error) => {
        console.error('Failed to fetch classes:', error);
      });
  }, []);

  const slugify = (name: string) => name.toUpperCase().replace(/\s+/g, '-');

  return (
    <div className="min-h-screen flex bg-white text-black font-sans">
      {/* Sidebar */}
      <aside className="w-64 bg-[#3553B5] text-white p-6 flex flex-col gap-6">
        <h2 className="text-2xl font-bold">Smart Attendance</h2>
        <nav className="flex flex-col gap-4 text-lg">
          <button className="text-left hover:text-gray-200" onClick={() => router.push('/staff/dashboard')}>
            Back to Dashboard
          </button>
        </nav>
        <div className="mt-auto">
          <button
            onClick={() => {
              localStorage.removeItem('authToken');
              router.push('/login');
            }}
            className="text-sm bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition"
          >
            Logout
          </button>
        </div>
      </aside>

      {/* Main */}
      <main className="flex-1 p-10">
        <h1 className="text-3xl font-bold mb-6">My Classes</h1>

        {/* My Classes Table */}
        <div className="mb-10">
          <h2 className="text-2xl font-bold mb-4">Courses</h2>
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
              <thead className="bg-[#D9D9D9]">
                <tr>
                  <th className="px-6 py-3 text-left">Course Code</th>
                  <th className="px-6 py-3 text-left">Course Name</th>
                  <th className="px-6 py-3 text-left">Faculty</th>
                  <th className="px-6 py-3 text-left">Actions</th>
                </tr>
              </thead>
              <tbody>
                {classes.map((course, idx) => (
                  <tr key={idx} className="hover:bg-[#EFF1FA] border-t border-gray-200">
                    <td className="px-6 py-4">{course.code}</td>
                    <td className="px-6 py-4">{course.name}</td>
                    <td className="px-6 py-4">{course.faculty}</td>
                    <td className="px-6 py-4">
                      <button
                        className="bg-[#3553B5] text-white px-4 py-1 rounded hover:bg-blue-700 text-sm"
                        onClick={() => router.push(`/staff/class-management/class-details/${slugify(course.code)}`)}
                      >
                        Attendance Details
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </main>
    </div>
  );
}
