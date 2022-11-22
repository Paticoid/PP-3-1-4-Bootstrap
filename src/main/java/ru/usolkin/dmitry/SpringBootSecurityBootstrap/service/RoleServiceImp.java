package ru.usolkin.dmitry.SpringBootSecurityBootstrap.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.dao.RoleDao;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.Role;

@Service
@Transactional(readOnly = true)
public class RoleServiceImp  implements RoleService{
    private RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Override
    public Role showRole(String name) {
        return roleDao.showRole(name);
    }
}
