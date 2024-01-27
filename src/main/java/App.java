import jakarta.persistence.Persistence;
import persistance.Users;
import presentation.ConsoleApp;
import service.Admin;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // setup
        var scanner = new Scanner(System.in);
        var emf = Persistence.createEntityManagerFactory("default");
        var em = emf.createEntityManager();

        var users = new Users(em);
        var admin = new Admin(users);
        var consoleApp = new ConsoleApp(scanner, admin);
        // start
        consoleApp.start();

        // close app
        scanner.close();
        em.close();
        emf.close();
    }
}