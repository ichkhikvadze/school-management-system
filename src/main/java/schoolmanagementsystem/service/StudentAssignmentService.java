package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.StudentAssignmentDto;
import schoolmanagementsystem.entity.Grade;
import schoolmanagementsystem.entity.Student;
import schoolmanagementsystem.mapper.AssignmentMapper;
import schoolmanagementsystem.repository.AssignmentRepository;
import schoolmanagementsystem.repository.GradeRepository;
import schoolmanagementsystem.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAssignmentService {

    private AssignmentRepository assignmentRepository;
    private GradeRepository gradeRepository;
    private StudentRepository studentRepository;

    @Autowired
    public StudentAssignmentService(AssignmentRepository assignmentRepository, GradeRepository gradeRepository, StudentRepository studentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudentAssignmentDto> getAssignments(String username) {
        Student student = studentRepository
                .findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return assignmentRepository
                .findAssignmentsForStudent(username)
                .stream()
                .map(assignment -> {
                    Optional<Grade> gradeOptional =
                            gradeRepository
                                    .findByAssignmentIdAndStudentId(
                                            assignment.getId(),
                                            student.getId());
                    return AssignmentMapper.toStudentAssignmentDto(assignment, gradeOptional);
                })
                .toList();
    }
}
