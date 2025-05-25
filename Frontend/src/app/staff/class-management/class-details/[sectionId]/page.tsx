import StudentList from '../../../../../../components/StudentList';

export default function SectionDetailsPage({ params }: { params: { sectionId: string } }) {
  return (
    <div className="p-6">
      <StudentList sectionId={params.sectionId} />
    </div>
  );
}
