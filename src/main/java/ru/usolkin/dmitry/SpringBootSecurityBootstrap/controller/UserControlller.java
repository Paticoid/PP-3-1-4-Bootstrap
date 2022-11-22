package ru.usolkin.dmitry.SpringBootSecurityBootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.User;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.service.RegUserService;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.service.RoleService;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.service.UserService;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.util.CustomValid;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserControlller {
    private CustomValid customValid;
    private RegUserService regUserService;
    private UserService userService;

    public UserControlller(CustomValid customValid, RegUserService regUserService, UserService userService, RoleService roleService) {
        this.customValid = customValid;
        this.regUserService = regUserService;
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") User user) {
        return "/user/registration";
    }

    @PostMapping ("/registration")
    public String registration(@ModelAttribute("user")@Valid User user, BindingResult bindingResult) {
        customValid.validate(user,bindingResult);
        if(bindingResult.hasErrors()) {
            return "user/registration";
        }
        regUserService.register(user);
        return "redirect:/login";
    }
    @GetMapping("/hello")
    public String getHello(Model model, Principal principal) {
        model.addAttribute("user",userService.getUserByName(principal.getName()));
        return "user/userPage";
    }
}
