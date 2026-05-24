package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.StudentExamDto;
import schoolmanagementsystem.entity.Grade;
import schoolmanagementsystem.entity.Student;
import schoolmanagementsystem.mapper.ExamMapper;
import schoolmanagementsystem.repository.ExamRepository;
import schoolmanagementsystem.repository.GradeRepository;
import schoolmanagementsystem.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentExamService {

    private ExamRepository examRepository;
    private GradeRepository gradeRepository;
    private StudentRepository studentRepository;

    @Autowired
    public StudentExamService(ExamRepository examRepository, GradeRepository gradeRepository, StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudentExamDto> getStudentExams(String username) {

        Student student = studentRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return examRepository
                .findStudentExams(username)
                .stream()
                .map(exam -> {
                    Optional<Grade> gradeOptional = gradeRepository.findByExamIdAndStudentId(exam.getId(), student.getId());
                    return ExamMapper.toStudentExamDto(exam, gradeOptional);
                })
                .toList();
    }
}
