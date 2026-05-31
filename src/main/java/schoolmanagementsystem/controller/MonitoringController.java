package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.service.MonitoringService;

@Controller
@RequestMapping("/monitoring")
public class MonitoringController {

    private MonitoringService monitoringService;

    @Autowired
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("sensors", monitoringService.getDashboard());
        return "monitoring/dashboard";
    }
}
