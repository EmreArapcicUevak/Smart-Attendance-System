'use client';

import React, { useEffect, useState } from 'react';

interface AttendanceRecord {
  date: string;
  status: 'Present' | 'Absent' | 'Late';
  subject: string;
}

export default function StudentAttendance() {
  const [attendanceData, setAttendanceData] = useState<AttendanceRecord[]>([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedDate, setSelectedDate] = useState('');

  useEffect(() => {
    // Fetch from backend API
    fetch('/api/attendance')
      .then(res => res.json())
      .then(setAttendanceData)
      .catch(() => {
        setAttendanceData([
          { date: '2025-04-01', status: 'Present', subject: 'Mathematics' },
        ]);
      });
  }, []);

  const filteredData = attendanceData.filter((record) => {
  const matchesSubject = record.subject.toLowerCase().includes(searchTerm.toLowerCase());
  const matchesDate = record.date.toLowerCase().includes(selectedDate.toLowerCase());
  return matchesSubject && matchesDate;
});


  const totalPresent = filteredData.filter(r => r.status === 'Present').length;
  const totalAbsent = filteredData.filter(r => r.status === 'Absent').length;
  const totalLate = filteredData.filter(r => r.status === 'Late').length;

  return (
    <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-5xl mx-auto">
      <h2 className="text-3xl font-semibold text-[#3553B5] mb-6 text-center">
        Attendance Records
      </h2>

      {/* Filters */}
      <div className="flex flex-col md:flex-row gap-4 justify-between mb-8">
        <input
          type="text"
          placeholder="Search by course name..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="flex-1 border border-gray-300 rounded-lg px-4 py-2 text-sm bg-white"
        />
        <input
  type="text"
  placeholder="Search by date (YYYY-MM-DD)"
  value={selectedDate}
  onChange={(e) => setSelectedDate(e.target.value)}
  className="w-full md:w-1/3 border border-gray-300 rounded-lg px-4 py-2 text-sm bg-white"
/>

      </div>

      {/* Summary */}
      <div className="grid grid-cols-3 gap-4 mb-6">
        <div className="bg-green-100 text-green-800 p-4 rounded-md text-center">
          <p className="text-2xl font-bold">{totalPresent}</p>
          <p>Present</p>
        </div>
        <div className="bg-red-100 text-red-800 p-4 rounded-md text-center">
          <p className="text-2xl font-bold">{totalAbsent}</p>
          <p>Absent</p>
        </div>
        <div className="bg-yellow-100 text-yellow-800 p-4 rounded-md text-center">
          <p className="text-2xl font-bold">{totalLate}</p>
          <p>Late</p>
        </div>
      </div>

      {/* Attendance Table */}
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white text-left text-sm border border-gray-200 rounded-lg">
          <thead className="bg-gray-200 text-black">
            <tr>
              <th className="px-6 py-3">Date</th>
              <th className="px-6 py-3">Course</th>
              <th className="px-6 py-3">Status</th>
            </tr>
          </thead>
          <tbody>
            {filteredData.map((record, index) => (
              <tr
                key={index}
                className={`hover:bg-gray-100 border-t ${
                  record.status === 'Present'
                    ? 'bg-green-50'
                    : record.status === 'Absent'
                    ? 'bg-red-50'
                    : 'bg-yellow-50'
                }`}
              >
                <td className="px-6 py-4">{record.date}</td>
                <td className="px-6 py-4">{record.subject}</td>
                <td className={`px-6 py-4 font-semibold ${
                  record.status === 'Present'
                    ? 'text-green-600'
                    : record.status === 'Absent'
                    ? 'text-red-500'
                    : 'text-yellow-600'
                }`}>
                  {record.status}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {filteredData.length === 0 && (
        <p className="mt-4 text-gray-600 text-center">No attendance records found.</p>
      )}
    </div>
  );
}
