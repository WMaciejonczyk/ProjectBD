package service;

import java.util.List;

public interface IAdmin {
    void addUser(Staff staff);
    void deleteUser(String login);
    List<Staff> getAllUsers();
}
