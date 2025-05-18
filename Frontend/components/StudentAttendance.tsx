'use client';

import React, { useState } from 'react';

interface AttendanceRecord {
  date: string;
  status: 'Present' | 'Absent' | 'Late';
  subject: string;
}

const attendanceData: AttendanceRecord[] = [
  { date: '2025-04-01', status: 'Present', subject: 'Mathematics' },
  { date: '2025-04-02', status: 'Absent', subject: 'English' },
  { date: '2025-04-03', status: 'Late', subject: 'Physics' },
  { date: '2025-04-04', status: 'Present', subject: 'History' },
  { date: '2025-04-05', status: 'Absent', subject: 'Chemistry' },
  { date: '2025-04-06', status: 'Present', subject: 'Biology' },
];

export default function StudentAttendance() {
  const [subjectFilter, setSubjectFilter] = useState<string>('All');
  const [startDate, setStartDate] = useState<string>('');
  const [endDate, setEndDate] = useState<string>('');

  const filteredData = attendanceData.filter((record) => {
    const withinDateRange =
      (!startDate || new Date(record.date) >= new Date(startDate)) &&
      (!endDate || new Date(record.date) <= new Date(endDate));

    const matchesSubject =
      subjectFilter === 'All' || record.subject === subjectFilter;

    return withinDateRange && matchesSubject;
  });

  const totalPresent = filteredData.filter((record) => record.status === 'Present').length;
  const totalAbsent = filteredData.filter((record) => record.status === 'Absent').length;
  const totalLate = filteredData.filter((record) => record.status === 'Late').length;

  return (
    <div className="bg-white p-6 rounded-lg shadow-md w-full">
      <h2 className="text-3xl font-semibold text-[#3553B5] mb-4">Attendance Records</h2>

      {/* Summary Counts */}
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

      {/* Filters */}
      <div className="flex gap-4 mb-4">
        <select
          value={subjectFilter}
          onChange={(e) => setSubjectFilter(e.target.value)}
          className="border border-gray-300 rounded-lg px-4 py-2 text-sm w-1/3"
        >
          <option value="All">All Subjects</option>
          <option value="Mathematics">Mathematics</option>
          <option value="English">English</option>
          <option value="Physics">Physics</option>
          <option value="History">History</option>
          <option value="Chemistry">Chemistry</option>
          <option value="Biology">Biology</option>
        </select>

        <input
          type="date"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
          className="border border-gray-300 rounded-lg px-4 py-2 text-sm w-1/3"
          placeholder="Start Date"
        />

        <input
          type="date"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
          className="border border-gray-300 rounded-lg px-4 py-2 text-sm w-1/3"
          placeholder="End Date"
        />
      </div>

      {/* Attendance Table */}
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white text-left text-sm border border-gray-200 rounded-lg">
          <thead className="bg-gray-200 text-black">
            <tr>
              <th className="px-6 py-3">Date</th>
              <th className="px-6 py-3">Subject</th>
              <th className="px-6 py-3">Status</th>
            </tr>
          </thead>
          <tbody>
            {filteredData.map((record, index) => (
              <tr
                key={index}
                className={`hover:bg-gray-100 border-t ${
                  record.status === 'Present' ? 'bg-green-50' :
                  record.status === 'Absent' ? 'bg-red-50' :
                  'bg-yellow-50'
                }`}
              >
                <td className="px-6 py-4">{record.date}</td>
                <td className="px-6 py-4">{record.subject}</td>
                <td className={`px-6 py-4 font-semibold ${
                  record.status === 'Present' ? 'text-green-600' :
                  record.status === 'Absent' ? 'text-red-500' :
                  'text-yellow-600'
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
