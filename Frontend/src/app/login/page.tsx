'use client';

import { useRouter } from 'next/navigation';

export default function HomePage() {
  const router = useRouter();

  return (
    <div className="min-h-screen flex items-center justify-center bg-white px-4">
      <div className="bg-white shadow-md rounded-2xl w-full max-w-md p-8">
        <h1 className="text-3xl font-bold text-center text-black mb-8">Login</h1>

        <form className="space-y-6">
          {/* Email Input */}
          <div>
            <label htmlFor="email" className="block text-sm font-medium text-[#AAA6A6] mb-1">
              Email
            </label>
            <input
              id="email"
              type="email"
              required
              placeholder="you@example.com"
              className="w-full px-4 py-3 rounded-lg bg-[#D9D9D9] text-black placeholder-gray-600 focus:outline-none focus:ring-2 focus:ring-[#3553B5]"
            />
          </div>

          {/* Password Input */}
          <div>
            <label htmlFor="password" className="block text-sm font-medium text-[#AAA6A6] mb-1">
              Password
            </label>
            <input
              id="password"
              type="password"
              required
              placeholder="••••••••"
              className="w-full px-4 py-3 rounded-lg bg-[#D9D9D9] text-black placeholder-gray-600 focus:outline-none focus:ring-2 focus:ring-[#3553B5]"
            />
          </div>

          {/* Login Button */}
          <button
            type="submit"
            className="w-full bg-[#3553B5] text-white py-3 rounded-lg font-semibold hover:bg-[#2d4799] transition"
          >
            Login
          </button>

        

          {/* Register Button */}
          <button
            type="button"
            className="w-full border border-[#3553B5] text-[#3553B5] py-3 rounded-lg font-semibold hover:bg-[#EFF1FA] transition"
            onClick={() => router.push('/register')}
          >
            Create Organization
          </button>
        </form>
      </div>
    </div>
  );
}
