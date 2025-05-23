'use client';

import { useRouter, useParams } from 'next/navigation';
import { useState } from 'react';

export default function AttendanceDetailsPage() {
  const router = useRouter();
  const params = useParams();
  const courseCode = params?.courseCode || 'UNKNOWN';

  const [searchId, setSearchId] = useState('');

  const attendanceData = [
    { id: 'S101', type: 'Lecture', week: 1, status: 'Present' },
    { id: 'S102', type: 'Lab', week: 1, status: 'Absent' },
    { id: 'S103', type: 'Tutorial', week: 2, status: 'Present' },
    { id: 'S101', type: 'Lecture', week: 2, status: 'Absent' },
  ];

  const filteredData = searchId
    ? attendanceData.filter((entry) => entry.id.includes(searchId))
    : attendanceData;

  return (
    <div className="min-h-screen flex bg-white text-black font-sans">
      <aside className="w-64 bg-[#3553B5] text-white p-6 flex flex-col gap-6">
        <h2 className="text-2xl font-bold">Smart Attendance</h2>
        <button className="text-left text-lg hover:text-gray-200" onClick={() => router.back()}>
          Back to Classes
        </button>
      </aside>

      <main className="flex-1 p-10">
        <h1 className="text-3xl font-bold mb-6">Attendance Details - {courseCode}</h1>

        <div className="mb-4">
          <input
            type="text"
            placeholder="Search by Student ID"
            className="border border-gray-300 px-4 py-2 rounded-md w-full max-w-sm"
            value={searchId}
            onChange={(e) => setSearchId(e.target.value)}
          />
        </div>

        <div className="overflow-x-auto">
          <table className="min-w-full bg-white text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
            <thead className="bg-[#D9D9D9]">
              <tr>
                <th className="px-6 py-3 text-left">Student ID</th>
                <th className="px-6 py-3 text-left">Course Type</th>
                <th className="px-6 py-3 text-left">Week</th>
                <th className="px-6 py-3 text-left">Status</th>
              </tr>
            </thead>
            <tbody>
              {filteredData.map((entry, idx) => (
                <tr key={idx} className="hover:bg-[#EFF1FA] border-t border-gray-200">
                  <td className="px-6 py-4">{entry.id}</td>
                  <td className="px-6 py-4">{entry.type}</td>
                  <td className="px-6 py-4">{entry.week}</td>
                  <td className="px-6 py-4">{entry.status}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </main>
    </div>
  );
}
