'use client';

import React, { useState, useEffect } from 'react';

interface ClassEntry {
  id: string;
  name: string;
  schedule: string;
  day: string;
}


const classesData: ClassEntry[] = [
  { id: 'CS101', name: 'Computer Science', schedule: '08:00 - 09:30', day: 'Monday' },
  { id: 'MA202', name: 'Calculus II', schedule: '10:00 - 11:00', day: 'Monday' },
  { id: 'PH301', name: 'Physics', schedule: '11:30 - 12:30', day: 'Tuesday' },
];


const getCurrentDay = () => new Date().toLocaleString('en-US', { weekday: 'long' });
const getCurrentTime = () => new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

export default function StudentClassesList() {
  const [filter, setFilter] = useState<'Day' | 'Week'>('Day');
  const [currentDay, setCurrentDay] = useState<string>(getCurrentDay());
  const [currentTime, setCurrentTime] = useState<string>(getCurrentTime());

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentDay(getCurrentDay());
      setCurrentTime(getCurrentTime());
    }, 60000);
    return () => clearInterval(interval);
  }, []);

  const isOngoing = (schedule: string) => {
    const [start, end] = schedule.split(' - ');
    return currentTime >= start && currentTime <= end;
  };

  const getNextClass = () => {
    return classesData.find(
      (entry) => currentTime < entry.schedule.split(' - ')[0] && entry.day === currentDay
    );
  };

  const ongoingClass = classesData.find((entry) => isOngoing(entry.schedule) && entry.day === currentDay);
  const upcomingClass = getNextClass();

  return (
    <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-3xl mx-auto">
      <h2 className="text-3xl font-semibold text-[#3553B5] mb-4">Enrolled Classes</h2>

      {/* Filter Options */}
      <div className="flex gap-4 mb-6">
        {['Day', 'Week'].map((option) => (
          <button
            key={option}
            onClick={() => setFilter(option as 'Day' | 'Week' )}
            className={`px-4 py-2 rounded ${
              filter === option ? 'bg-[#3553B5] text-white' : 'bg-gray-200 text-gray-700'
            }`}
          >
            {option}
          </button>
        ))}
      </div>

      {/* Ongoing and Upcoming Classes */}
{ongoingClass ? (
  <div className="mb-4 p-3 bg-green-100 text-green-800 rounded-lg">
    Ongoing Class: <strong>{ongoingClass.name}</strong> ({ongoingClass.id})
  </div>
) : upcomingClass ? (
  <div className="mb-4 p-3 bg-blue-100 text-blue-800 rounded-lg">
    Next Class: <strong>{upcomingClass.name}</strong> ({upcomingClass.id}) at {upcomingClass.schedule}
  </div>
) : (
  <div className="mb-4 p-3 bg-yellow-100 text-yellow-800 rounded-lg">
    No ongoing or upcoming classes today.
  </div>
)}

      {/* Classes List */}
      <div className="grid gap-4">
        {classesData
          .filter((entry) => (filter === 'Day' ? entry.day === currentDay : true))
          .map((entry, index) => (
            <div
              key={index}
              className={`p-4 rounded-lg shadow-md ${
                isOngoing(entry.schedule) ? 'bg-[#EFF1FA] border-l-4 border-[#3553B5]' : 'bg-gray-50'
              }`}
            >
              <h3 className="text-xl font-bold text-[#3553B5]">{entry.id}</h3>
              <p className="text-sm text-gray-800 font-medium">{entry.name}</p>
              <p className="text-sm text-gray-600">Schedule: {entry.schedule}</p>
              <p className="text-sm text-gray-500">Day: {entry.day}</p>
            </div>
          ))}
      </div>
    </div>
  );
}
