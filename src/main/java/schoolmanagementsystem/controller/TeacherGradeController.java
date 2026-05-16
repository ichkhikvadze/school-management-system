package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.dto.StudentGradeDto;
import schoolmanagementsystem.service.TeacherGradeService;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherGradeController {

    private TeacherGradeService teacherGradeService;

    @Autowired
    public TeacherGradeController(TeacherGradeService teacherGradeService) {
        this.teacherGradeService = teacherGradeService;
    }

    @GetMapping("/students/{studentId}/grades")
    public String studentGradesPage(@PathVariable Long studentId, Authentication authentication, Model model) {
        String username = authentication.getName();
        List<StudentGradeDto> grades = teacherGradeService.getStudentGrades(username, studentId);
        model.addAttribute("grades", grades);
        return "teacher/student-grades";
    }
}
