package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.ExamViewDto;
import schoolmanagementsystem.entity.Subject;
import schoolmanagementsystem.entity.Teacher;
import schoolmanagementsystem.mapper.ExamMapper;
import schoolmanagementsystem.repository.ExamRepository;
import schoolmanagementsystem.repository.TeacherRepository;
import schoolmanagementsystem.repository.TimetableRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherExamService {

    private ExamRepository examRepository;
    private TimetableRepository timetableRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherExamService(ExamRepository examRepository, TimetableRepository timetableRepository, TeacherRepository teacherRepository) {
        this.examRepository = examRepository;
        this.timetableRepository = timetableRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<ExamViewDto> getGroupExams(String username, Long groupId) {
        boolean hasAccess = timetableRepository.teacherHasAccessToGroup(username, groupId);
        Optional<Teacher> teacherOptional = teacherRepository.findByUserUsername(username);

        List<Subject> teacherSubjects;
        if (teacherOptional.isPresent()) {
            teacherSubjects = teacherOptional.get().getSubjects();
        } else {
            teacherSubjects = new ArrayList<>();
        }

        if (!hasAccess) {
            throw new RuntimeException("Access denied");
        }

        return examRepository
                .findGroupExams(groupId)
                .stream()
                .filter(exam -> teacherSubjects.contains(exam.getSubject()))
                .map(ExamMapper::toExamViewDto)
                .toList();
    }
}
