package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.TeacherGroupDto;
import schoolmanagementsystem.entity.StudentGroup;

public class StudentGroupMapper {

    public static TeacherGroupDto toTeacherGroupDto(StudentGroup studentGroup) {
        TeacherGroupDto teacherGroupDto = new TeacherGroupDto();
        teacherGroupDto.setId(studentGroup.getId());
        teacherGroupDto.setGroupName(studentGroup.getName());
        teacherGroupDto.setGradeLevel(studentGroup.getGradeLevel());
        teacherGroupDto.setAcademicYear(studentGroup.getAcademicYear());
        return teacherGroupDto;
    }
}
