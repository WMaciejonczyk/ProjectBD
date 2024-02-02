package presentation;


import persistance.entity.InfoEntity;
import persistance.entity.ReservationsEntity;
import persistance.equipment.IEquipmentStorage;
import persistance.reservations.IReservationsRepository;
import persistance.view.DoctorViewStorage;
import service.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class DoctorApp {

    private Doctor doc;
    private IEquipmentStorage storage;
    private IReservationsRepository reservations;
    private DoctorViewStorage viewStorage;
    private Scanner scanner;

    public DoctorApp(Doctor doc, IEquipmentStorage storage, DoctorViewStorage viewStorage, IReservationsRepository reservations){
        this.doc = doc;
        scanner = new Scanner(System.in);
        this.storage = storage;
        this.viewStorage = viewStorage;
        this.reservations = reservations;
    }

    public void start(){
        while (true) {
            var option = chooseDoctorOption();
            if (option.equalsIgnoreCase("q") || option.equalsIgnoreCase("quit")) {
                break;
            }
            performDoctor(option);
            System.out.println();
            System.out.println();
        }

    }

    private String chooseDoctorOption() {
        System.out.println("What do you want to do?");
        System.out.println("(1) Reserve equipment");
        System.out.println("(2) Change equipment usage status");
        System.out.println("(3) Show a list of equipment (with status)");
        System.out.println("(4) Show all reservations of a given equipment");

        return scanner.nextLine();
    }

    private void performDoctor (String option) {
        switch (option) {
            case "1":
            case "Reserve equipment":
                //startReservation();
                break;
            case "2":
            case "Use equipment":
                List<InfoEntity> AvailableDevices = doc.getAvailableDevices(storage);
                System.out.println("Available items (ID,Name,Type, Status):");
                for (InfoEntity item : AvailableDevices) {
                    System.out.println(item.getEqId()+ "   " +  item .getEqName() + "   " + item.getEqType()
                            + "  " + item.getEqStatus());
                }
                break;
            case "3":
            case "Show a list of equipment (with status)":
                List<InfoEntity> StatusItems = doc.getDevices(storage);
                System.out.println("Items(ID,Name,Type, Status):");
                for (InfoEntity item : StatusItems) {
                    System.out.println(item.getEqId()+ "   " +  item .getEqName() + "   " + item.getEqType()
                            + "  " + item.getEqStatus());
                }
                break;
            case "4":
            case "Show all reservations of a given equipment":
                System.out.println("Which device would you like to see reservations for? Enter ID.");
                String deviceID = scanner.nextLine();
                List<ReservationsEntity>ReservationsForDevice = doc.getReservationsForItem(deviceID, reservations);
                System.out.println("DeviceID, Reservation Start, Reservation End, Reserver");
                for (ReservationsEntity reservation : ReservationsForDevice) {
                    System.out.println(reservation.getEquipmentId() + "   " +  reservation.getStartDate() + "   " + reservation.getEndDate()
                            + "  " + reservation.getReserver());
                }
                break;
        }
    }

    private void startReservation() {
        if (storage.getAllEquipment().isEmpty()) {
            System.out.println("Storage is empty.");
        } else {
            List<InfoEntity> items = doc.getDevices(storage);
            System.out.println("ID  Name   Type");
            for (InfoEntity item : items) {
                System.out.println(item.getEqId() + "   " + item.getEqName() + "   " + item.getEqType() + "  ");
            }
            System.out.println("Which device would you like to reserve? Enter ID.");
            int deviceID = Integer.parseInt(scanner.nextLine());
            System.out.println("Start of reservation date in a (yyyy-mm-dd) format) ");
            var reservationStart = Date.valueOf(scanner.nextLine());
            System.out.println("End of reservation date in a (yyyy-mm-dd) format) ");
            var reservationEnd=  Date.valueOf(scanner.nextLine());
            doc.reserveDevice(deviceID, reservationStart, reservationEnd, reservations);
        }
    }
}