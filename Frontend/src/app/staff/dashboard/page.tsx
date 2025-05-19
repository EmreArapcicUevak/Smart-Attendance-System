'use client';

import { useRouter } from 'next/navigation';
import { useState } from 'react';
import StaffCourseList from '../../../../components/StaffCourseList';

export default function StaffDashboard() {
  const router = useRouter();
  const [userName] = useState('John Doe');
  const [searchQuery, setSearchQuery] = useState('');
  const [filter, setFilter] = useState('All');

  const attendanceData = [
    { date: '2025-04-01', status: 'Present', in: '08:00', out: '17:00' },
    { date: '2025-03-31', status: 'Absent', in: '-', out: '-' },
    { date: '2025-03-30', status: 'Late', in: '08:45', out: '17:00' },
  ];

  const filteredData = attendanceData.filter((entry) => {
    const matchesSearch = entry.date.includes(searchQuery);
    const matchesFilter = filter === 'All' || entry.status === filter;
    return matchesSearch && matchesFilter;
  });

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

        {/* Stats */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
          <div className="bg-[#D9D9D9] rounded-xl p-6 shadow-sm">
            <h3 className="text-xl font-semibold text-black mb-2">Total Days</h3>
            <p className="text-3xl font-bold text-[#3553B5]">180</p>
          </div>
          <div className="bg-[#D9D9D9] rounded-xl p-6 shadow-sm">
            <h3 className="text-xl font-semibold text-black mb-2">Present</h3>
            <p className="text-3xl font-bold text-[#3553B5]">172</p>
          </div>
          <div className="bg-[#D9D9D9] rounded-xl p-6 shadow-sm">
            <h3 className="text-xl font-semibold text-black mb-2">Absent</h3>
            <p className="text-3xl font-bold text-[#3553B5]">8</p>
          </div>
        </div>

        {/* Search & Filter */}
        <div className="flex flex-wrap items-center gap-4 mb-6">
          <input
            type="text"
            placeholder="Search by date..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="border border-gray-300 rounded-lg px-4 py-2 text-sm text-black"
          />
          <select
            value={filter}
            onChange={(e) => setFilter(e.target.value)}
            className="border border-gray-300 rounded-lg px-4 py-2 text-sm text-black"
          >
            <option value="All">All</option>
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
            <option value="Late">Late</option>
          </select>
        </div>

        {/* Attendance Table */}
        <div>
          <h2 className="text-2xl font-bold mb-4">Recent Attendance</h2>
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white text-left text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
              <thead className="bg-[#D9D9D9] text-black">
                <tr>
                  <th className="px-6 py-3">Date</th>
                  <th className="px-6 py-3">Status</th>
                  <th className="px-6 py-3">Check-In</th>
                  <th className="px-6 py-3">Check-Out</th>
                </tr>
              </thead>
              <tbody>
                {filteredData.map((entry, idx) => (
                  <tr
                    key={idx}
                    className="hover:bg-[#EFF1FA] border-t border-gray-200"
                  >
                    <td className="px-6 py-4">{entry.date}</td>
                    <td
                      className={`px-6 py-4 font-medium ${
                        entry.status === 'Present'
                          ? 'text-green-600'
                          : entry.status === 'Absent'
                          ? 'text-red-500'
                          : 'text-yellow-600'
                      }`}
                    >
                      {entry.status}
                    </td>
                    <td className="px-6 py-4">{entry.in}</td>
                    <td className="px-6 py-4">{entry.out}</td>
                  </tr>
                ))}
              </tbody>
            </table>
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
