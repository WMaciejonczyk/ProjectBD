package service;
import persistance.users.IUsers;
import persistance.entity.UsersEntity;
import java.util.List;
import java.util.stream.Collectors;

public class Admin implements IAdmin {
    private final IUsers users;

    public Admin(IUsers users) {
        this.users = users;
    }

    @Override
    public void addUser(Staff staff) {
        users.addUser(mapStaffToUsersEntity(staff));
    }
    @Override
    public void deleteUser(String login) {
        users.deleteUser(login);
    }
    @Override
    public List<Staff> getAllUsers() {
        return users.getAllUsers().stream().map(this::mapUsersEntityToStaff).collect(Collectors.toList());
    }

    private Staff mapUsersEntityToStaff(UsersEntity usersEntity) {
        if (usersEntity == null) {
            return null;
        }
        return new Staff(usersEntity.getUserLogin(), usersEntity.getUserPassword(), usersEntity.getDepartment());
    }

    private UsersEntity mapStaffToUsersEntity(Staff staff) {
        var usersEntity = new UsersEntity();
        if (!staff.getDepartment().name().equalsIgnoreCase("admin")) {
            usersEntity.setUserLogin(staff.getLogin());
            usersEntity.setUserPassword(staff.getPassword());
            usersEntity.setDepartment(staff.getDepartment());
            return usersEntity;
        }
        else {
            return null;
        }
    }
}
