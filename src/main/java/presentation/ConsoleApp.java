package presentation;
import persistance.archives.IArchives;
import persistance.equipment.IEquipmentStorage;
import persistance.reservations.IReservationsRepository;
import persistance.view.DoctorViewStorage;
import persistance.view.TechnicianViewStorage;
import types.Department;
import service.IAdmin;
import service.Staff;
import service.Technician;
import service.Doctor;

import javax.print.Doc;
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
    private DoctorApp doctorApp;
    private IReservationsRepository reservations;
    private DoctorViewStorage DocViewStorage;

    public ConsoleApp(Scanner scanner, IAdmin admin, IEquipmentStorage storage,
                      TechnicianViewStorage viewStorage, IArchives archives,
                      DoctorViewStorage DocViewStorage, IReservationsRepository reservations) {
        this.scanner = scanner;
        this.admin = admin;
        this.storage = storage;
        this.viewStorage = viewStorage;
        this.archives = archives;
        this.DocViewStorage = DocViewStorage;
        this.reservations = reservations;
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
                    Doctor doc = new Doctor(user.get().getLogin(), user.get().getPassword(), user.get().getDepartment());
                    doctorApp = new DoctorApp(doc, storage, DocViewStorage, reservations);
                    doctorApp.start();
                    break;
                case TECHNICIAN:
                    Technician tech = new Technician(user.get().getLogin(), user.get().getPassword(), user.get().getDepartment());
                    technicianApp = new TechnicianApp(tech, storage, viewStorage, archives);
                    technicianApp.start();
                    break;
                default:
                    System.out.println("Invalid department.");
            }
        } else {
            System.out.println("Invalid login or password.");
        }
    }

    private String chooseAdminOption() {
        System.out.println("What do you want to do?");
        System.out.println("(1) Show all users");
        System.out.println("(2) Delete user");
        return scanner.nextLine();
    }

    private void performAdmin(String option) {
        switch (option) {
            case "1":
            case "Show all users":
                showAllUsers();
                break;
            case "2":
            case "Delete user":
                deleteUser();
                break;
        }
    }

    private void showAllUsers() {
        System.out.println("User      Department");
        for (Staff s : admin.getAllUsers()) {
            if (!s.getDepartment().name().equalsIgnoreCase("admin")) {
                String leftAlignment = "%-9s %-10s %n";
                System.out.format(leftAlignment, s.getLogin(), s.getDepartment());
            }
        }
    }

    private void deleteUser() {
        System.out.println("Enter username:");
        var username = scanner.nextLine();
        Staff staff = admin.getAllUsers().stream().filter(u -> u.getLogin().equals(username)).findFirst().get();
        System.out.println(staff.getLogin() + "  " + staff.getDepartment());
        System.out.println("Are you sure about deletion? (y/n)");
        var response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            admin.deleteUser(username);
        }
    }
}