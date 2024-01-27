package persistance.entity;

import jakarta.persistence.*;
import service.Department;

@Entity
@Table(name = "users", schema = "medical_equipment")
public class UsersEntity {
    @Id
    @Column(name = "user_login")
    private String userLogin;
    @Basic
    @Column(name = "user_password")
    private String userPassword;
    @Basic
    @Column(name = "department")
    private Object department;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userLogin != null ? userLogin.hashCode() : 0;
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }
}
