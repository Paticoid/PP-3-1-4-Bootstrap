package ru.usolkin.dmitry.SpringBootSecurityBootstrap.dao;


import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.Role;

import java.util.List;

public interface RoleDao {
    public Role showRole(String name);
    public List<Role> getRoleList();
}
