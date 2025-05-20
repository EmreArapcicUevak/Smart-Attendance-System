'use client';

import { useRouter } from 'next/navigation';

export default function ClassManagementPage() {
  const router = useRouter();

  const sections = [
    { name: 'Section A', students: 25 },
    { name: 'Section B', students: 30 },

  ];
  

  const sessions = [
    { type: 'Lecture', topic: 'React Basics' },
    { type: 'Lab', topic: 'Component Design' },
  ];
  
  
  const slugify = (name: string) => name.toUpperCase().replace(/\s+/g, '-');

  return (
    <div className="min-h-screen flex bg-white text-black font-sans">
      {/* Sidebar */}
      <aside className="w-64 bg-[#3553B5] text-white p-6 flex flex-col gap-6">
        <h2 className="text-2xl font-bold">Smart Attendance</h2>
        <nav className="flex flex-col gap-4 text-lg">
          <button className="text-left hover:text-gray-200" onClick={() => router.push('/staff/dashboard')}>
            Back to Dashboard
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

      {/* Main */}
      <main className="flex-1 p-10">
        <h1 className="text-3xl font-bold mb-6">Class Management</h1>

        {/* Manage Sections/Sessions/Personal */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
        <div
  className="bg-[#D9D9D9] rounded-xl p-6 cursor-pointer hover:bg-[#c6c6c6]"
  onClick={() => router.push('/staff/class-management/manage-sections')}
>
  <h2 className="text-xl font-semibold mb-2">Manage Sections</h2>
  <p className="text-sm text-[#AAA6A6]">Create, update, and delete class sections.</p>
</div>

<div
  className="bg-[#D9D9D9] rounded-xl p-6 cursor-pointer hover:bg-[#c6c6c6]"
  onClick={() => router.push('/staff/class-management/manage-sessions')}
>
  <h2 className="text-xl font-semibold mb-2">Manage Sessions</h2>
  <p className="text-sm text-[#AAA6A6]">Organize lab and tutorial sessions.</p>
</div>
        </div>
        {/* Sections Table */}
        <div className="mb-10">
          <h2 className="text-2xl font-bold mb-4">Sections</h2>
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
              <thead className="bg-[#D9D9D9]">
                <tr>
                  <th className="px-6 py-3 text-left">Section</th>
                  <th className="px-6 py-3 text-left">Students</th>
                  <th className="px-6 py-3 text-left">Actions</th>
                </tr>
              </thead>
              <tbody>
                {sections.map((section, idx) => (
                  <tr key={idx} className="hover:bg-[#EFF1FA] border-t border-gray-200">
                    <td className="px-6 py-4">{section.name}</td>
                    <td className="px-6 py-4">{section.students}</td>
                    <td className="px-6 py-4">
                    <button
  className="bg-[#3553B5] text-white px-4 py-1 rounded hover:bg-blue-700 text-sm"
  onClick={() => router.push(`/staff/class-management/class-details/${slugify(section.name)}`)}
>
  Class Details
</button>

                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        {/* Sessions Table */}
        <div>
          <h2 className="text-2xl font-bold mb-4">Sessions</h2>
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white text-sm text-black border border-gray-200 rounded-lg overflow-hidden">
              <thead className="bg-[#D9D9D9]">
                <tr>
                  <th className="px-6 py-3 text-left">Type</th>
                  <th className="px-6 py-3 text-left">Topic</th>
                  <th className="px-6 py-3 text-left">Actions</th>
                </tr>
              </thead>
              <tbody>
                {sessions.map((session, idx) => (
                  <tr key={idx} className="hover:bg-[#EFF1FA] border-t border-gray-200">
                    <td className="px-6 py-4">{session.type}</td>
                    <td className="px-6 py-4">{session.topic}</td>
                    <td className="px-6 py-4">
                    <button
  className="bg-[#3553B5] text-white px-4 py-1 rounded hover:bg-blue-700 text-sm"
  onClick={() => router.push(`/staff/class-management/session-details/${session.type}?topic=${encodeURIComponent(session.topic)}`)}

>
  Class Details
</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </main>
    </div>
  );
}


