package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.dto.StudentExamDto;
import schoolmanagementsystem.service.StudentExamService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentExamController {

    private StudentExamService studentExamService;

    @Autowired
    public StudentExamController(StudentExamService studentExamService) {
        this.studentExamService = studentExamService;
    }

    @GetMapping("/exams")
    public String examsPage(Authentication authentication, Model model) {
        List<StudentExamDto> exams = studentExamService.getStudentExams(authentication.getName());
        model.addAttribute("exams", exams);
        return "student/exams";
    }
}
