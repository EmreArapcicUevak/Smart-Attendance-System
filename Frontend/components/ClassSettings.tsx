'use client';
import React, { useState } from 'react';

interface CourseComponent {
  id: number;
  code: string;
  name: string;
  faculty: string;
  students?: string[];
}

interface ClassSettingsProps {
  course: CourseComponent;
  onDelete: () => void;
  onSave: (updatedCourse: CourseComponent) => void;
}

const ClassSettings: React.FC<ClassSettingsProps> = ({ course, onDelete, onSave }) => {
  const [currentView, setCurrentView] = useState<'details' | 'edit' | 'students'>('details');
  const [code, setCode] = useState(course.code);
  const [name, setName] = useState(course.name);
  const [faculty, setFaculty] = useState(course.faculty);
  const [newStudentId, setNewStudentId] = useState('');
  const [students, setStudents] = useState<string[]>(course.students || []);

  const handleSaveChanges = () => {
    const updatedCourse: CourseComponent = {
      ...course,
      code,
      name,
      faculty,
      students,
    };
    onSave(updatedCourse);
    setCurrentView('details');
  };

  const handleAddStudent = () => {
    if (newStudentId.trim() && !students.includes(newStudentId.trim())) {
      const updatedStudents = [...students, newStudentId.trim()];
      setStudents(updatedStudents);
      setNewStudentId('');
    }
  };

  return (
    <div>
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

      {currentView === 'details' && (
        <div>
          <p><strong>Course Code:</strong> {course.code}</p>
          <p><strong>Name:</strong> {course.name}</p>
          <p><strong>Faculty:</strong> {course.faculty}</p>
          <button
            onClick={onDelete}
            className="mt-4 px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
          >
            Delete Course
          </button>
        </div>
      )}

      {currentView === 'edit' && (
        <div>
          <input
            className="w-full p-2 border rounded my-2"
            value={code}
            onChange={(e) => setCode(e.target.value)}
            placeholder="Course Code"
          />
          <input
            className="w-full p-2 border rounded my-2"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Course Name"
          />
          <input
            className="w-full p-2 border rounded my-2"
            value={faculty}
            onChange={(e) => setFaculty(e.target.value)}
            placeholder="Faculty"
          />
          <button
            onClick={handleSaveChanges}
            className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
          >
            Save Changes
          </button>
        </div>
      )}

      {currentView === 'students' && (
        <div>
          <input
            className="w-full p-2 border rounded my-2"
            value={newStudentId}
            onChange={(e) => setNewStudentId(e.target.value)}
            placeholder="Enter Student ID"
          />
          <button
            onClick={handleAddStudent}
            className="mb-4 px-4 py-2 bg-indigo-600 text-white rounded hover:bg-indigo-700"
          >
            Add Student
          </button>

          <ul className="list-disc list-inside">
            {students.map((student, index) => (
              <li key={index}>{student}</li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default ClassSettings;
