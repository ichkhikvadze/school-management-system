package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolmanagementsystem.dto.StudentOptionDto;
import schoolmanagementsystem.entity.Exam;
import schoolmanagementsystem.entity.Grade;
import schoolmanagementsystem.entity.Student;
import schoolmanagementsystem.enums.GradeType;
import schoolmanagementsystem.mapper.StudentMapper;
import schoolmanagementsystem.repository.ExamRepository;
import schoolmanagementsystem.repository.GradeRepository;
import schoolmanagementsystem.repository.StudentRepository;
import schoolmanagementsystem.request.CreateExamGradeRequest;

import java.util.List;

@Service
public class TeacherExamGradeService {

    private ExamRepository examRepository;
    private StudentRepository studentRepository;
    private GradeRepository gradeRepository;

    @Autowired
    public TeacherExamGradeService(ExamRepository examRepository,
                                   StudentRepository studentRepository,
                                   GradeRepository gradeRepository) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<StudentOptionDto> getStudentsForExam(Long examId) {
        Exam exam = examRepository.findDetailedById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        return exam.getGroup()
                .getStudents()
                .stream()
                .map(StudentMapper::toStudentOptionDto)
                .toList();
    }

    @Transactional
    public void addExamGrade(Long examId, CreateExamGradeRequest request) {
        boolean alreadyExists = gradeRepository.existsByExamIdAndStudentId(examId, request.getStudentId());

        if (alreadyExists) {
            throw new RuntimeException("Grade already exists for this student");
        }

        Exam exam = examRepository.findDetailedById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Student student = studentRepository
                .findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Grade grade = new Grade();
        grade.setExam(exam);
        grade.setStudent(student);
        grade.setGradeValue(request.getScore());

        gradeRepository.save(grade);
    }
}
