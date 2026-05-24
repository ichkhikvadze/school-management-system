package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.dto.StudentTimetableDto;
import schoolmanagementsystem.service.StudentTimetableService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentTimetableController {

    private StudentTimetableService timetableService;

    @Autowired
    public StudentTimetableController(StudentTimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping("/timetable")
    public String timetablePage(Authentication authentication, Model model) {
        List<StudentTimetableDto> timetable = timetableService.getStudentTimetable(authentication.getName());
        model.addAttribute("timetable", timetable);
        return "student/timetable";
    }
}
