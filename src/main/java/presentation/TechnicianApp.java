package presentation;

import persistance.equipment.IEquipmentStorage;
import service.Equipment;
import service.Technician;
import types.EqStatus;
import types.EqType;
import java.sql.Date;
import java.util.Scanner;

public class TechnicianApp {

    private Technician tech;
    private IEquipmentStorage storage;
    private Scanner scanner;

    public TechnicianApp(Technician tech, IEquipmentStorage storage){
        this.tech = tech;
        scanner = new Scanner(System.in);
        this.storage = storage;
    }

    public void start(){
        var option = chooseTechnicianOption();
        performTechnician(option);

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
//                getEquipmentList();
                break;
            case "5":
            case "Add a new equipment":
                addEquipment();
                break;
        }
    }

    private void addEquipment() {
        System.out.println("Enter equipment name:");
        var name = scanner.nextLine();
        System.out.println("Enter equipment type:");
        var type = scanner.nextLine();
        System.out.println("Enter production date:");
        var productionDate = scanner.nextLine();
        System.out.println("Enter last service date:");
        var serviceDate = scanner.nextLine();
        System.out.println("Enter service validity period:");
        var validity = scanner.nextShort();
        tech.addEquipment(new Equipment(name, EqType.valueOf(type), Date.valueOf(productionDate),
                Date.valueOf(serviceDate), EqStatus.valueOf("free"), validity), storage);
    }

//    private void getEquipmentList() {
//        tech.getEquipmentList();
//    }
}
