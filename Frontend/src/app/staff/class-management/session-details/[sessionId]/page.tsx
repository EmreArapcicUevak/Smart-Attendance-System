'use client';

import { useSearchParams } from 'next/navigation';
import StudentList from '../../../../../../components/StudentList';

export default function SessionDetailsPage({ params }: { params: { sessionId: string } }) {
  const searchParams = useSearchParams();
  const topic = searchParams.get('topic');

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-4">
        Session {params.sessionId} {topic ? `- ${topic}` : ''}
      </h1>
      <StudentList sessionId={params.sessionId} topic={topic ?? undefined} />
    </div>
  );
}

