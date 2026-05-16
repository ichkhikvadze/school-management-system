package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.dto.TeacherTimetableDto;
import schoolmanagementsystem.service.TimetableService;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TimetableController {

    private TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping("/timetable")
    public String timetablePage(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<TeacherTimetableDto> timetable = timetableService.getTeacherTimetable(username);
        model.addAttribute("timetable", timetable);
        return "teacher/timetable";
    }
}
