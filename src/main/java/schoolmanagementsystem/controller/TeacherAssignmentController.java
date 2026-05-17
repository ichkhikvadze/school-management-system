package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import schoolmanagementsystem.request.CreateAssignmentRequest;
import schoolmanagementsystem.service.TeacherAssignmentService;

@Controller
@RequestMapping("/teacher/groups")
public class TeacherAssignmentController {

    private TeacherAssignmentService teacherAssignmentService;

    @Autowired
    public TeacherAssignmentController(TeacherAssignmentService teacherAssignmentService) {
        this.teacherAssignmentService = teacherAssignmentService;
    }

    @GetMapping("/{groupId}/assignments/create")
    public String createAssignmentPage(@PathVariable Long groupId, Model model) {
        model.addAttribute("assignment", new CreateAssignmentRequest());
        model.addAttribute("groupId", groupId);
        return "teacher/create-assignment";
    }

    @PostMapping("/{groupId}/assignments/create")
    public String createAssignment(
            @PathVariable Long groupId,
            @ModelAttribute("assignment") CreateAssignmentRequest request,
            Authentication authentication,
            RedirectAttributes redirectAttributes
    ) {
        try {
            teacherAssignmentService.createAssignment(authentication.getName(), groupId, request);
            redirectAttributes.addFlashAttribute("success", "Assignment created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teacher/groups/"
                + groupId
                + "/assignments/create";
    }
}
