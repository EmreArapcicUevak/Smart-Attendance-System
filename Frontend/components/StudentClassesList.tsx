'use client';

import React, { useState, useEffect } from 'react';

interface ClassEntry {
  id: string;
  name: string;
  faculty: string;
}

export default function StudentClassesList() {
  const [classes, setClasses] = useState<ClassEntry[]>([]);

  useEffect(() => {
    // ⬇️ Replace this with actual API call later
    const classesData: ClassEntry[] = [
      { id: 'CS101', name: 'Computer Science', faculty: 'Engineering' }
    ];
    setClasses(classesData);
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
            <h3 className="text-xl font-bold text-[#3553B5]">{entry.id}</h3>
            <p className="text-sm text-gray-800 font-medium">{entry.name}</p>
            <p className="text-sm text-gray-600">Faculty: {entry.faculty}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
