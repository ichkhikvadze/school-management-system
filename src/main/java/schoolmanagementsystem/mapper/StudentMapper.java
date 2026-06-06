package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.GroupStudentDto;
import schoolmanagementsystem.dto.StudentOptionDto;
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
        studentViewDto.setAdmissionDate(student.getAdmissionDate());

        return studentViewDto;
    }

    public static GroupStudentDto toGroupStudentDto(Student student) {
        GroupStudentDto groupStudentDto = new GroupStudentDto();
        groupStudentDto.setId(student.getId());
        groupStudentDto.setUsername(student.getUser().getUsername());
        groupStudentDto.setFirstName(student.getUser().getFirstName());
        groupStudentDto.setLastName(student.getUser().getLastName());
        return groupStudentDto;
    }

    public static StudentOptionDto toStudentOptionDto(Student student) {
        StudentOptionDto studentOptionDto = new StudentOptionDto();
        studentOptionDto.setId(student.getId());
        studentOptionDto.setDisplayName(student.getUser().getFirstName() + " " + student.getUser().getLastName()
                + " (" + student.getUser().getUsername() + ")");
        return studentOptionDto;
    }
}
