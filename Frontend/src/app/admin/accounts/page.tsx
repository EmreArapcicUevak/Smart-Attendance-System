"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

interface Account {
  id: string;
  fullName: string;
  role: "student" | "staff";
  studentId?: string;
}

export default function AdminAccountsPage() {
  const router = useRouter();
  const [accounts, setAccounts] = useState<Account[]>([]);
  const [search, setSearch] = useState("");
  const [filter, setFilter] = useState("");

  const fetchAccounts = async (roleFilter: string) => {
    try {
      let url = "http://localhost:8080/api/user";
      if (roleFilter === "student") url += "/students";
      else if (roleFilter === "staff") url += "/staff";

      const response = await fetch(url);
      const data = await response.json();
      console.log("API Response:", data); // Debug API response
      setAccounts(Array.isArray(data) ? data : []); // Directly set the response array to accounts
    } catch (error) {
      console.error("Failed to fetch accounts:", error);
      setAccounts([]);
    }
  };

  useEffect(() => {
    fetchAccounts(filter);
  }, [filter]);

  const filteredAccounts = accounts.filter((account) =>
    account.fullName.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="min-h-screen bg-[#EFF1FA] p-10 text-black font-sans">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-4xl font-bold text-[#3553B5]">Accounts</h1>
        <button
          onClick={() => router.push("/admin/dashboard")}
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
          <option value="">All Roles</option>
          <option value="student">Student</option>
          <option value="staff">Staff</option>
        </select>
      </div>

      {/* Account Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredAccounts.map((account) => (
          <div
            key={account.id} // Use `id` from the API response as the unique key
            className="bg-white rounded-xl shadow-md border p-5 transition hover:shadow-lg"
          >
            <h3 className="text-xl font-bold text-[#3553B5]">
              {account.fullName}
            </h3>
            <p className="text-sm text-gray-600 capitalize">
              Role: {account.role}
            </p>
            {account.role === "student" && account.studentId && (
              <p className="text-sm text-gray-600">
                Student ID: {account.studentId}
              </p>
            )}
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
