package persistance.users;

import persistance.entity.UsersEntity;

import java.util.List;

public interface IUsers {
    void addUser(UsersEntity usersEntity);
    void deleteUser(String login);
    List<UsersEntity> getAllUsers();

}
