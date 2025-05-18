'use client';

import React from 'react';
import Timetable from '../../../../components/timetable';

export default function TimetablePage() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="w-full max-w-4xl p-6">
        <Timetable />
      </div>
    </div>
  );
}
