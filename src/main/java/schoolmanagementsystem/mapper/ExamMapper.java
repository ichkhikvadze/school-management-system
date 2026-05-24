package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.ExamViewDto;
import schoolmanagementsystem.dto.StudentExamDto;
import schoolmanagementsystem.entity.Exam;
import schoolmanagementsystem.entity.Grade;

import java.math.BigDecimal;
import java.util.Optional;

public class ExamMapper {

    public static ExamViewDto toExamViewDto(Exam exam) {
        ExamViewDto examViewDto = new ExamViewDto();
        examViewDto.setId(exam.getId());
        examViewDto.setTitle(exam.getTitle());
        examViewDto.setSubjectName(exam.getSubject().getName());
        examViewDto.setExamDate(exam.getExamDate());
        return examViewDto;
    }

    public static StudentExamDto toStudentExamDto(Exam exam, Optional<Grade> gradeOptional) {
        StudentExamDto studentExamDto = new StudentExamDto();

        BigDecimal grade = null;
        boolean graded = false;
        if (gradeOptional.isPresent()) {
            grade = gradeOptional.get().getGradeValue();
            graded = true;
        }

        studentExamDto.setExamId(exam.getId());
        studentExamDto.setTitle(exam.getTitle());
        studentExamDto.setSubjectName(exam.getSubject().getName());
        studentExamDto.setExamDate(exam.getExamDate());
        studentExamDto.setGrade(grade);
        studentExamDto.setMaxScore(exam.getMaxScore());
        studentExamDto.setGraded(graded);
        return  studentExamDto;
    }
}
