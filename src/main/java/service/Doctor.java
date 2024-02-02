package service;

import persistance.archives.IArchives;
import persistance.entity.InfoEntity;
import persistance.entity.ServiceArchivesEntity;
import persistance.equipment.IEquipmentStorage;
import persistance.view.DoctorView;
import persistance.view.DoctorViewStorage;
import types.Department;

import java.sql.Date;
import java.time.LocalDate;
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

    public List<DoctorViewItem> getAllItems(DoctorViewStorage storage) {
        return storage.getAllItems().stream().map(this::mapDoctorViewToItems).collect(Collectors.toList());
    }

    public List<InfoEntity> getDevicesToReserve(IEquipmentStorage storage) {
        return storage.getAllEquipment().stream().
                filter(e -> e.getEqStatus().name().equalsIgnoreCase("FREE")).collect(Collectors.toList());
    }


    public List<InfoEntity> getAvailableDevices(IEquipmentStorage storage) {
        return storage.getAllEquipment().stream().
                filter(e -> e.getEqStatus().name().equalsIgnoreCase("FREE")).collect(Collectors.toList());
    }





    public void reserveDevice(Integer deviceID, Date reservationStart, Date reservationEnd, IEquipmentStorage storage) {
        InfoEntity infoEntity = storage.getOneEquipment(deviceID);
        storage.updateEquipmentStatus(id, "OCCUPIED");
    }

    public List<InfoEntity> getServicedDevices(IEquipmentStorage storage) {
        return storage.getAllEquipment().stream().
                filter(e -> e.getEqStatus().name().equalsIgnoreCase("OCCUPIED")).collect(Collectors.toList());
    }

    public void endServiceOnDevice(int id, IEquipmentStorage storage) {
        InfoEntity infoEntity = storage.getOneEquipment(id);
        storage.updateEquipmentStatus(id, "FREE");
        storage.updateEquipmentServiceDate(id, LocalDate.now());
    }

    private ServiceArchivesEntity mapArchivesEntryToServiceArchivesEntity(ArchiveEntry entry) {
        var archivesEntity = new ServiceArchivesEntity();
        archivesEntity.setServiceDate(entry.getServiceDate());
        archivesEntity.setEquipmentId(entry.getEquipmentId());
        archivesEntity.setTechnician(entry.getTechnician());
        archivesEntity.setDescription(entry.getDescription());
        return archivesEntity;
    }

    private ArchiveEntry mapServiceArchivesEntityToArchiveEntry(ServiceArchivesEntity archivesEntity) {
        if (archivesEntity == null) {
            return null;
        }
        return new ArchiveEntry(archivesEntity.getServiceDate(), archivesEntity.getEquipmentId(), archivesEntity.getTechnician(),
                archivesEntity.getDescription());
    }

    public List<ArchiveEntry> getAllArchivesEntries(IArchives archives) {
        return archives.getAllServices().stream().map(this::mapServiceArchivesEntityToArchiveEntry).collect(Collectors.toList());
    }

    public void sendToServiceArchives(int id, String description, IArchives archives, IEquipmentStorage storage) {
        ServiceArchivesEntity archivesEntity = new ServiceArchivesEntity();
        Calendar calendar = Calendar.getInstance();
        archivesEntity.setServiceDate(Date.valueOf(LocalDate.now()));
        archivesEntity.setTechnician(this.getLogin());
        archivesEntity.setDescription(description);
        InfoEntity infoEntity = storage.getOneEquipment(id);
        archivesEntity.setInfoByEquipmentId(infoEntity);
        archives.addToArchives(archivesEntity);
    }
}