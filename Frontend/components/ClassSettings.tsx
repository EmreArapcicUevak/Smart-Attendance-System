'use client';

import React, { useState } from 'react';

interface Student {
  id: number;
  name: string;
}

interface CourseComponent {
  id: number;
  name: string;
  instructor: string;
  schedule: string;
  students: Student[];
  sessionSettings: string;
}

interface ClassSettingsProps {
  course: CourseComponent;
  onDelete: () => void;
  onSave: (updatedCourse: CourseComponent) => void;
}

const ClassSettings: React.FC<ClassSettingsProps> = ({ course, onDelete, onSave }) => {
  const [currentView, setCurrentView] = useState<'details' | 'edit' | 'students'>('details');
  const [editedName, setEditedName] = useState(course.name);
  const [editedInstructor, setEditedInstructor] = useState(course.instructor);
  const [editedSchedule, setEditedSchedule] = useState(course.schedule);
  const [newStudentName, setNewStudentName] = useState('');
  const [students, setStudents] = useState<Student[]>(course.students);

  // Save the changes made to the course
  const handleSaveChanges = () => {
    const updatedCourse: CourseComponent = {
      ...course,
      name: editedName,
      instructor: editedInstructor,
      schedule: editedSchedule,
      students: students,
    };
    onSave(updatedCourse);
    setCurrentView('details');
  };

  // Add a new student to the list
  const handleAddStudent = () => {
    if (newStudentName.trim()) {
      const newStudent = { id: students.length + 1, name: newStudentName };
      setStudents([...students, newStudent]);
      setNewStudentName('');
    }
  };

  // Remove a student from the list
  const handleRemoveStudent = (id: number) => {
    setStudents(students.filter((student) => student.id !== id));
  };

  return (
    <div className="p-6 bg-white rounded-lg shadow-md w-full">
      {/* Navigation for different views */}
      <div className="flex gap-4 mb-6">
        <button
          onClick={() => setCurrentView('details')}
          className={`px-4 py-2 rounded ${currentView === 'details' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}
        >
          Details
        </button>
        <button
          onClick={() => setCurrentView('edit')}
          className={`px-4 py-2 rounded ${currentView === 'edit' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}
        >
          Edit
        </button>
        <button
          onClick={() => setCurrentView('students')}
          className={`px-4 py-2 rounded ${currentView === 'students' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}
        >
          Students
        </button>
      </div>

      {/* Details View */}
      {currentView === 'details' && (
        <div>
          <h3 className="text-xl font-semibold">Class Details</h3>
          <p><strong>Name:</strong> {course.name}</p>
          <p><strong>Instructor:</strong> {course.instructor}</p>
          <p><strong>Schedule:</strong> {course.schedule}</p>
          <p><strong>Session:</strong> {course.sessionSettings}</p>
          <button
            onClick={onDelete}
            className="mt-4 px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
          >
            Delete Section
          </button>
        </div>
      )}

      {/* Edit View */}
      {currentView === 'edit' && (
        <div>
          <h3 className="text-xl font-semibold">Edit Class</h3>
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
          <h3 className="text-xl font-semibold">Enrolled Students</h3>
          <input
            className="w-full p-2 border rounded my-2"
            value={newStudentName}
            onChange={(e) => setNewStudentName(e.target.value)}
            placeholder="New Student Name"
          />
          <button
            onClick={handleAddStudent}
            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          >
            Add Student
          </button>
          <ul className="mt-4 space-y-2">
            {students.map((student) => (
              <li key={student.id} className="flex justify-between items-center">
                {student.name}
                <button
                  onClick={() => handleRemoveStudent(student.id)}
                  className="px-2 py-1 bg-red-500 text-white rounded hover:bg-red-600"
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
};

export default ClassSettings;
