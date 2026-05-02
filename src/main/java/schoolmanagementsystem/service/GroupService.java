package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolmanagementsystem.entity.*;
import schoolmanagementsystem.repository.*;
import schoolmanagementsystem.request.ClassCreateRequest;
import schoolmanagementsystem.request.ExamCreateRequest;
import schoolmanagementsystem.request.GroupCreateRequest;
import schoolmanagementsystem.request.TimetableRequest;

@Service
public class GroupService {

    private StudentGroupRepository studentGroupRepository;
    private StudentRepository studentRepository;
    private SchoolClassRepository schoolClassRepository;
    private TimetableRepository timetableRepository;
    private ExamRepository examRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public GroupService(StudentGroupRepository studentGroupRepository,
                        StudentRepository studentRepository,
                        TimetableRepository timetableRepository,
                        ExamRepository examRepository,
                        SubjectRepository subjectRepository,
                        TeacherRepository teacherRepository) {
        this.studentGroupRepository = studentGroupRepository;
        this.studentRepository = studentRepository;
        this.timetableRepository = timetableRepository;
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public void createGroup(GroupCreateRequest request) {
        StudentGroup group = new StudentGroup();
        group.setName(request.getName());
        group.setGradeLevel(request.getGradeLevel());
        group.setAcademicYear(request.getAcademicYear());

        studentGroupRepository.save(group);
    }

    @Transactional
    public void addStudentToGroup(Long groupId, Long studentId) {
        StudentGroup group = studentGroupRepository.findById(groupId).orElseThrow();
        Student student = studentRepository.findById(studentId).orElseThrow();

        group.getStudents().add(student);
        studentGroupRepository.save(group);
    }

    @Transactional
    public void removeStudentFromGroup(Long groupId, Long studentId) {
        StudentGroup group = studentGroupRepository.findById(groupId).orElseThrow();

        group.getStudents().removeIf(s -> s.getId().equals(studentId));
        studentGroupRepository.save(group);
    }

    @Transactional
    public void createTimetable(TimetableRequest request) {
        TimeTable timetable = new TimeTable();

        timetable.setGroup(studentGroupRepository.findByName(request.getGroupName()).orElseThrow());
        timetable.setSubject(subjectRepository.findByName(request.getSubjectName()).orElseThrow());
        timetable.setTeacher(teacherRepository.findByUserUsername(request.getTeacherUsername()).orElseThrow());
        timetable.setDayOfWeek(request.getDayOfWeek());
        timetable.setStartTime(request.getStartTime());
        timetable.setEndTime(request.getEndTime());
        timetable.setRoomNumber(request.getRoomNumber());

        timetableRepository.save(timetable);
    }

    @Transactional
    public void createExam(ExamCreateRequest request) {
        Exam exam = new Exam();

        exam.setGroup(studentGroupRepository.findByName(request.getGroupName()).orElseThrow());
        exam.setSubject(subjectRepository.findByName(request.getSubjectName()).orElseThrow());
        exam.setExamDate(request.getExamDate());
        exam.setMaxScore(request.getMaxScore());

        examRepository.save(exam);
    }

    @Transactional
    public void createClass(ClassCreateRequest request) {
        StudentGroup group = studentGroupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setGroup(group);
        schoolClass.setSubject(subject);
        schoolClass.setTeacher(teacher);
        schoolClass.setClassDate(request.getClassDate());
        schoolClass.setStartTime(request.getStartTime());
        schoolClass.setEndTime(request.getEndTime());
        schoolClass.setRoomNumber(request.getRoomNumber());
        schoolClass.setTopic(request.getTopic());
        schoolClass.setDescription(request.getDescription());

        schoolClassRepository.save(schoolClass);
    }
}
