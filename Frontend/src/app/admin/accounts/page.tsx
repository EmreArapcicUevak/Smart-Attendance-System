'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';

interface Account {
  name: string;
  email: string;
  type: 'student' | 'staff';
  studentId?: string;
}

export default function AdminAccountsPage() {
  const router = useRouter();
  const [accounts, setAccounts] = useState<Account[]>([]);
  const [search, setSearch] = useState('');
  const [filter, setFilter] = useState('');

  useEffect(() => {
    fetch('/api/accounts')
      .then(res => res.json())
      .then(setAccounts)
      .catch(() => {
        setAccounts([]);
      });
  }, []);

  const filteredAccounts = accounts
    .filter(account =>
      account.name.toLowerCase().includes(search.toLowerCase())
    )
    .filter(account => !filter || account.type === filter);

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-10 text-black font-sans">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-4xl font-bold text-[#3553B5]">Accounts</h1>
        <button
          onClick={() => router.push('/admin/dashboard')}
          className="px-4 py-2 bg-[#3553B5] text-white rounded hover:bg-blue-700"
        >
          ← Back to Dashboard
        </button>
      </div>

      {/* Search & Filter */}
      <div className="mb-6 flex flex-col md:flex-row md:items-center gap-4">
        <input
          type="text"
          placeholder="Search accounts..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="flex-1 border px-4 py-2 rounded shadow-sm bg-white"
        />
        <select
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
          className="w-full md:w-60 border px-4 py-2 rounded shadow-sm bg-white"
        >
          <option value="">All Types</option>
          <option value="student">Student</option>
          <option value="staff">Staff</option>
        </select>
      </div>

      {/* Account Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredAccounts.map((account, idx) => (
          <div
            key={idx}
            className="bg-white rounded-xl shadow-md border p-5 transition hover:shadow-lg"
          >
            <h3 className="text-xl font-bold text-[#3553B5]">{account.name}</h3>
            <p className="text-sm text-gray-600 capitalize">Type: {account.type}</p>
            {account.type === 'student' && (
              <p className="text-sm text-gray-600">Student ID: {account.studentId}</p>
            )}
            <p className="text-sm text-gray-600">Email: {account.email}</p>
          </div>
        ))}

        {filteredAccounts.length === 0 && (
          <div className="text-gray-500 text-sm col-span-full">
            No accounts match your search and filter criteria.
          </div>
        )}
      </div>
    </div>
  );
}
