package service;

import java.sql.Date;

public class ArchiveEntry {
    private Date serviceDate;
    private int equipmentId;
    private String technician;
    private String description;

    public ArchiveEntry(Date serviceDate, int equipmentId, String technician, String description) {
        this.serviceDate = serviceDate;
        this.equipmentId = equipmentId;
        this.technician = technician;
        this.description = description;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
