'use client';

import { useState } from 'react';
type Course = {
  id: number;
  name: string;
  schedule: string;
  semester: string;
  studentCount: number;
};

const mockCourses: Course[] = [
  {
    id: 1,
    name: 'Software Engineering',
    schedule: 'Mon & Wed 10:00 – 11:30',
    semester: 'Spring 2025',
    studentCount: 25,
  },
  {
    id: 2,
    name: 'Human‑Computer Interaction',
    schedule: 'Tue & Thu 14:00 – 15:30',
    semester: 'Spring 2025',
    studentCount: 32,
  },
  {
    id: 3,
    name: 'Advanced Algorithms',
    schedule: 'Fri 08:00 – 10:00',
    semester: 'Fall 2024',
    studentCount: 18,
  },
];

/* ---------- Component ---------- */
export default function StaffCourseList() {
  const [courses, setCourses] = useState<Course[]>(mockCourses);
  const [courseFilter, setCourseFilter] = useState('');
  const [semesterFilter, setSemesterFilter] = useState('');
  const [selected, setSelected] = useState<Course | null>(null); // for details modal
  const [editing, setEditing] = useState<Course | null>(null);
  const [editData, setEditData] = useState({ name: '', schedule: '', semester: '' });

  /* Filtering */
  const filtered = courses.filter(
    (c) =>
      c.name.toLowerCase().includes(courseFilter.toLowerCase()) &&
      c.semester.toLowerCase().includes(semesterFilter.toLowerCase())
  );

  /* Delete */
  const handleDelete = (id: number) =>
    setCourses((prev) => prev.filter((c) => c.id !== id));

  /* JSX */
  return (
    <section className="mt-10">
      <h2 className="text-2xl font-bold mb-4">My Courses</h2>
      <div className="overflow-x-auto">

      </div>
      {/* Filters */}
      <div className="flex flex-col md:flex-row gap-4 mb-6">
        <input
          className="border px-4 py-2 rounded w-full md:w-1/3"
          placeholder="Filter by course name"
          value={courseFilter}
          onChange={(e) => setCourseFilter(e.target.value)}
        />
        <input
          className="border px-4 py-2 rounded w-full md:w-1/3"
          placeholder="Filter by semester"
          value={semesterFilter}
          onChange={(e) => setSemesterFilter(e.target.value)}
        />
      </div>

      {/* Table */}
      <div className="min-w-full bg-white text-left text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
        {/* Table */}
<div className="w-full overflow-x-auto">
  <table className="w-full bg-white text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
    <thead className="bg-[#D9D9D9] text-black">
      <tr>
        <th className="px-6 py-3">Course Name</th>
        <th className="px-6 py-3">Schedule</th>
        <th className="px-6 py-3">Semester</th>
        <th className="px-6 py-3">Students</th>
        <th className="px-6 py-3">Actions</th>
      </tr>
    </thead>
    <tbody>
      {filtered.length ? (
        filtered.map((c) => (
          <tr
            key={c.id}
            className="hover:bg-[#EFF1FA] border-t border-gray-200"
          >
            <td className="px-6 py-4">{c.name}</td>
            <td className="px-6 py-4">{c.schedule}</td>
            <td className="px-6 py-4">{c.semester}</td>
            <td className="px-6 py-4">{c.studentCount}</td>
            <td className="px-6 py-4 space-x-3">
              <button
                className="text-blue-600 hover:underline"
                onClick={() => setSelected(c)}
              >
                View
              </button>
                 </td>
          </tr>
        ))
      ) : (
        <tr>
          <td colSpan={5} className="py-6 text-center text-gray-500">
            No courses match your filters.
          </td>
        </tr>
      )}
    </tbody>
  </table>
</div>

      </div>

      {/* ------------ Details Modal ------------ */}
      {selected && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-xl w-full max-w-md p-6">
            <h3 className="text-xl font-semibold mb-4">Course Details</h3>
            <p><strong>Course:</strong> {selected.name}</p>
            <p><strong>Schedule:</strong> {selected.schedule}</p>
            <p><strong>Semester:</strong> {selected.semester}</p>
            <p><strong>Students:</strong> {selected.studentCount}</p>
            <div className="text-right mt-6">
              <button
                className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                onClick={() => setSelected(null)}
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}

      {/* ------------ Edit Modal ------------ */}
      {editing && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-xl w-full max-w-md p-6">
            <h3 className="text-xl font-semibold mb-4">Edit Course</h3>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                setCourses((prev) =>
                  prev.map((c) =>
                    c.id === editing.id ? { ...c, ...editData } : c
                  )
                );
                setEditing(null);
              }}
              className="flex flex-col gap-3"
            >
              <label className="text-sm font-medium">
                Course Name
                <input
                  className="border px-3 py-1 rounded w-full mt-1"
                  value={editData.name}
                  onChange={(e) =>
                    setEditData({ ...editData, name: e.target.value })
                  }
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Schedule
                <input
                  className="border px-3 py-1 rounded w-full mt-1"
                  value={editData.schedule}
                  onChange={(e) =>
                    setEditData({ ...editData, schedule: e.target.value })
                  }
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Semester
                <input
                  className="border px-3 py-1 rounded w-full mt-1"
                  value={editData.semester}
                  onChange={(e) =>
                    setEditData({ ...editData, semester: e.target.value })
                  }
                  required
                />
              </label>

              <div className="flex justify-end gap-3 mt-4">
                <button
                  type="button"
                  className="text-gray-500 hover:text-gray-700"
                  onClick={() => setEditing(null)}
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                >
                  Save
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </section>
  );
}
