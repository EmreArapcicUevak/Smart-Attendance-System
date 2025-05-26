'use client';

import React, { useState, useEffect } from 'react';

interface ClassEntry {
  studentId: string;
  fullName: string;
}

export default function StudentClassesList() {
  const [classes, setClasses] = useState<ClassEntry[]>([]);

  useEffect(() => {
    const studentId = '1'; // Replace with dynamic ID when ready
    fetch(`http://localhost:8080/api/courses/${studentId}`)
      .then((res) => res.json())
      .then((data) => setClasses(data))
      .catch((err) => {
        console.error('Failed to fetch enrolled classes:', err);
        setClasses([]);
      });
  }, []);

  return (
    <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-3xl mx-auto">
      <h2 className="text-3xl font-semibold text-[#3553B5] mb-4">Enrolled Classes</h2>

      <div className="grid gap-4">
        {classes.map((entry, index) => (
          <div
            key={index}
            className="p-4 rounded-lg shadow-md bg-gray-50"
          >
            <h3 className="text-xl font-bold text-[#3553B5]">{entry.studentId}</h3>
            <p className="text-sm text-gray-800 font-medium">{entry.fullName}</p>
          </div>
        ))}
      </div>

      {classes.length === 0 && (
        <p className="text-gray-500 mt-4 text-center">No enrolled classes found.</p>
      )}
    </div>
  );
}
