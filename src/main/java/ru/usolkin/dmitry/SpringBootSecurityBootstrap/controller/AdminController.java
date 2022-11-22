package ru.usolkin.dmitry.SpringBootSecurityBootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.User;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.service.RegAdminService;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.service.RegUserService;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.service.UserService;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.util.CustomValid;


import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private CustomValid customValid;
    private RegUserService regService;
    private RegAdminService regAdminService;
    private UserService userService;

    public AdminController(CustomValid customValid, RegUserService regService, RegAdminService regAdminService, UserService userService) {
        this.customValid = customValid;
        this.regService = regService;
        this.regAdminService = regAdminService;
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String registrationAdmin(@ModelAttribute(name = "admin") User user){
        return "admin/registration";
    }
    @GetMapping
    public String getAll(Model model, Principal principal) {
    model.addAttribute("newUser",new User());
    model.addAttribute("users", userService.allUsers());
    model.addAttribute("existUser",userService.getUserByName(principal.getName()));
    return "admin/all";
    }
//    @GetMapping("/{id}")
//    public String editUser(@PathVariable("id") long id,Model model) {
//        model.addAttribute("user",userService.show(id));
//        return "admin/all";
//    }
//    @PostMapping("/registration")
//    public String regAdmin(@ModelAttribute(name = "regUser")@Valid User user,BindingResult bindingResult) {
//        System.out.println("pop");
//        customValid.validate(user,bindingResult);
//        System.out.println("dop");
//        if(bindingResult.hasErrors()){
//            return "admin/all";
//        }
//        System.out.println("rot");
//        regAdminService.register(user);
//        return "redirect:/admin";
//    }


//    @GetMapping("/user/{id}")
//    public String show(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user",userService.show(id));
//        return "admin/user";
//    }

    @PostMapping
    public String createUser(@ModelAttribute("newUser")@Valid User user, BindingResult bindingResult) {
//      customValid.validate(user,bindingResult);
        if(bindingResult.hasErrors()) {
            return "admin/all";
        }
        regService.register(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id")long id, @ModelAttribute("userEd")  User user) {
        userService.update(id,user);
        return "redirect:/admin";
    }
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id")long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
