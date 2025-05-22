'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function CreateCoursePage() {
  const router = useRouter();

  const [courseName, setCourseName] = useState('');
  const [courseCode, setCourseCode] = useState('');
  const [faculty, setFaculty] = useState('');
  const [errors, setErrors] = useState<{ name?: string; code?: string; faculty?: string }>({});

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const newErrors: { name?: string; code?: string; faculty?: string } = {};

    if (!courseName.trim()) newErrors.name = 'Course name is required.';
    if (!courseCode.trim()) newErrors.code = 'Course code is required.';
    if (!faculty.trim()) newErrors.faculty = 'Faculty is required.';

    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      const courseDetails = {
        courseName,
        courseCode,
        faculty,
      };

      console.log('Course Created:', courseDetails);
      alert('Course created successfully!');

      // Reset fields
      setCourseName('');
      setCourseCode('');
      setFaculty('');
    }
  };

  return (
    <div className="min-h-screen flex bg-white text-black font-sans">
      <main className="mx-auto mt-20 w-full max-w-xl px-6">
        <h1 className="text-3xl font-bold text-[#3553B5] mb-8">Create New Course</h1>
        <div>
          <button
            onClick={() => router.push('/staff/dashboard')}
            className="mb-6 px-4 py-2 bg-[#3553B5] text-white rounded hover:bg-blue-700"
          >
            ← Back to Dashboard
          </button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-6">
          {/* Course Name */}
          <div>
            <label className="block text-lg font-medium mb-1">Course Name</label>
            <input
              type="text"
              value={courseName}
              onChange={(e) => setCourseName(e.target.value)}
              className="w-full px-4 py-2 border rounded-md"
              placeholder="e.g., Introduction to Programming"
            />
            {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>}
          </div>

          {/* Course Code */}
          <div>
            <label className="block text-lg font-medium mb-1">Course Code</label>
            <input
              type="text"
              value={courseCode}
              onChange={(e) => setCourseCode(e.target.value)}
              className="w-full px-4 py-2 border rounded-md"
              placeholder="e.g., CS101"
            />
            {errors.code && <p className="text-red-500 text-sm mt-1">{errors.code}</p>}
          </div>

          {/* Faculty */}
          <div>
            <label className="block text-lg font-medium mb-1">Faculty</label>
            <input
              type="text"
              value={faculty}
              onChange={(e) => setFaculty(e.target.value)}
              className="w-full px-4 py-2 border rounded-md"
              placeholder="e.g., Faculty of Engineering and Natural Science"
            />
            {errors.faculty && <p className="text-red-500 text-sm mt-1">{errors.faculty}</p>}
          </div>

          {/* Submit */}
          <div>
            <button
              type="submit"
              className="w-full bg-[#3553B5] text-white py-2 rounded-md hover:bg-blue-700 text-lg font-medium"
            >
              Create Course
            </button>
          </div>
        </form>
      </main>
    </div>
  );
}
