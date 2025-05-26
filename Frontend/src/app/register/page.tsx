'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function RegisterPage() {
  const router = useRouter();

  const [name, setName] = useState('');
  const [adminEmail, setAdminEmail] = useState('');
  const [adminPassword, setAdminPassword] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!name || !adminEmail || !adminPassword) {
      setMessage('All fields are required.');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/organizations', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, adminEmail, adminPassword }),
      });

      if (!response.ok) throw new Error('Registration failed');

      setMessage('Organization registered successfully!');
      setName('');
      setAdminEmail('');
      setAdminPassword('');

      setTimeout(() => router.push('/login'), 1500);
    } catch (error) {
      setMessage('Error during registration. Please try again.');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h2 className="text-2xl font-bold text-center text-[#3553B5] mb-2">
          Create Your Organization
        </h2>
        {message && (
          <div className="mb-4 p-2 text-sm text-white bg-red-500 rounded">
            {message}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Organization Name</label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Your organization"
              className="w-full px-4 py-2 border rounded-md text-sm text-gray-700 mb-1"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Admin Email</label>
            <input
              type="email"
              value={adminEmail}
              onChange={(e) => setAdminEmail(e.target.value)}
              placeholder="you@example.com"
              className="w-full px-4 py-2 border rounded-md text-sm text-gray-700 mb-1"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Admin Password</label>
            <input
              type="password"
              value={adminPassword}
              onChange={(e) => setAdminPassword(e.target.value)}
              placeholder="••••••••"
              className="w-full px-4 py-2 border rounded-md text-sm text-gray-700 mb-1"
              required
            />
          </div>

          <button
            type="submit"
            className="w-full py-2 bg-[#3553B5] text-white rounded-md text-sm font-medium hover:bg-blue-700 transition"
          >
            Create Organization
          </button>
        </form>

        <div className="mt-6 text-center text-sm">
          <span className="text-gray-600">Already have an account?</span>{' '}
          <button
            onClick={() => router.push('/login')}
            className="text-[#3553B5] font-medium hover:underline"
          >
            Log in
          </button>
        </div>
      </div>
    </div>
  );
}
