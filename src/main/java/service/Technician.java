package service;

import persistance.archives.IArchives;
import persistance.entity.InfoEntity;
import persistance.entity.ServiceArchivesEntity;
import persistance.equipment.IEquipmentStorage;
import persistance.view.TechnicianView;
import persistance.view.TechnicianViewStorage;
import types.Department;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Technician extends Staff {
    public Technician(String login, String password, Department department) {
        super(login, password, department);
    }

    public void addEquipment(Equipment equipment, IEquipmentStorage storage) {
        storage.addEquipment(mapEquipmentToInfoEntity(equipment));
    }

    private InfoEntity mapEquipmentToInfoEntity(Equipment equipment) {
        var infoEntity = new InfoEntity();
        infoEntity.setEqName(equipment.getName());
        infoEntity.setEqType(equipment.getType());
        infoEntity.setProductionDate(equipment.getProductionDate());
        infoEntity.setLastServiceDate(equipment.getLastServiceDate());
        infoEntity.setEqStatus(equipment.getStatus());
        infoEntity.setServiceValidityPeriod(equipment.getValidity());
        return infoEntity;
    }

    private Equipment mapInfoEntityToEquipment(InfoEntity infoEntity) {
        if (infoEntity == null) {
            return null;
        }
        return new Equipment(infoEntity.getEqName(), infoEntity.getEqType(),
                infoEntity.getProductionDate(), infoEntity.getLastServiceDate(),
                infoEntity.getEqStatus(), infoEntity.getServiceValidityPeriod());
    }

    private TechnicianViewItem mapTechnicianViewToItems(TechnicianView view) {
        if (view == null) {
            return null;
        }
        return new TechnicianViewItem(view.getEqName(), view.getEqType(), view.getNextServiceDate());
    }

    public List<TechnicianViewItem> getAllItems(TechnicianViewStorage storage) {
        return storage.getAllItems().stream().map(this::mapTechnicianViewToItems).collect(Collectors.toList());
    }

    public List<InfoEntity> getDevicesToService(IEquipmentStorage storage) {
        return storage.getAllEquipment().stream().
                filter(e -> e.getEqStatus().name().equalsIgnoreCase("FREE")).collect(Collectors.toList());
    }

    public void serviceDevice(int id, IEquipmentStorage storage) {
        InfoEntity infoEntity = storage.getOneEquipment(id);
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
