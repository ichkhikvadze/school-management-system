package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.TeacherViewDto;
import schoolmanagementsystem.entity.Teacher;

public class TeacherMapper {

    public static TeacherViewDto toTeacherViewDto(Teacher teacher) {
        TeacherViewDto teacherViewDto = new TeacherViewDto();
        teacherViewDto.setUsername(teacher.getUser().getUsername());
        teacherViewDto.setFirstName(teacher.getUser().getFirstName());
        teacherViewDto.setLastName(teacher.getUser().getLastName());
        teacherViewDto.setQualification(teacher.getQualification());
        return teacherViewDto;
    }
}
