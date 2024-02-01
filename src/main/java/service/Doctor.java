package service;

import types.Department;

public class Doctor extends Staff {

    public Doctor(String login, String password, Department department) {
        super(login, password, department);
    }
}
