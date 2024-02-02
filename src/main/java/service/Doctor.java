package service;

import persistance.archives.IArchives;
import persistance.entity.InfoEntity;
import persistance.entity.ReservationsEntity;
import persistance.entity.ServiceArchivesEntity;
import persistance.equipment.IEquipmentStorage;
import persistance.reservations.IReservationsRepository;
import persistance.view.DoctorView;
import types.Department;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Doctor extends Staff {
    public Doctor(String login, String password, Department department) {
        super(login, password, department);
    }


    private DoctorViewItem mapDoctorViewToItems(DoctorView view) {
        if (view == null) {
            return null;
        }
        return new DoctorViewItem(view.getEqID(), view.getEqName(), view.getEqType() );
    }

    public List<InfoEntity> getDevices(IEquipmentStorage storage) {
        return new ArrayList<>(storage.getAllEquipment());
    }

    public List<ReservationsEntity> getReservations(IReservationsRepository reservationsRepository) {
        return new ArrayList<>(reservationsRepository.getAllReservations());
    }

    public List<ReservationsEntity> getReservationsForItem(String itemID, IReservationsRepository reservationsRepository) {
        Integer IntItemID = Integer.parseInt(itemID);
        return reservationsRepository.getAllReservations().stream().filter(value -> value.getReservationId() == IntItemID).collect(Collectors.toList());
    }
    public List<InfoEntity> getAvailableDevices(IEquipmentStorage storage) {
        return storage.getAllEquipment().stream().
                filter(e -> e.getEqStatus().name().equalsIgnoreCase("FREE")).collect(Collectors.toList());
    }
    public void reserveDevice(Integer deviceID, Date reservationStart, Date reservationEnd, IReservationsRepository reservationsRepository) {
        ReservationsEntity DeviceReservation = reservationsRepository.getOneReservation(deviceID);


    }
}
