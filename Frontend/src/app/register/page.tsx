'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function RegisterPage() {
  const router = useRouter();

  // Form state
  const [organizationName, setOrganizationName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');

  // Form submission handler
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

      if (!response.ok) {
        throw new Error('Registration failed');
      }

      setMessage('Organization registered successfully!');
      setOrganizationName('');
      setEmail('');
      setPassword('');

      // Redirect to login after successful registration
      setTimeout(() => router.push('/'), 1500);
    } catch (error) {
      setMessage('Error during registration. Please try again.');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-semibold text-[#3553B5] mb-6 text-center">Register</h2>


        {message && (
          <div className="mb-4 p-2 text-sm text-white bg-red-500 rounded">
            {message}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700">Organization Name</label>
            <input
              type="text"
              placeholder="Your organization name"
              value={organizationName}
              onChange={(e) => setOrganizationName(e.target.value)}
              className="w-full px-4 py-2 border rounded-md text-sm"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">Email</label>
            <input
              type="email"
              placeholder="you@example.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-2 border rounded-md text-sm"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">Password</label>
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-2 border rounded-md text-sm"
              required
            />
          </div>

          <button
            type="submit"
            className="w-full py-2 bg-[#3553B5] text-white rounded-md hover:bg-blue-700"
          >
            Register
          </button>
        </form>

        <div className="mt-4 text-sm text-center">
  Already have an account?{' '}
  <button
    onClick={() => router.push('/login')}
    className="text-[#3553B5] hover:underline"
  >
    Back to Login
  </button>
        </div>
      </div>
    </div>
  );
}
