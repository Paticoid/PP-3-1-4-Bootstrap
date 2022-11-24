package ru.usolkin.dmitry.SpringBootSecurityBootstrap.service;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.Role;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.User;

import java.util.List;

public interface RegUserService {
    public void register(User user, List<String> roleName);
}
