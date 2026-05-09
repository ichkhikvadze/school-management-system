package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import schoolmanagementsystem.dto.TeacherViewDto;
import schoolmanagementsystem.service.TeacherService;

@Controller
@RequestMapping("/admin/teachers")
public class AdminTeacherController {

    private TeacherService teacherService;

    @Autowired
    public AdminTeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String teachersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            Model model
    ) {

        Page<TeacherViewDto> teacherPage =
                teacherService.getTeachers(search, page, size);

        model.addAttribute("teachers", teacherPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", teacherPage.getTotalPages());
        model.addAttribute("search", search);

        return "admin/teachers";
    }
}
