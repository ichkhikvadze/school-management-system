package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.StudentGradeDto;
import schoolmanagementsystem.entity.Grade;
import schoolmanagementsystem.enums.GradeType;

import java.math.BigDecimal;

public class GradeMapper {

    public static StudentGradeDto toStudentGradeDto(Grade grade) {
        StudentGradeDto studentGradeDto = new StudentGradeDto();
        if (grade.getExam() != null) {
            studentGradeDto.setGradeType(GradeType.EXAM.name());
            studentGradeDto.setSubjectName(grade.getExam().getSubject().getName());
            studentGradeDto.setMaxScore(BigDecimal.valueOf(grade.getExam().getMaxScore()));
        } else if (grade.getAssignment() != null) {
            studentGradeDto.setGradeType(GradeType.ASSIGNMENT.name());
            studentGradeDto.setSubjectName(grade.getAssignment().getSubject().getName());
            studentGradeDto.setMaxScore(BigDecimal.valueOf(grade.getAssignment().getMaxScore()));
        }
        studentGradeDto.setScore(grade.getGradeValue());
        return studentGradeDto;
    }
}
