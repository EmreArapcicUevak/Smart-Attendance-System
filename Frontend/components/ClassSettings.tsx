'use client';

import React, { useState } from 'react';

interface Student {
  id: number;
  name: string;
}

interface ClassComponent {
  id: number;
  name: string;
  instructor: string;
  schedule: string;
  students: Student[];
  sessionSettings: string;
}

const mockClass: ClassComponent = {
  id: 1,
  name: 'Advanced Mathematics',
  instructor: 'Mr. John Smith',
  schedule: 'Monday 10:00 - 11:30',
  students: [
    { id: 1, name: 'Alice Johnson' },
    { id: 2, name: 'Bob Smith' },
    { id: 3, name: 'Charlie Brown' },
  ],
  sessionSettings: 'Weekly - Room 101',
};

export default function ClassSettings() {
  const [currentView, setCurrentView] = useState<'details' | 'edit' | 'students'>('details');
  const [classInfo, setClassInfo] = useState<ClassComponent>(mockClass);
  const [newStudentName, setNewStudentName] = useState('');
  const [editedName, setEditedName] = useState(classInfo.name);
  const [editedInstructor, setEditedInstructor] = useState(classInfo.instructor);
  const [editedSchedule, setEditedSchedule] = useState(classInfo.schedule);

  // Switch views
  const handleViewChange = (view: 'details' | 'edit' | 'students') => {
    setCurrentView(view);
  };

  // Save changes to class info
  const handleSaveChanges = () => {
    setClassInfo((prev) => ({
      ...prev,
      name: editedName,
      instructor: editedInstructor,
      schedule: editedSchedule,
    }));
    setCurrentView('details');
  };

  // Add a student
  const handleAddStudent = () => {
    if (newStudentName.trim()) {
      const newStudent: Student = {
        id: classInfo.students.length + 1,
        name: newStudentName,
      };
      setClassInfo((prev) => ({
        ...prev,
        students: [...prev.students, newStudent],
      }));
      setNewStudentName('');
    }
  };

  // Remove a student
  const handleRemoveStudent = (id: number) => {
    setClassInfo((prev) => ({
      ...prev,
      students: prev.students.filter((student) => student.id !== id),
    }));
  };

  // Delete the section
  const handleDeleteSection = () => {
    alert('Section deleted!');
    setClassInfo({ ...mockClass, students: [] });
  };

  return (
    <div className="p-6 bg-white rounded-lg shadow-md w-full max-w-3xl mx-auto">
      <div className="flex gap-4 mb-6">
        <button
          onClick={() => handleViewChange('details')}
          className={`px-4 py-2 rounded ${currentView === 'details' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}
        >
          Details
        </button>
        <button
          onClick={() => handleViewChange('edit')}
          className={`px-4 py-2 rounded ${currentView === 'edit' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}
        >
          Edit
        </button>
        <button
          onClick={() => handleViewChange('students')}
          className={`px-4 py-2 rounded ${currentView === 'students' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}
        >
          Students
        </button>
      </div>

      {/* Details View */}
      {currentView === 'details' && (
        <div>
          <h2 className="text-2xl font-semibold text-[#3553B5]">Class Details</h2>
          <p>Name: {classInfo.name}</p>
          <p>Instructor: {classInfo.instructor}</p>
          <p>Schedule: {classInfo.schedule}</p>
          <p>Session Settings: {classInfo.sessionSettings}</p>
          <button
            onClick={handleDeleteSection}
            className="mt-4 px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
          >
            Delete Section
          </button>
        </div>
      )}

      {/* Edit View */}
      {currentView === 'edit' && (
        <div>
          <h2 className="text-2xl font-semibold text-[#3553B5]">Edit Class Details</h2>
          <input
            className="w-full p-2 border rounded my-2"
            value={editedName}
            onChange={(e) => setEditedName(e.target.value)}
            placeholder="Class Name"
          />
          <input
            className="w-full p-2 border rounded my-2"
            value={editedInstructor}
            onChange={(e) => setEditedInstructor(e.target.value)}
            placeholder="Instructor"
          />
          <input
            className="w-full p-2 border rounded my-2"
            value={editedSchedule}
            onChange={(e) => setEditedSchedule(e.target.value)}
            placeholder="Schedule"
          />
          <button
            onClick={handleSaveChanges}
            className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
          >
            Save Changes
          </button>
        </div>
      )}

      {/* Students View */}
      {currentView === 'students' && (
        <div>
          <h2 className="text-2xl font-semibold text-[#3553B5]">Enrolled Students</h2>
          <input
            className="w-full p-2 border rounded my-2"
            value={newStudentName}
            onChange={(e) => setNewStudentName(e.target.value)}
            placeholder="New Student Name"
          />
          <button
            onClick={handleAddStudent}
            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 mb-4"
          >
            Add Student
          </button>
          <ul>
            {classInfo.students.map((student) => (
              <li key={student.id} className="flex justify-between items-center p-2 border-b">
                {student.name}
                <button
                  onClick={() => handleRemoveStudent(student.id)}
                  className="text-sm bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
                >
                  Remove
                </button>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}
