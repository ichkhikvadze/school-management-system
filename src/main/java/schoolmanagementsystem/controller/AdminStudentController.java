package schoolmanagementsystem.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import schoolmanagementsystem.dto.StudentViewDto;
import schoolmanagementsystem.service.StudentService;

@Controller
@RequestMapping("/admin/students")
public class AdminStudentController {

    private StudentService studentService;

    public AdminStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String studentsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            Model model
    ) {

        Page<StudentViewDto> studentPage = studentService.getStudents(search, page, size);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("search", search);

        return "admin/students";
    }
}
