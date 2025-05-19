'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

interface Params {
  params: { courseId: string };
}

export default function CourseDetailsPage({ params }: Params) {
  const router = useRouter();
  const { courseId } = params;

  const [activeTab, setActiveTab] = useState<'roster' | 'attendance'>('roster');

  // Mock data
  const mockRoster = [
    { id: 'S001', name: 'Alice Johnson', major: 'Computer Science' },
    { id: 'S002', name: 'Bob Smith', major: 'Software Engineering' },
    { id: 'S003', name: 'Charlie Doe', major: 'Information Systems' },
  ];

  const mockAttendance = [
    { date: '2025-04-01', present: 23, absent: 2 },
    { date: '2025-03-31', present: 21, absent: 4 },
    { date: '2025-03-30', present: 25, absent: 0 },
  ];

  return (
    <div className="min-h-screen p-10 bg-white text-black">
      {/* Back button */}
      <button
        onClick={() => router.back()}
        className="text-blue-600 hover:underline mb-6 inline-block"
      >
        ← Back
      </button>

      {/* Header */}
      <div className="mb-6">
        <h1 className="text-3xl font-bold text-[#3553B5] mb-1">
          Course Details: {courseId}
        </h1>
        <p className="text-gray-600 text-sm">
          View full details for this course — including roster and attendance.
        </p>
      </div>

      {/* Tabs */}
      <div className="flex gap-6 border-b border-gray-200 mb-4">
        <button
          onClick={() => setActiveTab('roster')}
          className={`pb-2 ${
            activeTab === 'roster'
              ? 'border-b-2 border-[#3553B5] text-[#3553B5] font-semibold'
              : 'text-gray-500 hover:text-[#3553B5]'
          }`}
        >
          Roster
        </button>
        <button
          onClick={() => setActiveTab('attendance')}
          className={`pb-2 ${
            activeTab === 'attendance'
              ? 'border-b-2 border-[#3553B5] text-[#3553B5] font-semibold'
              : 'text-gray-500 hover:text-[#3553B5]'
          }`}
        >
          Attendance
        </button>
      </div>

      {/* Roster */}
      {activeTab === 'roster' && (
        <div className="overflow-x-auto border border-gray-200 rounded-lg">
          <table className="w-full text-sm bg-white">
            <thead className="bg-[#D9D9D9]">
              <tr>
                <th className="px-6 py-3 text-left">Student Name</th>
                <th className="px-6 py-3 text-left">Student ID</th>
                <th className="px-6 py-3 text-left">Major</th>
              </tr>
            </thead>
            <tbody>
              {mockRoster.map((student, index) => (
                <tr key={index} className="border-t border-gray-200 hover:bg-[#EFF1FA]">
                  <td className="px-6 py-4">{student.name}</td>
                  <td className="px-6 py-4">{student.id}</td>
                  <td className="px-6 py-4">{student.major}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* Attendance */}
      {activeTab === 'attendance' && (
        <div className="overflow-x-auto border border-gray-200 rounded-lg">
          <table className="w-full text-sm bg-white">
            <thead className="bg-[#D9D9D9]">
              <tr>
                <th className="px-6 py-3 text-left">Date</th>
                <th className="px-6 py-3 text-left">Present</th>
                <th className="px-6 py-3 text-left">Absent</th>
              </tr>
            </thead>
            <tbody>
              {mockAttendance.map((record, index) => (
                <tr key={index} className="border-t border-gray-200 hover:bg-[#EFF1FA]">
                  <td className="px-6 py-4">{record.date}</td>
                  <td className="px-6 py-4">{record.present}</td>
                  <td className="px-6 py-4">{record.absent}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
