'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function CreateCoursePage() {
  const router = useRouter();

  const [courseName, setCourseName] = useState('');
  const [courseCode, setCourseCode] = useState('');
  const [faculty, setFaculty] = useState('');
  const [endTime, setEndTime] = useState('');
  const [courseDate, setCourseDate] = useState('');
  const [dayOfWeek, setDayOfWeek] = useState('');
  const [numStudents, setNumStudents] = useState('');
  const [semester, setSemester] = useState('');
  const [instructor, setInstructor] = useState('');
  const [errors, setErrors] = useState<{ name?: string; code?: string }>({});

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const newErrors: { name?: string; code?: string } = {};

    if (!courseName.trim()) newErrors.name = 'Course name is required.';
    if (!courseCode.trim()) newErrors.code = 'Course code is required.';

    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      const courseDetails = {
        courseName,
        courseCode,
        faculty,
        endTime,
        courseDate,
        dayOfWeek,
        numStudents,
        semester,
        instructor,
      };

      console.log('Course Created:', courseDetails);
      alert('Course created successfully!');

      // Reset fields
      setCourseName('');
      setCourseCode('');
      setFaculty('');
      setEndTime('');
      setCourseDate('');
      setDayOfWeek('');
      setNumStudents('');
      setSemester('');
      setInstructor('');
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
            <select
              value={faculty}
              onChange={(e) => setFaculty(e.target.value)}
              className="w-full px-4 py-2 border rounded-md"
            >
              <option value="">Select Faculty</option>
              <option value="Engineering">Engineering</option>
              <option value="Science">Science</option>
              <option value="Business">Business</option>
              <option value="Arts">Arts</option>
            </select>
          </div>

          {/* New Fields Below */}

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium mb-1">Day of the Week</label>
              <input
                type="text"
                value={dayOfWeek}
                onChange={(e) => setDayOfWeek(e.target.value)}
                className="w-full px-4 py-2 border rounded-md"
                placeholder="e.g., Monday"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">Number of Students</label>
              <input
                type="number"
                value={numStudents}
                onChange={(e) => setNumStudents(e.target.value)}
                className="w-full px-4 py-2 border rounded-md"
                placeholder="e.g., 25"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">Semester</label>
              <input
                type="text"
                value={semester}
                onChange={(e) => setSemester(e.target.value)}
                className="w-full px-4 py-2 border rounded-md"
                placeholder="e.g., Spring 2025"
              />
            </div>

            <div className="md:col-span-2">
              <label className="block text-sm font-medium mb-1">Instructor</label>
              <input
                type="text"
                value={instructor}
                onChange={(e) => setInstructor(e.target.value)}
                className="w-full px-4 py-2 border rounded-md"
                placeholder="e.g., Dr. Smith"
              />
            </div>
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
