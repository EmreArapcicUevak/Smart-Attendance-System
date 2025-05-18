'use client';

import React, { useState, useEffect } from 'react';

interface TimeInterval {
  startTime: string;
  endTime: string;
  description: string;
}

export default function AddTimeIntervalPage() {
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const [description, setDescription] = useState('');
  const [message, setMessage] = useState('');
  const [intervals, setIntervals] = useState<TimeInterval[]>([]);

  // Load intervals from local storage
  useEffect(() => {
    const storedIntervals = localStorage.getItem('timeIntervals');
    if (storedIntervals) {
      setIntervals(JSON.parse(storedIntervals));
    }
  }, []);

  // Save intervals to local storage
  const saveIntervals = (newIntervals: TimeInterval[]) => {
    localStorage.setItem('timeIntervals', JSON.stringify(newIntervals));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    // Validation: Check if end time is after start time
    if (new Date(`1970-01-01T${endTime}`) <= new Date(`1970-01-01T${startTime}`)) {
      setMessage('End time must be after start time.');
      return;
    }

    const newInterval = { startTime, endTime, description };
    const updatedIntervals = [...intervals, newInterval];

    // Update state and local storage
    setIntervals(updatedIntervals);
    saveIntervals(updatedIntervals);

    setMessage(`Time Interval Added: ${startTime} - ${endTime} (${description})`);
    setStartTime('');
    setEndTime('');
    setDescription('');
  };

  const handleDelete = (index: number) => {
    const updatedIntervals = intervals.filter((_, i) => i !== index);
    setIntervals(updatedIntervals);
    saveIntervals(updatedIntervals);
    setMessage('Time interval deleted.');
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-[#EFF1FA] p-6 text-black">
      <div className="w-full max-w-2xl bg-white p-8 rounded-xl shadow-lg border border-gray-200">
        <h2 className="text-3xl font-semibold text-[#3553B5] mb-6">Add Time Interval</h2>

        {message && (
          <div className="mb-4 p-3 bg-green-100 border border-green-300 rounded-lg text-green-700 text-sm">
            {message}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Start Time</label>
            <input
              type="time"
              value={startTime}
              onChange={(e) => setStartTime(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">End Time</label>
            <input
              type="time"
              value={endTime}
              onChange={(e) => setEndTime(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Description</label>
            <input
              type="text"
              placeholder="Interval Description (e.g., Morning Shift)"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-[#3553B5] text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition"
          >
            Add Interval
          </button>
        </form>

        {/* Display Added Time Intervals */}
        {intervals.length > 0 && (
          <div className="mt-8">
            <h3 className="text-2xl font-semibold text-[#3553B5] mb-4">Added Time Intervals</h3>
            <table className="min-w-full bg-white text-left text-sm border border-gray-300 rounded-lg">
              <thead className="bg-gray-200">
                <tr>
                  <th className="px-4 py-2">Start Time</th>
                  <th className="px-4 py-2">End Time</th>
                  <th className="px-4 py-2">Description</th>
                  <th className="px-4 py-2">Actions</th>
                </tr>
              </thead>
              <tbody>
                {intervals.map((interval, index) => (
                  <tr key={index} className="border-t hover:bg-gray-100">
                    <td className="px-4 py-2">{interval.startTime}</td>
                    <td className="px-4 py-2">{interval.endTime}</td>
                    <td className="px-4 py-2">{interval.description}</td>
                    <td className="px-4 py-2">
                      <button
                        onClick={() => handleDelete(index)}
                        className="text-sm bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}
