'use client';

import { useState } from 'react';

export default function StudentSettingsPage() {
  const [section, setSection] = useState('');
  const [message, setMessage] = useState('');
  const [currentName, setCurrentName] = useState('Jane Doe');
  const [currentEmail, setCurrentEmail] = useState('janedoe@example.com');
  const [newName, setNewName] = useState('');
  const [newEmail, setNewEmail] = useState('');
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmNewPassword, setConfirmNewPassword] = useState('');

  const validateEmail = (email: string) =>
    /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);

  const handleUpdateName = () => {
    if (!newName.trim()) {
      setMessage('New name cannot be empty.');
      return;
    }
    setCurrentName(newName.trim());
    setNewName('');
    setMessage('Name updated successfully.');
  };

  const handleUpdateEmail = () => {
    if (!newEmail.trim()) {
      setMessage('New email cannot be empty.');
      return;
    }
    if (!validateEmail(newEmail.trim())) {
      setMessage('Invalid email format.');
      return;
    }
    setCurrentEmail(newEmail.trim());
    setNewEmail('');
    setMessage('Email updated successfully.');
  };

  const handleUpdatePassword = () => {
    if (!currentPassword || !newPassword || !confirmNewPassword) {
      setMessage('All password fields are required.');
      return;
    }
    if (newPassword !== confirmNewPassword) {
      setMessage('New passwords do not match.');
      return;
    }
    setCurrentPassword('');
    setNewPassword('');
    setConfirmNewPassword('');
    setMessage('Password updated successfully.');
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-[#EFF1FA] p-6 text-black font-sans">
      <div className="w-full max-w-2xl bg-white p-8 rounded-xl shadow-lg border border-gray-200">
        <div className="text-center mb-6">
          <h1 className="text-4xl font-bold text-[#3553B5]">⚙️ Student Settings</h1>
          <p className="text-sm text-gray-600 mt-2">
            Update your name, email, or password.
          </p>
        </div>

        {message && (
          <div className="mb-4 p-3 bg-green-100 border border-green-300 rounded-lg text-green-700 text-sm">
            {message}
          </div>
        )}

        <div className="mb-6">
          <label className="block mb-2 font-medium text-sm">Choose what to update:</label>
          <select
            value={section}
            onChange={(e) => {
              setMessage('');
              setSection(e.target.value);
            }}
            className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm bg-white"
          >
            <option value="">-- Select Option --</option>
            <option value="name">Change Name</option>
            <option value="email">Change Email</option>
            <option value="password">Change Password</option>
          </select>
        </div>

        {section === 'name' && (
          <div className="space-y-3">
            <p className="text-sm text-gray-600 mb-1">Current Name: <strong>{currentName}</strong></p>
            <input
              type="text"
              placeholder="New Name"
              value={newName}
              onChange={(e) => setNewName(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm"
            />
            <button
              onClick={handleUpdateName}
              className="w-full bg-[#3553B5] hover:bg-[#2b4291] text-white px-4 py-2 rounded-lg text-sm"
            >
              Update Name
            </button>
          </div>
        )}

        {section === 'email' && (
          <div className="space-y-3">
            <p className="text-sm text-gray-600 mb-1">Current Email: <strong>{currentEmail}</strong></p>
            <input
              type="email"
              placeholder="New Email"
              value={newEmail}
              onChange={(e) => setNewEmail(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm"
            />
            <button
              onClick={handleUpdateEmail}
              className="w-full bg-[#3553B5] hover:bg-[#2b4291] text-white px-4 py-2 rounded-lg text-sm"
            >
              Update Email
            </button>
          </div>
        )}

        {section === 'password' && (
          <div className="space-y-3">
            <input
              type="password"
              placeholder="Current Password"
              value={currentPassword}
              onChange={(e) => setCurrentPassword(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm"
            />
            <input
              type="password"
              placeholder="New Password"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm"
            />
            <input
              type="password"
              placeholder="Confirm New Password"
              value={confirmNewPassword}
              onChange={(e) => setConfirmNewPassword(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm"
            />
            <button
              onClick={handleUpdatePassword}
              className="w-full bg-[#3553B5] hover:bg-[#2b4291] text-white px-4 py-2 rounded-lg text-sm"
            >
              Update Password
            </button>
          </div>
        )}
      </div>
    </div>
  );
}
