'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

type Session = {
  id: number;
  type: string;
  topic: string;
};

export default function ManageSessionsPage() {
  const [sessions, setSessions] = useState<Session[]>([
    { id: 1, type: 'Lecture', topic: 'React Basics' },
    { id: 2, type: 'Lab', topic: 'Component Design' },
  ]);
  const [showModal, setShowModal] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);
  const [editSessionId, setEditSessionId] = useState<number | null>(null);
  const [formData, setFormData] = useState({ type: '', topic: '' });

  const router = useRouter();

  const resetForm = () => {
    setFormData({ type: '', topic: '' });
    setIsEditMode(false);
    setEditSessionId(null);
    setShowModal(false);
  };

  const handleCreateSession = () => {
    const newSession: Session = {
      id: Date.now(),
      type: formData.type.trim(),
      topic: formData.topic.trim(),
    };
    setSessions([...sessions, newSession]);
    resetForm();
  };

  const handleUpdateSession = () => {
    setSessions((prev) =>
      prev.map((session) =>
        session.id === editSessionId
          ? { ...session, type: formData.type.trim(), topic: formData.topic.trim() }
          : session
      )
    );
    resetForm();
  };

  const handleDeleteSession = (id: number) => {
    setSessions((prev) => prev.filter((session) => session.id !== id));
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
        <h1 className="text-3xl font-bold">Manage Sessions</h1>
        <p className="text-gray-600 mt-1">Organize lab and tutorial sessions.</p>
      </div>

      <div className="flex justify-end mb-4">
        <button
          onClick={() => {
            setIsEditMode(false);
            setFormData({ type: '', topic: '' });
            setShowModal(true);
          }}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          + Create Session
        </button>
      </div>

      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-300 rounded text-sm">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left">Session Type</th>
              <th className="px-4 py-2 text-left">Topic</th>
              <th className="px-4 py-2 text-left">Actions</th>
            </tr>
          </thead>
          <tbody>
            {sessions.map((session) => (
              <tr key={session.id} className="hover:bg-gray-50 border-t">
                <td className="px-4 py-2">{session.type}</td>
                <td className="px-4 py-2">{session.topic}</td>
                <td className="px-4 py-2 flex gap-3">
                  <button
                    className="text-blue-600 hover:underline"
                    onClick={() => {
                      setIsEditMode(true);
                      setEditSessionId(session.id);
                      setFormData({ type: session.type, topic: session.topic });
                      setShowModal(true);
                    }}
                  >
                    Edit
                  </button>
                  <button
                    className="text-red-600 hover:underline"
                    onClick={() => handleDeleteSession(session.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
            {sessions.length === 0 && (
              <tr>
                <td colSpan={3} className="text-center py-6 text-gray-500">
                  No sessions available.
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
              {isEditMode ? 'Edit Session' : 'Create New Session'}
            </h2>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                isEditMode ? handleUpdateSession() : handleCreateSession();
              }}
              className="flex flex-col gap-3"
            >
              <label className="text-sm font-medium">
                Session Type
                <input
                  type="text"
                  value={formData.type}
                  onChange={(e) => setFormData({ ...formData, type: e.target.value })}
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
                />
              </label>
              <label className="text-sm font-medium">
                Topic
                <input
                  type="text"
                  value={formData.topic}
                  onChange={(e) => setFormData({ ...formData, topic: e.target.value })}
                  className="border px-3 py-1 rounded w-full mt-1"
                  required
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
                  {isEditMode ? 'Update Session' : 'Create Session'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
