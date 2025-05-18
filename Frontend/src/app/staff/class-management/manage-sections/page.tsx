'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

type Section = {
  id: number;
  name: string;
  studentCount: number;
};

export default function ManageSectionsPage() {
  const [sections, setSections] = useState<Section[]>([
    { id: 1, name: 'Section A', studentCount: 25 },
    { id: 2, name: 'Section B', studentCount: 30 },
  ]);
  const [showModal, setShowModal] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);
  const [editSectionId, setEditSectionId] = useState<number | null>(null);
  const [formData, setFormData] = useState({ name: '', studentCount: 0 });

  const router = useRouter();

  const resetForm = () => {
    setFormData({ name: '', studentCount: 0 });
    setIsEditMode(false);
    setEditSectionId(null);
    setShowModal(false);
  };

  const handleCreateSection = () => {
    const newSection: Section = {
      id: Date.now(),
      name: formData.name.trim(),
      studentCount: formData.studentCount,
    };
    setSections([...sections, newSection]);
    resetForm();
  };

  const handleUpdateSection = () => {
    setSections((prev) =>
      prev.map((section) =>
        section.id === editSectionId
          ? { ...section, name: formData.name.trim(), studentCount: formData.studentCount }
          : section
      )
    );
    resetForm();
  };

  const handleDeleteSection = (id: number) => {
    setSections((prev) => prev.filter((section) => section.id !== id));
  };

  return (
    <div className="min-h-screen p-10 bg-white text-black">
      <div className="mb-6">
        <button
          onClick={() => router.push('/staff/class-management')}
          className="text-blue-600 hover:underline mb-4 inline-block"
        >
          ← Back to Class Management
        </button>
        <h1 className="text-3xl font-bold">Manage Sections</h1>
        <p className="text-gray-600 mt-1">Create, update, and delete class sections.</p>
      </div>

      <div className="flex justify-end mb-4">
        <button
          onClick={() => {
            setIsEditMode(false);
            setFormData({ name: '', studentCount: 0 });
            setShowModal(true);
          }}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          + Create Section
        </button>
      </div>

      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-300 rounded text-sm">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left">Section Name</th>
              <th className="px-4 py-2 text-left">Student Count</th>
              <th className="px-4 py-2 text-left">Actions</th>
            </tr>
          </thead>
          <tbody>
            {sections.map((section) => (
              <tr key={section.id} className="hover:bg-gray-50 border-t">
                <td className="px-4 py-2">{section.name}</td>
                <td className="px-4 py-2">{section.studentCount}</td>
                <td className="px-4 py-2 flex gap-3">
                  <button
                    className="text-blue-600 hover:underline"
                    onClick={() => {
                      setIsEditMode(true);
                      setEditSectionId(section.id);
                      setFormData({
                        name: section.name,
                        studentCount: section.studentCount,
                      });
                      setShowModal(true);
                    }}
                  >
                    Edit
                  </button>
                  <button
                    className="text-red-600 hover:underline"
                    onClick={() => handleDeleteSection(section.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
            {sections.length === 0 && (
              <tr>
                <td colSpan={3} className="text-center py-6 text-gray-500">
                  No sections available.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {/* Modal */}
      {showModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-lg p-6 w-full max-w-md">
            <h2 className="text-xl font-semibold mb-4">
              {isEditMode ? 'Edit Section' : 'Create New Section'}
            </h2>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                isEditMode ? handleUpdateSection() : handleCreateSection();
              }}
              className="flex flex-col gap-3"
            >
              <label className="text-sm font-medium">
                Section Name
                <input
                  type="text"
                  value={formData.name}
                  onChange={(e) =>
                    setFormData({ ...formData, name: e.target.value })
                  }
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Number of Students
                <input
                  type="number"
                  value={formData.studentCount}
                  onChange={(e) =>
                    setFormData({
                      ...formData,
                      studentCount: Number(e.target.value),
                    })
                  }
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                  min={1}
                />
              </label>
              <div className="flex justify-end gap-3 mt-4">
                <button
                  type="button"
                  className="text-gray-500 hover:text-gray-700"
                  onClick={resetForm}
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                >
                  {isEditMode ? 'Update Section' : 'Create Section'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
