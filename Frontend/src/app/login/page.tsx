"use client";

import { useRouter } from "next/navigation";

export default function HomePage() {
  const router = useRouter();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const res = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });

      if (!res.ok) {
        const errorText = await res.text();
        alert("❌ Login failed: " + errorText);
        return;
      }

      const data = await res.json();

      // Store token and user ID
      localStorage.setItem("authToken", data.token);
      localStorage.setItem("userId", data.id);

      // Fetch role from backend
      const roleResponse = await fetch("http://localhost:8080/auth/role", {
        headers: {
          Authorization: `Bearer ${data.token}`,
        },
      });

      if (roleResponse.ok) {
        const role = await roleResponse.text();
        localStorage.setItem("userRole", role);

        // Role-based redirection
        if (role === "STUDENT") {
          router.push("/student/dashboard");
        } else if (role === "STAFF") {
          router.push("/staff/dashboard");
        } else if (role === "ADMIN") {
          router.push("/admin/dashboard");
        } else {
          alert("❌ Unknown user role. Please contact support.");
        }
      } else {
        console.error("Failed to fetch role:", await roleResponse.text());
      }
    } catch (err: any) {
      alert("❌ Error: " + err.message);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-white px-4">
      <div className="bg-white shadow-md rounded-2xl w-full max-w-md p-8">
        <h1 className="text-3xl font-bold text-center text-black mb-8">
          Login
        </h1>

        <form className="space-y-6">
          {/* Email Input */}
          <div>
            <label
              htmlFor="email"
              className="block text-sm font-medium text-[#AAA6A6] mb-1"
            >
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
            <label
              htmlFor="password"
              className="block text-sm font-medium text-[#AAA6A6] mb-1"
            >
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
            onClick={() => router.push("/register")}
          >
            Register
          </button>

          {/* Create Organization Button */}
          <button
            type="button"
            className="w-full border border-[#AAA6A6] text-[#AAA6A6] py-3 rounded-lg font-semibold hover:bg-gray-100 transition"
            onClick={() => router.push("/create-organization")}
          >
            Create Organization
          </button>
        </form>
      </div>
    </div>
  );
}
