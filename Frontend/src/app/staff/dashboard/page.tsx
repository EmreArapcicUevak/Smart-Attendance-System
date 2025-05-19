'use client';

import { useRouter } from 'next/navigation';
import { useState } from 'react';
import StaffCourseList from '../../../../components/StaffCourseList';

export default function StaffDashboard() {
  const router = useRouter();
  const [userName] = useState('John Doe');
 
  const upcomingClasses = [
    { subject: 'Math', date: '2025-04-06', time: '10:00 AM' },
    { subject: 'English', date: '2025-04-07', time: '11:00 AM' },
  ];

  const pastClasses = [
    { subject: 'Science', date: '2025-04-03', time: '9:00 AM' },
    { subject: 'History', date: '2025-04-02', time: '8:00 AM' },
  ];

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
    <button
  className="text-left hover:text-gray-200"
  onClick={() => router.push('/staff/time-intervals')}
>
  Add Time Intervals
</button>

    <button className="text-left hover:text-gray-200">Settings</button>
  </nav>
  <div className="mt-auto">
    <button
      onClick={() => router.push('/')}
      className="text-sm text-white underline hover:text-gray-200"
    >
      Logout
    </button>
  </div>
</aside>

      {/* Main Content */}
      <main className="flex-1 p-10 bg-white">
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
       
        {/* Upcoming & Past Classes */}
        <div className="mt-10 grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* Upcoming */}
          <div>
            <h2 className="text-2xl font-bold mb-4">Upcoming Classes</h2>
            <ul className="space-y-2">
              {upcomingClasses.map((cls, idx) => (
                <li
                  key={idx}
                  className="bg-[#D9D9D9] p-4 rounded-lg shadow-sm flex justify-between"
                >
                  <div>
                    <p className="font-semibold">{cls.subject}</p>
                    <p className="text-sm text-[#AAA6A6]">{cls.date}</p>
                  </div>
                  <p className="text-sm text-[#3553B5]">{cls.time}</p>
                </li>
              ))}
            </ul>
          </div>

          {/* Past */}
          <div>
            <h2 className="text-2xl font-bold mb-4">Past Classes</h2>
            <ul className="space-y-2">
              {pastClasses.map((cls, idx) => (
                <li
                  key={idx}
                  className="bg-[#D9D9D9] p-4 rounded-lg shadow-sm flex justify-between"
                >
                  <div>
                    <p className="font-semibold">{cls.subject}</p>
                    <p className="text-sm text-[#AAA6A6]">{cls.date}</p>
                  </div>
                  <p className="text-sm text-[#3553B5]">{cls.time}</p>
                </li>
              ))}
            </ul>
          </div>
      </div>

      {/* My Courses - Staff Course List Component */}
        <StaffCourseList />

      </main>
    </div>
  );
}
