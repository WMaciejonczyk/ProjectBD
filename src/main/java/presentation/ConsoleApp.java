package presentation;
import service.Department;
import service.IAdmin;
import service.Staff;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleApp {
    private final Scanner scanner;
    private final IAdmin admin;

    public ConsoleApp(Scanner scanner, IAdmin admin) {
        this.scanner = scanner;
        this.admin = admin;
    }

    public void start() {
        System.out.println("Hello!");

        var option = chooseEntryOption();
        perform(option);
    }

    private String chooseEntryOption() {
        System.out.println("What do you want to do?");
        System.out.println("(1) Register");
        System.out.println("(2) Login");

        return scanner.nextLine();
    }

    private void perform(String option) {
        switch (option) {
            case "1":
            case "Register":
                getRegisterInfo();
                break;
            case "2":
            case "Login":
                getLoginInfo();
                break;
        }
    }
    private void getRegisterInfo() {
        System.out.println("Enter username:");
        var username = scanner.nextLine();
        System.out.println("Enter password:");
        var password = scanner.nextLine();
        System.out.println("Choose department (doctor/technician)");
        var department = scanner.nextLine().toUpperCase();
        admin.addUser(new Staff(username, password, Department.valueOf(department)));
        getLoginInfo();
    }

    private void getLoginInfo() {
        System.out.println("Enter username:");
        var username = scanner.nextLine();
        System.out.println("Enter password:");
        var password = scanner.nextLine();
        Optional<Staff> user = admin.getAllUsers().stream()
                .filter(p -> p.getLogin().equals(username) && p.getPassword().equals(password))
                .findFirst();

        if (user.isPresent()) {
            if (user.get().getDepartment() == Department.DOCTOR) {
                var option = chooseDoctorOption();
                performDoctor(option);
            }
            else if (user.get().getDepartment() == Department.TECHNICIAN) {
                var option = chooseTechnicianOption();
                performTechnician(option);
            }
        }
        else {
            System.out.println("Invalid login or password.");
        }
    }

    private String chooseDoctorOption() {
        System.out.println("What do you want to do?");
        System.out.println("(1) Reserve an equipment");
        System.out.println("(2) Start using an equipment");
        System.out.println("(3) End using an equipment");
        System.out.println("(4) Show a list of equipment (with status)");
        System.out.println("(5) Show reservations of an equipment");

        return scanner.nextLine();
    }

    private void performDoctor (String option) {
        switch (option) {
            case "1":
            case "Reserve an equipment":
                // reserve();
                break;
            case "2":
            case "Start using an equipment":
              //  getLoginInfo();
                break;
            case "3":
            case "End using an equipment":
              //  getLoginInfo();
                break;
            case "4":
            case "Show a list of equipment (with status)":
              //  getLoginInfo();
                break;
            case "5":
            case "Show reservations of an equipment":
              //  getLoginInfo();
                break;
        }
    }

    private String chooseTechnicianOption() {
        System.out.println("What do you want to do?");
        System.out.println("(1) Start servicing an equipment");
        System.out.println("(2) End servicing an equipment");
        System.out.println("(3) Report service");
        System.out.println("(4) Show a list of equipment (with next service date)");
        System.out.println("(5) Add a new equipment");

        return scanner.nextLine();
    }

    private void performTechnician (String option) {
        switch (option) {
            case "1":
            case "Start servicing an equipment":
                // reserve();
                break;
            case "2":
            case "End servicing an equipment":
                //  getLoginInfo();
                break;
            case "3":
            case "Report service":
                //  getLoginInfo();
                break;
            case "4":
            case "Show a list of equipment (with next service date)":
                //  getLoginInfo();
                break;
            case "5":
            case "Add a new equipment":
                //  getLoginInfo();
                break;
        }
    }
}
