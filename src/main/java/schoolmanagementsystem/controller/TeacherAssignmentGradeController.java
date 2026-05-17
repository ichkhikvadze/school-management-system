package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import schoolmanagementsystem.dto.StudentOptionDto;
import schoolmanagementsystem.request.CreateAssignmentGradeRequest;
import schoolmanagementsystem.service.TeacherAssignmentGradeService;

import java.util.List;

@Controller
@RequestMapping("/teacher/assignments")
public class TeacherAssignmentGradeController {

    private TeacherAssignmentGradeService teacherAssignmentGradeService;

    @Autowired
    public TeacherAssignmentGradeController(TeacherAssignmentGradeService teacherAssignmentGradeService) {
        this.teacherAssignmentGradeService = teacherAssignmentGradeService;
    }

    @GetMapping("/{assignmentId}/grades/add")
    public String addGradePage(@PathVariable Long assignmentId, Authentication authentication, Model model) {

        List<StudentOptionDto> studentOptionDtoList = teacherAssignmentGradeService.getStudentsForAssignment(authentication.getName(), assignmentId);

        model.addAttribute("students", studentOptionDtoList);
        model.addAttribute("request", new CreateAssignmentGradeRequest());
        model.addAttribute("assignmentId", assignmentId);

        return "teacher/add-assignment-grade";
    }

    @PostMapping("/{assignmentId}/grades/add")
    public String addGrade(@PathVariable Long assignmentId,
                           @ModelAttribute("request") CreateAssignmentGradeRequest request,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) {
        try {
            teacherAssignmentGradeService.addAssignmentGrade(authentication.getName(), assignmentId, request);
            redirectAttributes.addFlashAttribute("success", "Grade added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/teacher/assignments/"
                + assignmentId
                + "/grades/add";
    }
}
