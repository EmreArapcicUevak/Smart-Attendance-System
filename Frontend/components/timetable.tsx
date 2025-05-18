'use client';

import React, { useState, useEffect } from 'react';

interface ClassEntry {
  subject: string;
  time: string;
  instructor: string;
  day: string;
}

const timetableData: ClassEntry[] = [
  { subject: 'Mathematics', time: '08:00 - 09:00', instructor: 'Mr. Smith', day: 'Monday' },
  { subject: 'English', time: '09:15 - 10:15', instructor: 'Ms. Johnson', day: 'Monday' },
  { subject: 'Physics', time: '10:30 - 11:30', instructor: 'Dr. Brown', day: 'Tuesday' },
  { subject: 'History', time: '12:00 - 13:00', instructor: 'Mr. Lee', day: 'Wednesday' },
  { subject: 'Chemistry', time: '14:00 - 15:00', instructor: 'Dr. Green', day: 'Thursday' },
  { subject: 'Biology', time: '15:30 - 16:30', instructor: 'Ms. White', day: 'Friday' },
];

const getCurrentDay = () => new Date().toLocaleString('en-US', { weekday: 'long' });
const getCurrentTime = () => new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

export default function Timetable() {
  const [filter, setFilter] = useState<'Day' | 'Week' | 'Month'>('Day');
  const [currentDay, setCurrentDay] = useState<string>(getCurrentDay());
  const [currentTime, setCurrentTime] = useState<string>(getCurrentTime());

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentDay(getCurrentDay());
      setCurrentTime(getCurrentTime());
    }, 60000);
    return () => clearInterval(interval);
  }, []);

  const isOngoing = (time: string) => {
    const [start, end] = time.split(' - ');
    return currentTime >= start && currentTime <= end;
  };

  const getNextClass = () => {
    return timetableData.find(
      (entry) => currentTime < entry.time.split(' - ')[0] && entry.day === currentDay
    );
  };

  const upcomingClass = getNextClass();
  const ongoingClass = timetableData.find((entry) => isOngoing(entry.time) && entry.day === currentDay);

  return (
    <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-3xl mx-auto">
      <h2 className="text-3xl font-semibold text-[#3553B5] mb-4">Timetable</h2>

      {/* Filter Options */}
      <div className="flex gap-4 mb-6">
        {['Day', 'Week', 'Month'].map((option) => (
          <button
            key={option}
            onClick={() => setFilter(option as 'Day' | 'Week' | 'Month')}
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
          Ongoing Class: {ongoingClass.subject} with {ongoingClass.instructor}
        </div>
      ) : upcomingClass ? (
        <div className="mb-4 p-3 bg-blue-100 text-blue-800 rounded-lg">
          Next Class: {upcomingClass.subject} with {upcomingClass.instructor} at {upcomingClass.time}
        </div>
      ) : (
        <div className="mb-4 p-3 bg-yellow-100 text-yellow-800 rounded-lg">
          No ongoing or upcoming classes today.
        </div>
      )}

      {/* Timetable Display */}
      <div className="grid gap-4">
        {timetableData
          .filter((entry) => (filter === 'Day' ? entry.day === currentDay : true))
          .map((entry, index) => (
            <div
              key={index}
              className={`p-4 rounded-lg shadow-md ${
                isOngoing(entry.time) ? 'bg-[#EFF1FA] border-l-4 border-[#3553B5]' : 'bg-gray-50'
              }`}
            >
              <h3 className="text-xl font-bold">{entry.subject}</h3>
              <p className="text-sm text-gray-600">Time: {entry.time}</p>
              <p className="text-sm text-gray-600">Instructor: {entry.instructor}</p>
              <p className="text-sm text-gray-500">Day: {entry.day}</p>
            </div>
          ))}
      </div>
    </div>
  );
}
