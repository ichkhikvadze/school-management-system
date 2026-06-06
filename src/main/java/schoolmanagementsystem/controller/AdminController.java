package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolmanagementsystem.entity.RoleName;
import schoolmanagementsystem.request.UserCreateRequest;
import schoolmanagementsystem.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/new")
    public String userForm(Model model) {
        model.addAttribute("user", new UserCreateRequest());
        model.addAttribute("roles", RoleName.values());
        return "admin/create-user";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute UserCreateRequest request) {
        userService.createUser(request);
        return "redirect:/admin/users/new?success";
    }
}
