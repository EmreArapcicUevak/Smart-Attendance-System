'use client';

import React, { useEffect, useState } from 'react';

interface AttendanceRecord {
  status: 'Present' | 'Absent';
  subject: string;
  sessionType: 'Lecture' | 'Lab' | 'Tutorial';
  week: string;
  faculty: string;
}

export default function StudentAttendance() {
  const [attendanceData, setAttendanceData] = useState<AttendanceRecord[]>([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [sessionType, setSessionType] = useState('');

  useEffect(() => {
    fetch('/api/attendance')
      .then((res) => res.json())
      .then(setAttendanceData)
      .catch(() => {
        setAttendanceData([
          {
            status: 'Present',
            subject: 'Mathematics',
            sessionType: 'Lecture',
            faculty: 'Engineering',
            week: '1',
          },
        ]);
      });
  }, []);

  const filteredData = attendanceData.filter((record) => {
    const matchesSubject = record.subject.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesType = sessionType ? record.sessionType === sessionType : true;
    return matchesSubject && matchesType;
  });

  const totalPresent = filteredData.filter((r) => r.status === 'Present').length;
  const totalAbsent = filteredData.filter((r) => r.status === 'Absent').length;

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

        <select
          value={sessionType}
          onChange={(e) => setSessionType(e.target.value)}
          className="w-full md:w-1/3 border border-gray-300 rounded-lg px-4 py-2 text-sm bg-white"
        >
          <option value="">All Sessions</option>
          <option value="Lecture">Lecture</option>
          <option value="Lab">Lab</option>
          <option value="Tutorial">Tutorial</option>
        </select>
      </div>

      {/* Summary */}
      <div className="grid grid-cols-2 gap-4 mb-6">
        <div className="bg-green-100 text-green-800 p-4 rounded-md text-center">
          <p className="text-2xl font-bold">{totalPresent}</p>
          <p>Present</p>
        </div>
        <div className="bg-red-100 text-red-800 p-4 rounded-md text-center">
          <p className="text-2xl font-bold">{totalAbsent}</p>
          <p>Absent</p>
        </div>
      </div>

      {/* Attendance Table */}
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white text-left text-sm border border-gray-200 rounded-lg">
          <thead className="bg-gray-200 text-black">
            <tr>
              <th className="px-6 py-3">Course</th>
              <th className="px-6 py-3">Faculty</th>
              <th className="px-6 py-3">Session</th>
              <th className="px-6 py-3">Week</th>
              <th className="px-6 py-3">Status</th>
            </tr>
          </thead>
          <tbody>
            {filteredData.map((record, index) => (
              <tr
                key={index}
                className={`hover:bg-gray-100 border-t ${
                  record.status === 'Present' ? 'bg-green-50' : 'bg-red-50'
                }`}
              >
                <td className="px-6 py-4">{record.subject}</td>
                <td className="px-6 py-4">{record.faculty}</td>
                <td className="px-6 py-4">{record.sessionType}</td>
                <td className="px-6 py-4">{record.week}</td>
                <td
                  className={`px-6 py-4 font-semibold ${
                    record.status === 'Present' ? 'text-green-600' : 'text-red-500'
                  }`}
                >
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
