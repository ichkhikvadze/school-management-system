package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.StudentGradeDto;
import schoolmanagementsystem.entity.Grade;
import schoolmanagementsystem.enums.GradeType;

public class GradeMapper {

    public static StudentGradeDto toStudentGradeDto(Grade grade) {
        StudentGradeDto studentGradeDto = new StudentGradeDto();
        if (grade.getExam() != null) {
            studentGradeDto.setGradeType(GradeType.EXAM.name());
            studentGradeDto.setSubjectName(grade.getExam().getSubject().getName());
        } else if (grade.getAssignment() != null) {
            studentGradeDto.setGradeType(GradeType.ASSIGNMENT.name());
            studentGradeDto.setSubjectName(grade.getAssignment().getSubject().getName());
        }
        studentGradeDto.setScore(grade.getGradeValue());
        return studentGradeDto;
    }
}
