package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.AssignmentViewDto;
import schoolmanagementsystem.entity.Assignment;

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
}
