'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';

interface Account {
  name: string;
  email: string;
  type: 'student' | 'Staff';
  studentId?: string;
  organizationId: string;
}

interface Course {
  code: string;
  name: string;
  faculty: string;
}

export default function AdminDashboard() {
  const router = useRouter();
  const [accounts, setAccounts] = useState<Account[]>([]);
  const [courses, setCourses] = useState<Course[]>([]);
  const [accountType, setAccountType] = useState<'student' | 'staff'>('student');
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    studentId: '',
    organizationId: '',
  });

  useEffect(() => {
    fetch('/api/accounts').then(res => res.json()).then(setAccounts);
    fetch('/api/courses').then(res => res.json()).then(setCourses);
  }, []);

  const handleFormChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleCreateAccount = async (e: React.FormEvent) => {
    e.preventDefault();
    const payload = { ...formData, type: accountType };
    const response = await fetch('/api/create-account', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload),
    });
    const result = await response.json();
    if (response.ok) {
      setAccounts([...accounts, result]);
      setFormData({
        name: '',
        email: '',
        password: '',
        studentId: '',
        organizationId: '',
      });
    }
  };

  return (
    <div className="min-h-screen flex bg-white text-black font-sans">
      {/* Sidebar */}
      <aside className="w-64 bg-[#3553B5] text-white p-6 flex flex-col gap-6">
        <h2 className="text-2xl font-bold">Admin Panel</h2>
        <nav className="flex flex-col gap-4 text-lg">
          <button className="text-left hover:text-gray-200" onClick={() => router.push('/admin/courses')}>
            Courses
          </button>
          <button className="text-left hover:text-gray-200" onClick={() => router.push('/admin/accounts')}>
            Accounts
          </button>
          <button className="text-left hover:text-gray-200" onClick={() => router.push('/admin/organizationSettings')}>
            Organization Settings
          </button>
        </nav>
        <div className="mt-auto">
          <button
            onClick={() => {
              localStorage.removeItem('authToken');
              router.push('/login');
            }}
            className="text-sm bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition"
          >
            Logout
          </button>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-10 bg-[#EFF1FA]">
        <div className="flex flex-col md:flex-row md:items-center justify-between mb-8 bg-white p-6 rounded-lg shadow-sm">
          <div>
            <h1 className="text-3xl font-bold text-[#3553B5]">Welcome Admin 👋</h1>
            <p className="text-sm text-gray-600 mt-1">Manage organization accounts and courses.</p>
          </div>
          <div className="mt-4 md:mt-0">
            <span className="bg-[#3553B5] text-white text-sm px-4 py-2 rounded-lg shadow">
              Admin Dashboard
            </span>
          </div>
        </div>

        {/* Create Account */}
        <div className="bg-white p-6 rounded-lg shadow mb-10 w-full">
          <h2 className="text-2xl font-semibold mb-4">Create Account</h2>
          <form onSubmit={handleCreateAccount} className="space-y-4">
            <select
              value={accountType}
              onChange={(e) => setAccountType(e.target.value as 'student' | 'staff')}
              className="w-full border px-4 py-2 rounded"
            >
              <option value="student">Student</option>
              <option value="staff">Staff</option>
            </select>
            <input
              type="text"
              name="name"
              placeholder="Name"
              value={formData.name}
              onChange={handleFormChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            <input
              type="email"
              name="email"
              placeholder="Email"
              value={formData.email}
              onChange={handleFormChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            <input
              type="password"
              name="password"
              placeholder="Password"
              value={formData.password}
              onChange={handleFormChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            <input
              type="text"
              name="organizationId"
              placeholder="Organization ID"
              value={formData.organizationId}
              onChange={handleFormChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            {accountType === 'student' && (
              <input
                type="text"
                name="studentId"
                placeholder="Student ID"
                value={formData.studentId}
                onChange={handleFormChange}
                className="w-full border px-4 py-2 rounded"
                required
              />
            )}
            <button type="submit" className="bg-[#3553B5] text-white px-6 py-2 rounded">
              Create Account
            </button>
          </form>
        </div>
      </main>
    </div>
  );
}
