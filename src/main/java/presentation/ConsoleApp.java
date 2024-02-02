package presentation;
import persistance.archives.IArchives;
import persistance.equipment.IEquipmentStorage;
import persistance.view.TechnicianViewStorage;
import types.Department;
import service.IAdmin;
import service.Staff;
import service.Technician;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleApp {
    private final Scanner scanner;
    private final IAdmin admin;
    private IEquipmentStorage storage;
    private TechnicianViewStorage viewStorage;
    private IArchives archives;
    private Optional<Staff> user;
    private TechnicianApp technicianApp;

    public ConsoleApp(Scanner scanner, IAdmin admin, IEquipmentStorage storage, TechnicianViewStorage viewStorage, IArchives archives) {
        this.scanner = scanner;
        this.admin = admin;
        this.storage = storage;
        this.viewStorage = viewStorage;
        this.archives = archives;
    }

    public void start() {
        while (true) {
            var option = chooseEntryOption();
            if (option.equalsIgnoreCase("q") || option.equalsIgnoreCase("quit")) {
                break;
            }
            perform(option);
            System.out.println();
            System.out.println();
        }
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

    }

    private void getLoginInfo() {
        System.out.println("Enter username:");
        var username = scanner.nextLine();
        System.out.println("Enter password:");
        var password = scanner.nextLine();
        user = admin.getAllUsers().stream()
                .filter(p -> p.getLogin().equals(username) && p.getPassword().equals(password))
                .findFirst();

        if (user.isPresent()) {
            switch (user.get().getDepartment()) {
                case ADMIN:
                    var optionA = chooseAdminOption();
                    performAdmin(optionA);
                    break;
                case DOCTOR:
                    var optionB = chooseDoctorOption();
                    performDoctor(optionB);
                    break;
                case TECHNICIAN:
                    Technician tech = new Technician(user.get().getLogin(), user.get().getPassword(), user.get().getDepartment());
                    technicianApp = new TechnicianApp(tech, storage, viewStorage, archives);
                    technicianApp.start();
                    break;
                default:
                    System.out.println("Invalid department.");
            }
        }
        else {
            System.out.println("Invalid login or password.");
        }
    }

    private String chooseAdminOption() {
        System.out.println("What do you want to do?");
        System.out.println("(1) Show all users");
        System.out.println("(2) Delete user");

        return scanner.nextLine();
    }

    private void performAdmin (String option) {
        switch (option) {
            case "1":
            case "Show all users":
                break;
            case "2":
            case "Delete user":
                break;
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






}
