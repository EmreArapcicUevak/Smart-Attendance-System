'use client';

import { useState, useMemo } from 'react';
import { FaSearch, FaPlus, FaEdit, FaTrash } from 'react-icons/fa';

type StudentStatus = 'Present' | 'Absent' | 'Late';

type Student = {
  id: string;
  name: string;
  major: string;
  status: StudentStatus;
  note?: string;
};

const statusColors: Record<StudentStatus, string> = {
  Present: 'bg-green-200 text-green-800',
  Absent: 'bg-red-200 text-red-800',
  Late: 'bg-yellow-200 text-yellow-800',
};

const mockStudents: Student[] = [
  { id: 'S001', name: 'Alice Johnson', major: 'Computer Science', status: 'Present' },
  { id: 'S002', name: 'Bob Smith', major: 'Software Engineering', status: 'Absent' },
  { id: 'S003', name: 'Charlie Doe', major: 'Information Systems', status: 'Present' },
  { id: 'S004', name: 'Dana White', major: 'Computer Science', status: 'Late' },
  { id: 'S005', name: 'Eliot Black', major: 'IT Management', status: 'Present' },
];

const pageSize = 4;

interface StudentListProps {
  sectionId?: string;
  sessionId?: string;
  topic?: string;
}

export default function StudentList({ sectionId, sessionId }: StudentListProps) {
  const [students, setStudents] = useState<Student[]>(mockStudents);
  const [searchQuery, setSearchQuery] = useState('');
  const [statusFilter, setStatusFilter] = useState<'All' | StudentStatus>('All');
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedStudent, setSelectedStudent] = useState<Student | null>(null);
  const [editStudent, setEditStudent] = useState<Student | null>(null);
  const [showAddModal, setShowAddModal] = useState(false);
  const [newStudent, setNewStudent] = useState<Student>({
    id: '',
    name: '',
    major: '',
    status: 'Present',
    note: '',
  });

  const filteredStudents = useMemo(() => {
    return students.filter((student) => {
      const matchesQuery =
        student.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
        student.id.toLowerCase().includes(searchQuery.toLowerCase());

      const matchesStatus = statusFilter === 'All' || student.status === statusFilter;

      return matchesQuery && matchesStatus;
    });
  }, [searchQuery, statusFilter, students]);

  const paginatedStudents = filteredStudents.slice(
    (currentPage - 1) * pageSize,
    currentPage * pageSize
  );

  const totalPages = Math.ceil(filteredStudents.length / pageSize);

  const handleDelete = (id: string) => {
    setStudents((prev) => prev.filter((student) => student.id !== id));
  };

  const handleEditChange = (field: keyof Student, value: string) => {
    if (!editStudent) return;
    setEditStudent({ ...editStudent, [field]: value });
  };

  const handleEditSave = () => {
    if (!editStudent) return;
    setStudents((prev) =>
      prev.map((student) => (student.id === editStudent.id ? editStudent : student))
    );
    setEditStudent(null);
  };

  return (
    <div className="bg-white p-6 rounded shadow-md">
      <div className="flex flex-col md:flex-row md:items-center justify-between mb-4 gap-2">
        <h2 className="text-2xl font-semibold">
          {sectionId && `Students in ${sectionId}`}
          {sessionId && `Students in ${sessionId}`}
          {!sectionId && !sessionId && 'Student List'}
        </h2>

        <div className="flex gap-2 items-center">
          <input
            type="text"
            placeholder="Search by name or ID"
            className="border px-2 py-1 rounded text-sm"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
          <select
            value={statusFilter}
            onChange={(e) => setStatusFilter(e.target.value as any)}
            className="border px-2 py-1 rounded text-sm"
          >
            <option value="All">All Statuses</option>
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
            <option value="Late">Late</option>
          </select>
          <button
            className="bg-blue-600 text-white px-3 py-1 rounded text-sm flex items-center gap-1"
            onClick={() => {
              setNewStudent({ id: '', name: '', major: '', status: 'Present', note: '' });
              setShowAddModal(true);
            }}
          >
            <FaPlus />
            Add Student
          </button>
        </div>
      </div>

      <table className="min-w-full bg-white border border-gray-200 text-sm">
        <thead>
          <tr className="bg-gray-100">
            <th className="py-2 px-4 border">Name</th>
            <th className="py-2 px-4 border">Student ID</th>
            <th className="py-2 px-4 border">Major</th>
            <th className="py-2 px-4 border">Status</th>
            <th className="py-2 px-4 border">Note</th>
            <th className="py-2 px-4 border">Actions</th>
          </tr>
        </thead>
        <tbody>
          {paginatedStudents.map((student) => (
            <tr key={student.id} className="text-center">
              <td className="py-2 px-4 border">
                <button
                  onClick={() => setSelectedStudent(student)}
                  className="text-blue-600 hover:underline"
                >
                  {student.name}
                </button>
              </td>
              <td className="py-2 px-4 border">{student.id}</td>
              <td className="py-2 px-4 border">{student.major}</td>
              <td className="py-2 px-4 border">
  <span className={`px-2 py-1 rounded ${statusColors[student.status]}`}>
    {student.status}
  </span>
</td>

<td className="py-2 px-4 border">
  {student.note ? (
    <span className="text-sm text-gray-700">{student.note}</span>
  ) : (
    <span className="text-sm text-gray-400 italic">No note</span>
  )}
</td>

              <td className="py-2 px-4 border flex justify-center gap-2">
                <button
                  className="text-yellow-600 hover:underline"
                  onClick={() => setEditStudent(student)}
                >
                  <FaEdit />
                </button>
                <button
                  className="text-red-600 hover:underline"
                  onClick={() => handleDelete(student.id)}
                >
                  <FaTrash />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="flex justify-center mt-4 gap-2">
        {Array.from({ length: totalPages }, (_, i) => (
          <button
            key={i}
            onClick={() => setCurrentPage(i + 1)}
            className={`px-3 py-1 rounded border ${
              currentPage === i + 1 ? 'bg-blue-600 text-white' : 'bg-gray-200'
            }`}
          >
            {i + 1}
          </button>
        ))}
      </div>

      {/* View Modal */}
      {selectedStudent && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-lg p-6 w-full max-w-sm">
            <h2 className="text-xl font-semibold mb-4">Student Details</h2>
            <p><strong>Name:</strong> {selectedStudent.name}</p>
            <p><strong>Student ID:</strong> {selectedStudent.id}</p>
            <p><strong>Major:</strong> {selectedStudent.major}</p>
            <p>
              <strong>Status:</strong>{' '}
              <span className={`px-2 py-1 rounded ${statusColors[selectedStudent.status]}`}>
                {selectedStudent.status}
              </span>
            </p>
            {selectedStudent.note && (
              <p><strong>Note:</strong> {selectedStudent.note}</p>
            )}
            <div className="flex justify-end mt-4">
              <button
                onClick={() => setSelectedStudent(null)}
                className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Edit Modal */}
      {editStudent && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-lg p-6 w-full max-w-sm">
            <h2 className="text-xl font-semibold mb-4">Edit Student</h2>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                handleEditSave();
              }}
              className="flex flex-col gap-3"
            >
              <label className="text-sm font-medium">
                Name
                <input
                  type="text"
                  value={editStudent.name}
                  onChange={(e) => handleEditChange('name', e.target.value)}
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Student ID
                <input
                  type="text"
                  value={editStudent.id}
                  onChange={(e) => handleEditChange('id', e.target.value)}
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Major
                <input
                  type="text"
                  value={editStudent.major}
                  onChange={(e) => handleEditChange('major', e.target.value)}
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Status
                <select
                  value={editStudent.status}
                  onChange={(e) => handleEditChange('status', e.target.value)}
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                >
                  <option value="Present">Present</option>
                  <option value="Absent">Absent</option>
                  <option value="Late">Late</option>
                </select>
              </label>
              <label className="text-sm font-medium">
                Note
                <input
                  type="text"
                  value={editStudent.note || ''}
                  onChange={(e) => handleEditChange('note', e.target.value)}
                  className="border px-3 py-1 rounded w-full mt-1"
                />
              </label>
              <div className="flex justify-end gap-3 mt-4">
                <button
                  type="button"
                  className="text-gray-500 hover:text-gray-700"
                  onClick={() => setEditStudent(null)}
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

      {/* Add Modal */}
      {showAddModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-lg p-6 w-full max-w-sm">
            <h2 className="text-xl font-semibold mb-4">Add New Student</h2>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                setStudents((prev) => [...prev, newStudent]);
                setShowAddModal(false);
              }}
              className="flex flex-col gap-3"
            >
              <label className="text-sm font-medium">
                Name
                <input
                  type="text"
                  value={newStudent.name}
                  onChange={(e) =>
                    setNewStudent({ ...newStudent, name: e.target.value })
                  }
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Student ID
                <input
                  type="text"
                  value={newStudent.id}
                  onChange={(e) =>
                    setNewStudent({ ...newStudent, id: e.target.value })
                  }
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Major
                <input
                  type="text"
                  value={newStudent.major}
                  onChange={(e) =>
                    setNewStudent({ ...newStudent, major: e.target.value })
                  }
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Status
                <select
                  value={newStudent.status}
                  onChange={(e) =>
                    setNewStudent({ ...newStudent, status: e.target.value as StudentStatus })
                  }
                  className="border px-3 py-1 rounded w-full mt-1"
                >
                  <option value="Present">Present</option>
                  <option value="Absent">Absent</option>
                  <option value="Late">Late</option>
                </select>
              </label>
              <label className="text-sm font-medium">
                Note
                <input
                  type="text"
                  value={newStudent.note || ''}
                  onChange={(e) =>
                    setNewStudent({ ...newStudent, note: e.target.value })
                  }
                  className="border px-3 py-1 rounded w-full mt-1"
                />
              </label>
              <div className="flex justify-end gap-3 mt-4">
                <button
                  type="button"
                  onClick={() => setShowAddModal(false)}
                  className="text-gray-500 hover:text-gray-700"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                >
                  Add
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
