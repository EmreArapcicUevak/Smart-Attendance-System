'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';

interface Organization {
  id: number;
  name: string;
}

export default function OrganizationSettings() {
  const router = useRouter();
  const [organizations, setOrganizations] = useState<Organization[]>([]);
  const [search, setSearch] = useState('');
  const [editingIndex, setEditingIndex] = useState<number | null>(null);
  const [editData, setEditData] = useState<{ name: string }>({ name: '' });

  useEffect(() => {
    fetch('http://localhost:8080/api/organizations')
      .then((res) => res.json())
      .then(setOrganizations)
      .catch(() => {
        setOrganizations([
          { id: 0, name: 'International University of Sarajevo' },
        ]);
      });
  }, []);

  const filteredOrganizations = organizations.filter((org) =>
    org.name.toLowerCase().includes(search.toLowerCase())
  );

  const startEditing = (index: number) => {
    setEditingIndex(index);
    setEditData({ name: organizations[index].name });
  };

  const saveEdit = () => {
    if (editingIndex === null) return;
    const updated = [...organizations];
    updated[editingIndex] = {
      ...updated[editingIndex],
      name: editData.name,
    };
    setOrganizations(updated);
    setEditingIndex(null);
    // Optional: Add PUT to backend later using updated[editingIndex].id
  };

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-6 md:p-10 text-black font-sans">
      <div className="max-w-7xl mx-auto">
        <div className="flex justify-end mb-6">
          <button
            onClick={() => router.push('/admin/dashboard')}
            className="mb-6 px-4 py-2 bg-[#3553B5] text-white rounded hover:bg-blue-700"
          >
            ← Back to Dashboard
          </button>
        </div>

        <div className="flex flex-col items-center text-center mb-8">
          <h1 className="text-4xl font-bold text-[#3553B5] mb-2">Organization Settings</h1>
          <p className="text-sm text-gray-600 mb-4">
            View and manage all organizations in the system.
          </p>
          <input
            type="text"
            placeholder="Search organizations..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full md:w-1/2 border px-4 py-2 rounded shadow-sm bg-white"
          />
        </div>

        {/* Organization Grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredOrganizations.map((org, idx) => (
            <div
              key={org.id}
              className="bg-white rounded-xl shadow-md border p-5 transition hover:shadow-lg"
            >
              {editingIndex === idx ? (
                <>
                  <input
                    type="text"
                    value={editData.name}
                    onChange={(e) => setEditData({ name: e.target.value })}
                    className="w-full mb-2 border px-3 py-2 rounded text-sm"
                    placeholder="Organization Name"
                  />
                  <button
                    onClick={saveEdit}
                    className="text-sm text-green-600 hover:text-green-800 underline"
                  >
                    Save
                  </button>
                </>
              ) : (
                <>
                  <div className="mb-2">
                    <p className="text-md text-gray-800 font-medium">{org.name}</p>
                  </div>
                  <button
                    className="text-sm text-blue-600 hover:text-blue-800 underline"
                    onClick={() => startEditing(idx)}
                  >
                    Edit Settings
                  </button>
                </>
              )}
            </div>
          ))}
        </div>

        {filteredOrganizations.length === 0 && (
          <div className="text-center text-gray-500 text-sm mt-6">
            No organizations match your search.
          </div>
        )}
      </div>
    </div>
  );
}
