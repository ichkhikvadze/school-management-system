package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import schoolmanagementsystem.dto.StudentOptionDto;
import schoolmanagementsystem.request.CreateExamGradeRequest;
import schoolmanagementsystem.service.TeacherExamGradeService;

import java.util.List;

@Controller
@RequestMapping("/teacher/exams")
public class TeacherExamGradeController {

    private TeacherExamGradeService teacherExamGradeService;

    @Autowired
    public TeacherExamGradeController(TeacherExamGradeService teacherExamGradeService) {
        this.teacherExamGradeService = teacherExamGradeService;
    }

    @GetMapping("/{examId}/grades/add")
    public String addGradePage(@PathVariable Long examId, Model model) {

        List<StudentOptionDto> studentOptionDtoList = teacherExamGradeService.getStudentsForExam(examId);
        model.addAttribute("students", studentOptionDtoList);
        model.addAttribute("request", new CreateExamGradeRequest());
        model.addAttribute("examId", examId);

        return "teacher/add-exam-grade";
    }

    @PostMapping("/{examId}/grades/add")
    public String addGrade(
            @PathVariable Long examId,
            @ModelAttribute("request")
            CreateExamGradeRequest request,
            RedirectAttributes redirectAttributes) {
        try {
            teacherExamGradeService.addExamGrade(examId, request);
            redirectAttributes.addFlashAttribute("success", "Exam grade added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/teacher/exams/"
                + examId
                + "/grades/add";
    }
}
