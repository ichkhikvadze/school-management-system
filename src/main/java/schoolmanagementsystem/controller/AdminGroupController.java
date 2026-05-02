package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import schoolmanagementsystem.request.ClassCreateRequest;
import schoolmanagementsystem.request.ExamCreateRequest;
import schoolmanagementsystem.request.GroupCreateRequest;
import schoolmanagementsystem.request.TimetableRequest;
import schoolmanagementsystem.service.GroupService;
import schoolmanagementsystem.service.StudentGroupService;

@Controller
@RequestMapping("/admin/groups")
@PreAuthorize("hasRole('ADMIN')")
public class AdminGroupController {

    private GroupService groupService;
    private StudentGroupService studentGroupService;

    @Autowired
    public AdminGroupController(GroupService groupService, StudentGroupService studentGroupService) {
        this.groupService = groupService;
        this.studentGroupService = studentGroupService;
    }

    @GetMapping
    public String listGroups(Model model) {
        model.addAttribute("groups", studentGroupService.getStudentGroupList());
        return "admin/groups";
    }

    @PostMapping("/create")
    public String createGroup(@ModelAttribute GroupCreateRequest request) {
        groupService.createGroup(request);
        return "redirect:/admin/groups";
    }

    @PostMapping("/{groupId}/students/{studentId}/add")
    public String addStudent(@PathVariable Long groupId, @PathVariable Long studentId) {
        groupService.addStudentToGroup(groupId, studentId);
        return "redirect:/admin/groups";
    }

    @PostMapping("/{groupId}/students/{studentId}/remove")
    public String removeStudent(@PathVariable Long groupId, @PathVariable Long studentId) {
        groupService.removeStudentFromGroup(groupId, studentId);
        return "redirect:/admin/groups";
    }

    @PostMapping("/timetable/create")
    public String createTimetable(@ModelAttribute TimetableRequest request) {
        groupService.createTimetable(request);
        return "redirect:/admin/groups";
    }

    @PostMapping("/exam/create")
    public String createExam(@ModelAttribute ExamCreateRequest request) {
        groupService.createExam(request);
        return "redirect:/admin/groups";
    }

    @PostMapping("/class/create")
    public String createClass(@ModelAttribute ClassCreateRequest request) {
        groupService.createClass(request);
        return "redirect:/admin/groups";
    }
}
