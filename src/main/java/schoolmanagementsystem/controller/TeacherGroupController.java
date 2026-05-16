package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.dto.GroupStudentDto;
import schoolmanagementsystem.dto.TeacherGroupDto;
import schoolmanagementsystem.service.TeacherGroupService;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherGroupController {

    private TeacherGroupService teacherGroupService;

    @Autowired
    public TeacherGroupController(TeacherGroupService teacherGroupService) {
        this.teacherGroupService = teacherGroupService;
    }

    @GetMapping("/groups")
    public String groupsPage(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<TeacherGroupDto> groups = teacherGroupService.getTeacherGroups(username);
        model.addAttribute("groups", groups);
        return "teacher/groups";
    }

    @GetMapping("/groups/{groupId}/students")
    public String groupStudentsPage(@PathVariable Long groupId, Authentication authentication, Model model) {
        String username = authentication.getName();
        List<GroupStudentDto> students = teacherGroupService.getGroupStudents(username, groupId);
        model.addAttribute("students", students);
        return "teacher/group-students";
    }
}
