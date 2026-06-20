package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.StudentGradeDto;
import schoolmanagementsystem.entity.Teacher;
import schoolmanagementsystem.mapper.GradeMapper;
import schoolmanagementsystem.repository.GradeRepository;
import schoolmanagementsystem.repository.TeacherRepository;
import schoolmanagementsystem.repository.TimetableRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherGradeService {

    private final TeacherRepository teacherRepository;
    private GradeRepository gradeRepository;
    private TimetableRepository timetableRepository;

    @Autowired
    public TeacherGradeService(GradeRepository gradeRepository, TimetableRepository timetableRepository, TeacherRepository teacherRepository) {
        this.gradeRepository = gradeRepository;
        this.timetableRepository = timetableRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<StudentGradeDto> getStudentGrades(String username, Long studentId) {
        boolean hasAccess = timetableRepository.teacherHasAccessToStudent(username, studentId);
        Optional<Teacher> teacherOptional = teacherRepository.findByUserUsername(username);
        if (!hasAccess) {
            throw new RuntimeException("Access denied");
        }
        return gradeRepository
                .findStudentGrades(studentId)
                .stream()
                .filter(grade -> {
                    if (teacherOptional.isEmpty()) {
                        return false;
                    }
                    Teacher teacher = teacherOptional.get();
                    if (grade.getAssignment() != null && teacher.getSubjects().contains(grade.getAssignment().getSubject())) {
                        return true;
                    }

                    if (grade.getExam() != null && teacher.getSubjects().contains(grade.getExam().getSubject())) {
                        return true;
                    }

                    return false;
                })
                .map(GradeMapper::toStudentGradeDto)
                .toList();
    }
}
