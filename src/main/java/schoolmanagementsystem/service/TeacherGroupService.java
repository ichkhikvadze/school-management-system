package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.GroupStudentDto;
import schoolmanagementsystem.dto.TeacherGroupDto;
import schoolmanagementsystem.entity.StudentGroup;
import schoolmanagementsystem.mapper.StudentGroupMapper;
import schoolmanagementsystem.mapper.StudentMapper;
import schoolmanagementsystem.repository.StudentGroupRepository;
import schoolmanagementsystem.repository.TimetableRepository;

import java.util.List;

@Service
public class TeacherGroupService {

    private StudentGroupRepository studentGroupRepository;
    private TimetableRepository timetableRepository;

    @Autowired
    public TeacherGroupService(StudentGroupRepository studentGroupRepository,  TimetableRepository timetableRepository) {
        this.studentGroupRepository = studentGroupRepository;
        this.timetableRepository = timetableRepository;
    }

    public List<TeacherGroupDto> getTeacherGroups(String username) {

        return timetableRepository
                .findGroupsByTeacherUsername(username)
                .stream()
                .map(StudentGroupMapper::toTeacherGroupDto)
                .toList();
    }

    public List<GroupStudentDto> getGroupStudents(String username, Long groupId) {
        boolean hasAccess = timetableRepository.teacherHasAccessToGroup(username, groupId);
        if (!hasAccess) {
            throw new RuntimeException("Access denied to this group");
        }
        StudentGroup group = studentGroupRepository.findWithStudentsById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return group.getStudents()
                .stream()
                .map(StudentMapper::toGroupStudentDto)
                .toList();
    }
}
