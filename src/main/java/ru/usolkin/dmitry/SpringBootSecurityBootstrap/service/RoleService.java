package ru.usolkin.dmitry.SpringBootSecurityBootstrap.service;

import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.Role;

import java.util.List;

public interface RoleService {
    public Role showRole(String name);
    public List<Role> getRoleList();
}
