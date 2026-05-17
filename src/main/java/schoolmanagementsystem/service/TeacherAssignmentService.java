package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolmanagementsystem.entity.Assignment;
import schoolmanagementsystem.entity.StudentGroup;
import schoolmanagementsystem.entity.Subject;
import schoolmanagementsystem.entity.Teacher;
import schoolmanagementsystem.repository.*;
import schoolmanagementsystem.request.CreateAssignmentRequest;

@Service
public class TeacherAssignmentService {

    private AssignmentRepository assignmentRepository;
    private TeacherRepository teacherRepository;
    private StudentGroupRepository groupRepository;
    private SubjectRepository subjectRepository;
    private TimetableRepository timetableRepository;

    @Autowired
    public TeacherAssignmentService(AssignmentRepository assignmentRepository,
                                    TeacherRepository teacherRepository,
                                    StudentGroupRepository groupRepository,
                                    SubjectRepository subjectRepository,
                                    TimetableRepository timetableRepository) {
        this.assignmentRepository = assignmentRepository;
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.timetableRepository = timetableRepository;
    }

    @Transactional
    public void createAssignment(String username, Long groupId, CreateAssignmentRequest request) {
        boolean hasAccess = timetableRepository.teacherHasAccessToGroup(username, groupId);

        if (!hasAccess) {
            throw new RuntimeException("You do not teach this group");
        }

        Teacher teacher = teacherRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        StudentGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Subject subject = subjectRepository.findByName(request.getSubjectName())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Assignment assignment = new Assignment();
        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDueDate(request.getDueDate());
        assignment.setMaxScore(request.getMaxScore());
        assignment.setTeacher(teacher);
        assignment.setGroup(group);
        assignment.setSubject(subject);
        assignmentRepository.save(assignment);
    }
}
