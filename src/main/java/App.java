import jakarta.persistence.Persistence;
import persistance.equipment.EquipmentStorage;
import persistance.users.Users;
import presentation.ConsoleApp;
import service.Admin;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // setup
        var scanner = new Scanner(System.in);
        var emf = Persistence.createEntityManagerFactory("default");;

        var users = new Users(emf);
        var admin = new Admin(users);
        var equipment = new EquipmentStorage(emf);
        var consoleApp = new ConsoleApp(scanner, admin, equipment);
        // start
        consoleApp.start();

        // close app
        scanner.close();
        emf.close();
    }
}