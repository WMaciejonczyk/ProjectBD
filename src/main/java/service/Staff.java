package service;

public class Staff {
    private String login;
    private String password;

    private Department department;

    public Staff(String login, String password, Department department) {
        this.login = login;
        this.password = password;
        this.department = department;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
