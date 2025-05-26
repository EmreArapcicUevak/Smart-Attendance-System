'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function CreateCoursePage() {
  const router = useRouter();

  const [courseName, setCourseName] = useState('');
  const [courseCode, setCourseCode] = useState('');
  const [hasLab, setHasLab] = useState(false);
  const [hasTutorial, setHasTutorial] = useState(false);
  const [errors, setErrors] = useState<{ name?: string; code?: string }>({});

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const newErrors: { name?: string; code?: string } = {};

    if (!courseName.trim()) newErrors.name = 'Course name is required.';
    if (!courseCode.trim()) newErrors.code = 'Course code is required.';

    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      fetch('http://localhost:8080/api/courses', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          courseName,
          courseCode,
          hasLab,
          hasTutorial,
        }),
      })
        .then((res) => {
          if (res.ok) {
            alert('✅ Course created successfully!');
            setCourseName('');
            setCourseCode('');
            setHasLab(false);
            setHasTutorial(false);
          } else {
            return res.text().then(text => {
              alert('❌ Failed to create course: ' + text);
            });
          }
        })
        .catch((err) => {
          alert('❌ Error: ' + err.message);
        });
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

          {/* Lab & Tutorial Checkboxes */}
          <div className="space-y-2">
            <label className="flex items-center gap-2">
              <input
                type="checkbox"
                checked={hasLab}
                onChange={() => setHasLab(!hasLab)}
                className="w-5 h-5"
              />
              <span className="text-sm">Course has a lab</span>
            </label>
            <label className="flex items-center gap-2">
              <input
                type="checkbox"
                checked={hasTutorial}
                onChange={() => setHasTutorial(!hasTutorial)}
                className="w-5 h-5"
              />
              <span className="text-sm">Course has a tutorial</span>
            </label>
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
