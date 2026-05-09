package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.StudentViewDto;
import schoolmanagementsystem.entity.Student;
import schoolmanagementsystem.entity.StudentGroup;

public class StudentMapper {

    public static StudentViewDto toStudentViewDto(Student student) {
        StudentViewDto studentViewDto = new StudentViewDto();

        String groupName = student.getGroups()
                .stream()
                .map(StudentGroup::getName)
                .findFirst()
                .orElse("No Group");

        studentViewDto.setUsername(student.getUser().getUsername());
        studentViewDto.setFirstName(student.getUser().getFirstName());
        studentViewDto.setLastName(student.getUser().getLastName());
        studentViewDto.setGroupName(groupName);
        studentViewDto.setParentName(student.getParentName());
        studentViewDto.setParentContact(student.getParentContact());

        return studentViewDto;
    }
}
