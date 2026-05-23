package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.dto.StudentAssignmentDto;
import schoolmanagementsystem.service.StudentAssignmentService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentAssignmentController {

    private StudentAssignmentService assignmentService;

    @Autowired
    public StudentAssignmentController(StudentAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/assignments")
    public String assignmentsPage(Authentication authentication, Model model) {
        List<StudentAssignmentDto> assignments = assignmentService.getAssignments(authentication.getName());
        model.addAttribute("assignments", assignments);

        return "student/assignments";
    }
}
