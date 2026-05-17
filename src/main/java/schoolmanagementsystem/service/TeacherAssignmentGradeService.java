package schoolmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolmanagementsystem.dto.StudentOptionDto;
import schoolmanagementsystem.entity.Assignment;
import schoolmanagementsystem.entity.Grade;
import schoolmanagementsystem.entity.Student;
import schoolmanagementsystem.mapper.StudentMapper;
import schoolmanagementsystem.repository.AssignmentRepository;
import schoolmanagementsystem.repository.GradeRepository;
import schoolmanagementsystem.repository.StudentRepository;
import schoolmanagementsystem.request.CreateAssignmentGradeRequest;

import java.util.List;

@Service
public class TeacherAssignmentGradeService {

    private AssignmentRepository assignmentRepository;
    private StudentRepository studentRepository;
    private GradeRepository gradeRepository;

    public TeacherAssignmentGradeService(AssignmentRepository assignmentRepository,
                                         StudentRepository studentRepository,
                                         GradeRepository gradeRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<StudentOptionDto> getStudentsForAssignment(String username, Long assignmentId) {
        boolean hasAccess = assignmentRepository.teacherOwnsAssignment(assignmentId, username);
        if (!hasAccess) {
            throw new RuntimeException("Access denied");
        }
        Assignment assignment = assignmentRepository.findDetailedById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        return assignment.getGroup()
                .getStudents()
                .stream()
                .map(StudentMapper::toStudentOptionDto)
                .toList();
    }

    @Transactional
    public void addAssignmentGrade(String username, Long assignmentId, CreateAssignmentGradeRequest request) {
        boolean hasAccess = assignmentRepository.teacherOwnsAssignment(assignmentId, username);
        if (!hasAccess) {
            throw new RuntimeException("Access denied");
        }
        boolean alreadyExists = gradeRepository.existsByAssignmentIdAndStudentId(assignmentId, request.getStudentId());
        if (alreadyExists) {
            throw new RuntimeException("Grade already exists for this student");
        }

        Assignment assignment = assignmentRepository.findDetailedById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Grade grade = new Grade();
        grade.setAssignment(assignment);
        grade.setStudent(student);
        grade.setGradeValue(request.getScore());

        gradeRepository.save(grade);
    }
}
