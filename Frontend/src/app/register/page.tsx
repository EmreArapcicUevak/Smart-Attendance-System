'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function RegisterPage() {
  const router = useRouter();

  const [organizationName, setOrganizationName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!organizationName || !email || !password) {
      setMessage('All fields are required.');
      return;
    }

    try {
      const response = await fetch('/api/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ organizationName, email, password }),
      });

      if (!response.ok) throw new Error('Registration failed');

      setMessage('Organization registered successfully!');
      setOrganizationName('');
      setEmail('');
      setPassword('');

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
              value={organizationName}
              onChange={(e) => setOrganizationName(e.target.value)}
              placeholder="Your organization"
              className="w-full px-4 py-2 border rounded-md text-sm text-gray-700 mb-1"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="you@example.com"
              className="w-full px-4 py-2 border rounded-md text-sm text-gray-700 mb-1"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
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
