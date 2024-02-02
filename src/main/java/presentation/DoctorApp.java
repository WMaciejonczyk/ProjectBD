package presentation;


import persistance.archives.IArchives;
import persistance.entity.InfoEntity;
import persistance.equipment.IEquipmentStorage;
import persistance.reservations.IReservationsRepository;
import persistance.view.DoctorViewStorage;
import service.*;
import types.EqStatus;
import types.EqType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                startReservation();
                break;
            case "2":
            case "Change equipment usage status":
                reportService();
                break;
            case "3":
            case "Show a list of equipment (with status)":
                List<InfoEntity> AvailableItems = doc.getAvailableDevices(storage);
                System.out.println("Name  Type   Status");
                for (DoctorViewItem item : items) {
                    System.out.println(item.getName() + "   " + item.getType() + "  " + item.getStatus());
                }
                break;
            case "4":
            case "Show all reservations of a given equipment":
                showArchiveEntries();
        }
    }

    private void startReservation() {
        if (storage.getAllEquipment().isEmpty()) {
            System.out.println("Storage is empty.");
        } else {
            List<DoctorViewItem> items = doc.getAllItems(viewStorage);
            System.out.println("ID  Name   Type");
            for (DoctorViewItem item : items) {
                System.out.println(item.getId() + "   " + item.getName() + "   " + item.getType() + "  ");
            }
            System.out.println("Which device would you like to reserve? Enter ID.");
            int deviceID = Integer.parseInt(scanner.nextLine());
            System.out.println("Start of reservation date in a (yyyy-mm-dd) format) ");
            var reservationStart = Date.valueOf(scanner.nextLine());
            System.out.println("End of reservation date in a (yyyy-mm-dd) format) ");
            var reservationEnd=  Date.valueOf(scanner.nextLine());
            doc.reserveDevice(deviceID, reservationStart, reservationEnd, storage);
        }
    }

    private void useDevice() {
        List<InfoEntity> servicedDevices = tech.getServicedDevices(storage);
        if (servicedDevices.isEmpty()) {
            System.out.println("No device is being serviced.");
        } else {
            System.out.println("On which device do you wish to end service?");
            System.out.println("ID  Name       Type");
            String leftAlignment = "(%-1s) %-10s %-5s %n";
            for (InfoEntity e : servicedDevices) {
                System.out.format(leftAlignment, e.getEqId(), e.getEqName(), e.getEqType());
            }
            int deviceNumber = Integer.parseInt(scanner.nextLine());
            tech.endServiceOnDevice(deviceNumber, storage);
            System.out.println("Enter your service description.");
            var description = scanner.nextLine();
            tech.sendToServiceArchives(deviceNumber, description, archives, storage);
        }
    }

    private String chooseFilterOption() {
        System.out.println("Which filter do you want to use?");
        System.out.println("(1) Show only ECG type devices");
        System.out.println("(2) Show only EMG type devices");
        System.out.println("(3) Show only RTG type devices");
        System.out.println("(4) Show only MRI type devices");
        System.out.println("(5) Show devices with service expiring in 2 weeks");
        System.out.println("(6) Show devices with service expiring in 1 month");
        System.out.println("(7) Show devices with service expiring in half a year");
        System.out.println("(8) Show devices with expired service");

        return scanner.nextLine();
    }

    private void performFilter(String option) {
        List<TechnicianViewItem> items = tech.getAllItems(viewStorage);
        List<TechnicianViewItem> filteredItems;
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        switch (option) {
            case "1":
            case "Show only ECG type devices":
                filteredItems = items.stream().filter(p -> p.getType().name().equalsIgnoreCase("ecg")).collect(Collectors.toList());
                break;
            case "2":
            case "Show only EMG type devices":
                filteredItems = items.stream().filter(p -> p.getType().name().equalsIgnoreCase("emg")).collect(Collectors.toList());
                break;
            case "3":
            case "Show only RTG type devices":
                filteredItems = items.stream().filter(p -> p.getType().name().equalsIgnoreCase("rtg")).collect(Collectors.toList());
                break;
            case "4":
            case "Show only MRI type devices":
                filteredItems = items.stream().filter(p -> p.getType().name().equalsIgnoreCase("mri")).collect(Collectors.toList());
                break;
            case "5":
            case "Show devices with service expiring in 2 weeks":
                cal.add(Calendar.DATE, 14);
                filteredItems = items.stream().filter(p -> !p.getNextServiceDate().after(cal.getTime())).
                        filter(p -> p.getNextServiceDate().after(cal2.getTime())).collect(Collectors.toList());
                break;
            case "6":
            case "Show devices with service expiring in 1 month":
                cal.add(Calendar.DATE, 30);
                filteredItems = items.stream().filter(p -> !p.getNextServiceDate().after(cal.getTime())).
                        filter(p -> p.getNextServiceDate().after(cal2.getTime())).collect(Collectors.toList());
                break;
            case "7":
            case "Show devices with service expiring in half a year":
                cal.add(Calendar.DATE, 182);
                filteredItems = items.stream().filter(p ->  p.getNextServiceDate().before(cal.getTime())).
                        filter(p -> p.getNextServiceDate().after(cal2.getTime())).collect(Collectors.toList());
                break;
            case "8":
            case "Show devices with expired service":
                filteredItems = items.stream().filter(p -> p.getNextServiceDate().before(cal2.getTime())).collect(Collectors.toList());
                break;
            default:
                filteredItems = null;
                break;
        }
        if (filteredItems.isEmpty()) {
            System.out.println("There are not any searched devices.");
        }
        else {
            System.out.println("Name       Type    Next service date");
            for (TechnicianViewItem item : filteredItems) {
                String leftAlignment = "%-10s %-5s %-19s %n";
                System.out.format(leftAlignment, item.getName(), item.getType(), item.getNextServiceDate());
            }
        }
    }

    private void addEquipment() {
        System.out.println("Enter equipment name:");
        var name = scanner.nextLine();
        System.out.println("Enter equipment type:");
        var type = scanner.nextLine().toUpperCase();
        System.out.println("Enter production date:");
        var productionDate = scanner.nextLine();
        System.out.println("Enter last service date:");
        var serviceDate = scanner.nextLine();
        System.out.println("Enter service validity period:");
        var validity = scanner.nextShort();
        tech.addEquipment(new Equipment(name, EqType.valueOf(type), Date.valueOf(productionDate),
                Date.valueOf(serviceDate), EqStatus.valueOf("FREE"), validity), storage);
    }

    private void showArchiveEntries() {
        List<ArchiveEntry> entries = tech.getAllArchivesEntries(archives);
        System.out.println("Service date   EquipmentID   Technician user  Description");
        for (ArchiveEntry entry : entries) {
            String leftAlignment = "%-14s %-13s %-16s %-20s %n";
            System.out.format(leftAlignment, entry.getServiceDate(), entry.getEquipmentId(), entry.getTechnician(), entry.getDescription());
        }
    }
}