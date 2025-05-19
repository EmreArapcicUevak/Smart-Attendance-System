'use client';

import React from 'react';
import { useRouter } from 'next/navigation';
import StudentClassesList from '../../../../components/StudentClassesList';

export default function StudentClassesPage() {
  const router = useRouter();

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-6 text-black font-sans">
      <div className="flex justify-between items-center mb-6 px-6">
        <h1 className="text-4xl font-bold text-[#3553B5]">My Classes</h1>
        <button
          onClick={() => router.push('/student/dashboard')}
          className="text-sm bg-[#3553B5] text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition"
        >
          Return to Dashboard
        </button>
      </div>
      
      {/* Classes List Covering Full Page */}
      <div className="w-full bg-white p-8 rounded-xl shadow-lg border border-gray-200">
        <StudentClassesList />
      </div>
    </div>
  );
}

