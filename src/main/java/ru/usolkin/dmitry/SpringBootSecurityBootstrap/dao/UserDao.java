package ru.usolkin.dmitry.SpringBootSecurityBootstrap.dao;

import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    public List<User> allUsers();
    public User show(long id);
    public Optional<User> getUserByName(String name);
    public void save(User user);
    public void update(long id,User updateUser);
    public void delete(long id);
}
