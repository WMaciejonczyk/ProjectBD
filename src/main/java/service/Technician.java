package service;

public class Technician extends Staff {
    private Department department;

    public Technician(String login, String password, Department department) {
        super(login, password, department);
    }
}
