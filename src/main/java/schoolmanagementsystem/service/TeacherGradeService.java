package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.StudentGradeDto;
import schoolmanagementsystem.mapper.GradeMapper;
import schoolmanagementsystem.repository.GradeRepository;
import schoolmanagementsystem.repository.TimetableRepository;

import java.util.List;

@Service
public class TeacherGradeService {

    private GradeRepository gradeRepository;
    private TimetableRepository timetableRepository;

    @Autowired
    public TeacherGradeService(GradeRepository gradeRepository, TimetableRepository timetableRepository) {
        this.gradeRepository = gradeRepository;
        this.timetableRepository = timetableRepository;
    }

    public List<StudentGradeDto> getStudentGrades(String username, Long studentId) {
        boolean hasAccess = timetableRepository.teacherHasAccessToStudent(username, studentId);
        if (!hasAccess) {
            throw new RuntimeException("Access denied");
        }
        return gradeRepository
                .findStudentGrades(studentId)
                .stream()
                .map(GradeMapper::toStudentGradeDto)
                .toList();
    }
}
