package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.AssignmentViewDto;
import schoolmanagementsystem.dto.StudentAssignmentDto;
import schoolmanagementsystem.entity.Assignment;
import schoolmanagementsystem.entity.Grade;

import java.math.BigDecimal;
import java.util.Optional;

public class AssignmentMapper {

    public static AssignmentViewDto toAssignmentViewDto(Assignment assignment) {
        AssignmentViewDto assignmentViewDto = new AssignmentViewDto();
        assignmentViewDto.setId(assignment.getId());
        assignmentViewDto.setTitle(assignment.getTitle());
        assignmentViewDto.setDescription(assignment.getDescription());
        if (assignment.getSubject() != null) {
            assignmentViewDto.setSubjectName(assignment.getSubject().getName());
        }
        assignmentViewDto.setDueDate(assignment.getDueDate());
        return assignmentViewDto;
    }

    public static StudentAssignmentDto toStudentAssignmentDto(Assignment assignment, Optional<Grade> gradeOptional) {
        StudentAssignmentDto studentAssignmentDto = new StudentAssignmentDto();

        BigDecimal grade = null;
        boolean graded = false;
        if (gradeOptional.isPresent()) {
            grade = gradeOptional.get().getGradeValue();
            graded = true;
        }
        studentAssignmentDto.setAssignmentId(assignment.getId());
        studentAssignmentDto.setTitle(assignment.getTitle());
        studentAssignmentDto.setDescription(assignment.getDescription());
        studentAssignmentDto.setSubjectName(assignment.getSubject().getName());
        studentAssignmentDto.setDueDate(assignment.getDueDate());
        studentAssignmentDto.setGrade(grade);
        studentAssignmentDto.setGraded(graded);

        return studentAssignmentDto;
    }
}
