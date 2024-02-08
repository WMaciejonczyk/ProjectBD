import jakarta.persistence.Persistence;
import persistance.archives.Archives;
import persistance.equipment.EquipmentStorage;
import persistance.reservations.ReservationsRepository;
import persistance.users.Users;
import persistance.view.DoctorViewStorage;
import persistance.view.TechnicianViewStorage;
import presentation.ConsoleApp;
import service.*;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // setup
        var scanner = new Scanner(System.in);
        var emf = Persistence.createEntityManagerFactory("default");

        var users = new Users(emf);
        var admin = new Admin(users);
        var equipment = new EquipmentStorage(emf);
        var technicianView = new TechnicianViewStorage(emf);
        var archives = new Archives(emf);
        var reservations = new ReservationsRepository(emf);
        var doctorView = new DoctorViewStorage(emf);
        var consoleApp = new ConsoleApp(scanner, admin, equipment, technicianView, archives, doctorView, reservations);
        // start
        consoleApp.start();

        // close app
        scanner.close();
        emf.close();
    }
}