package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.dto.ExamViewDto;
import schoolmanagementsystem.service.TeacherExamService;

import java.util.List;

@Controller
@RequestMapping("/teacher/groups")
public class TeacherExamController {

    private TeacherExamService teacherExamService;

    @Autowired
    public TeacherExamController(TeacherExamService teacherExamService) {
        this.teacherExamService = teacherExamService;
    }

    @GetMapping("/{groupId}/exams")
    public String examsPage(@PathVariable Long groupId, Authentication authentication, Model model) {
        List<ExamViewDto> exams = teacherExamService.getGroupExams(authentication.getName(), groupId);
        model.addAttribute("exams", exams);
        model.addAttribute("groupId", groupId);
        return "teacher/exams";
    }
}
