'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

/* ---------- type & mock data ---------- */
type Section = {
  id: number;
  name: string;
  instructor: string;
  schedule: string;
};

const mockSections: Section[] = [
  { id: 1, name: 'Section A – Software Eng.', instructor: 'Dr. Smith', schedule: 'Mon & Wed 10‑11:30' },
  { id: 2, name: 'Section B – HCI',          instructor: 'Prof. Johnson', schedule: 'Tue & Thu 14‑15:30' },
  { id: 3, name: 'Section C – Algorithms',    instructor: 'Dr. Smith',     schedule: 'Fri 08‑10:00' },
];

/* ---------- component ---------- */
export default function CourseSectionList() {
  const router = useRouter();

  /* state */
  const [sections, setSections] = useState<Section[]>(mockSections);
  const [courseFilter, setCourseFilter] = useState('');
  const [instructorFilter, setInstructorFilter] = useState('');
  const [selected, setSelected] = useState<Section | null>(null);
  const [editing, setEditing] = useState<Section | null>(null);
  const [editData, setEditData] = useState({ name: '', instructor: '', schedule: '' });
  

  const filtered = sections.filter(
    (s) =>
      s.name.toLowerCase().includes(courseFilter.toLowerCase()) &&
      s.instructor.toLowerCase().includes(instructorFilter.toLowerCase())
  );

  const handleDelete = (id: number) =>
    setSections((prev) => prev.filter((s) => s.id !== id));

  return (
    <div className="min-h-screen p-10 bg-white text-black">
      <button
        onClick={() => router.push('/staff/class-management')}
        className="text-blue-600 hover:underline mb-6 inline-block"
      >
        ← Back to Class Management
      </button>

      <h1 className="text-3xl font-bold mb-6">Course Sections</h1>

      {/* filters */}
      <div className="flex flex-col md:flex-row gap-4 mb-6">
        <input
          placeholder="Filter by course name"
          value={courseFilter}
          onChange={(e) => setCourseFilter(e.target.value)}
          className="border px-4 py-2 rounded w-full md:w-1/3"
        />
        <input
          placeholder="Filter by instructor"
          value={instructorFilter}
          onChange={(e) => setInstructorFilter(e.target.value)}
          className="border px-4 py-2 rounded w-full md:w-1/3"
        />
      </div>

      {/* table */}
      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-300 rounded text-sm">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left">Section</th>
              <th className="px-4 py-2 text-left">Instructor</th>
              <th className="px-4 py-2 text-left">Schedule</th>
              <th className="px-4 py-2 text-left">Actions</th>
            </tr>
          </thead>
          <tbody>
            {filtered.length ? (
              filtered.map((s) => (
                <tr key={s.id} className="hover:bg-gray-50 border-t">
                  <td className="px-4 py-2">{s.name}</td>
                  <td className="px-4 py-2">{s.instructor}</td>
                  <td className="px-4 py-2">{s.schedule}</td>
                  <td className="px-4 py-2 space-x-2">
                    <button
                      className="text-blue-600 hover:underline"
                      onClick={() => setSelected(s)}
                    >
                      View
                    </button>
                    
                    <button
                      className="text-yellow-600 hover:underline"
                      onClick={() => {
                      setEditing(s);                                 // open modal for this row
                      setEditData({                                  // pre‑fill form
                      name: s.name,
                      instructor: s.instructor,
                      schedule: s.schedule,});
                      }}>
                          Edit
                    </button>

                    <button
                      className="text-red-600 hover:underline"
                      onClick={() => handleDelete(s.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={4} className="py-6 text-center text-gray-500">
                  No sections match your filters.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {/* simple details modal */}
      {selected && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-xl w-full max-w-md p-6">
            <h2 className="text-xl font-semibold mb-4">Section Details</h2>
            <p><strong>Section:</strong> {selected.name}</p>
            <p><strong>Instructor:</strong> {selected.instructor}</p>
            <p><strong>Schedule:</strong> {selected.schedule}</p>
            <div className="text-right mt-6">
              <button
                onClick={() => setSelected(null)}
                className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}

      {/* ---------- Edit Modal ---------- */}
{editing && (
  <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
    <div className="bg-white rounded-lg shadow-xl w-full max-w-md p-6">
      <h2 className="text-xl font-semibold mb-4">Edit Section</h2>

      {/* simple form */}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          setSections((prev) =>
            prev.map((sec) =>
              sec.id === editing.id
                ? { ...sec, ...editData }
                : sec
            )
          );
          setEditing(null);
        }}
        className="flex flex-col gap-3"
      >
        <label className="text-sm font-medium">
          Section Name
          <input
            className="border px-3 py-1 rounded w-full mt-1"
            value={editData.name}
            onChange={(e) => setEditData({ ...editData, name: e.target.value })}
            required
          />
        </label>

        <label className="text-sm font-medium">
          Instructor
          <input
            className="border px-3 py-1 rounded w-full mt-1"
            value={editData.instructor}
            onChange={(e) =>
              setEditData({ ...editData, instructor: e.target.value })
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

        <div className="flex justify-end gap-3 mt-4">
          <button
            type="button"
            onClick={() => setEditing(null)}
            className="text-gray-500 hover:text-gray-700"
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

    </div>
  );
}
