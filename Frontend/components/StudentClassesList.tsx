'use client';

  import React, { useState, useEffect } from 'react';

  interface ClassEntry {
    id: string;
    courseName: string;
    courseCode: string;
  }

  export default function StudentClassesList() {
    const [classes, setClasses] = useState<ClassEntry[]>([]);

    useEffect(() => {
        const userId = localStorage.getItem("userId");
      fetch(`http://localhost:8080/api/courses/student/${userId}`)
        .then((res) => res.json())
        .then((data) => setClasses(data))
        .catch((err) => {
          console.error('Failed to fetch enrolled classes:', err);
          setClasses([]);
        });
    }, []);

    return (
      <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-3xl mx-auto">
        <h2 className="text-3xl font-semibold text-[#3553B5] mb-4 text-center">Enrolled Classes</h2>
        <div className="grid gap-4">
          {classes.length > 0 ? (
            classes.map((entry) => (
              <div
                key={entry.id}
                className="p-4 rounded-lg shadow bg-gradient-to-r from-blue-50 to-blue-100 flex flex-col items-start"
              >
                <h3 className="text-lg font-bold text-[#3553B5] mb-1">{entry.courseName}</h3>
                <span className="text-sm text-gray-500">Code: {entry.courseCode}</span>
              </div>
            ))
          ) : (
            <p className="text-gray-500 mt-4 text-center">No enrolled classes found.</p>
          )}
        </div>
      </div>
    );
  }