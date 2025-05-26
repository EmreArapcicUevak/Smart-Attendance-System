'use client';

import { useRouter, useParams } from 'next/navigation';
import { useState } from 'react';

interface AttendanceEntry {
  sessionType: string;
  week: number;
  status: string;
}

export default function AttendanceDetailsPage() {
  const router = useRouter();
  const params = useParams();
  const sectionId = params?.sectionId;

  const [searchId, setSearchId] = useState('');
  const [attendanceData, setAttendanceData] = useState<AttendanceEntry[]>([]);

  const handleSearch = async () => {
    if (!searchId || !sectionId) return;

try {
  const res = await fetch(
    `http://localhost:8080/api/students/${searchId}/attendance?courseId=${sectionId}`,
    {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('authToken') || ''}`
      }
    }
  );

      if (res.ok) {
        const data = await res.json();
        setAttendanceData(data);
      } else {
        setAttendanceData([]);
        alert('No attendance data found.');
      }
    } catch (err) {
      console.error(err);
      alert('Failed to fetch attendance.');
    }
  };

  return (
    <div className="min-h-screen flex bg-white text-black font-sans">
      {/* Sidebar */}
      <aside className="w-64 bg-[#3553B5] text-white p-6 flex flex-col gap-6">
        <h2 className="text-2xl font-bold">Smart Attendance</h2>
        <button className="text-left text-lg hover:text-gray-200" onClick={() => router.back()}>
          Back to Classes
        </button>
      </aside>

      {/* Main */}
      <main className="flex-1 p-10">
        <h1 className="text-3xl font-bold mb-6">Attendance Details - {sectionId}</h1>

        <div className="mb-4">
          <input
            type="text"
            placeholder="Search by Student ID"
            className="border border-gray-300 px-4 py-2 rounded-md w-full max-w-sm"
            value={searchId}
            onChange={(e) => setSearchId(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === 'Enter') handleSearch();
            }}
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
              {attendanceData.length > 0 ? (
                attendanceData.map((entry, idx) => (
                  <tr key={idx} className="hover:bg-[#EFF1FA] border-t border-gray-200">
                    <td className="px-6 py-4">{searchId}</td>
                    <td className="px-6 py-4">{entry.sessionType}</td>
                    <td className="px-6 py-4">{entry.week}</td>
                    <td className="px-6 py-4">{entry.status}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td className="px-6 py-4 text-center text-gray-500" colSpan={4}>
                    No data to display.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </main>
    </div>
  );
}
