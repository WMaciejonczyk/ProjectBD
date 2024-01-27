package service;

public class Doctor extends Staff {
    private Department department;

    public Doctor(String login, String password, Department department) {
        super(login, password, department);
    }
}
