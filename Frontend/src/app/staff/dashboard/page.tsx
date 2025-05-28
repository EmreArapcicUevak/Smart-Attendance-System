'use client';

import { useRouter } from 'next/navigation';
import { useState } from 'react';
import StaffCourseList from '../../../../components/StaffCourseList';

export default function StaffDashboard() {
  const router = useRouter();
  const [userName] = useState('John Doe');

  return (
    <div className="min-h-screen flex bg-white text-black font-sans">
      {/* Sidebar */}
      <aside className="w-64 bg-[#3553B5] text-white p-6 flex flex-col gap-6">
        <h2 className="text-2xl font-bold">Smart Attendance</h2>
        <nav className="flex flex-col gap-4 text-lg">
          <button className="text-left hover:text-gray-200" onClick={() => router.push('/staff/class-management')}>
            Class Management
          </button>

          <button className="text-left hover:text-gray-200" onClick={() => router.push('/staff/course-management')}>
            Course Management
          </button>

          <button className="text-left hover:text-gray-200" onClick={() => router.push('/staff/create-course')}>
            Create Course
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

      {/* Main Content */}
      <main className="flex flex-col w-full p-10 bg-white">
        {/* Header */}
        <div className="flex flex-col md:flex-row md:items-center justify-between mb-8 bg-[#EFF1FA] p-6 rounded-lg shadow-sm">
          <div>
            <h1 className="text-3xl font-bold text-[#3553B5]">Welcome back, {userName} 👋</h1>
            <p className="text-sm text-gray-600 mt-1">Here's what's happening today in your dashboard.</p>
          </div>
          <div className="mt-4 md:mt-0">
            <span className="bg-[#3553B5] text-white text-sm px-4 py-2 rounded-lg shadow">
              Staff Dashboard
            </span>
          </div>
        </div>

        {/* My Courses - Staff Course List Component */}
        <StaffCourseList />
      </main>
    </div>
  );
}
