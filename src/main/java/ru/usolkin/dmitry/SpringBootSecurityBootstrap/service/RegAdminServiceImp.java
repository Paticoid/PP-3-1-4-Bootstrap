package ru.usolkin.dmitry.SpringBootSecurityBootstrap.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.User;

@Service
public class RegAdminServiceImp implements RegAdminService{

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public RegAdminServiceImp(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.showRole("ROLE_ADMIN"));
        userService.save(user);
    }
}
