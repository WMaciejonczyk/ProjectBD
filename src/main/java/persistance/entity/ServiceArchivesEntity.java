package persistance.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "service_archives", schema = "medical_equipment")
public class ServiceArchivesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "service_id")
    private int serviceId;
    @Basic
    @Column(name = "service_date")
    private Date serviceDate;
    @Basic
    @Column(name = "equipment_id", insertable = false, updatable = false)
    private int equipmentId;
    @Basic
    @Column(name = "technician")
    private String technician;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "equipment_id", referencedColumnName = "eq_id", nullable = false)
    private InfoEntity infoByEquipmentId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceArchivesEntity that = (ServiceArchivesEntity) o;

        if (serviceId != that.serviceId) return false;
        if (equipmentId != that.equipmentId) return false;
        if (serviceDate != null ? !serviceDate.equals(that.serviceDate) : that.serviceDate != null) return false;
        if (technician != null ? !technician.equals(that.technician) : that.technician != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceId;
        result = 31 * result + (serviceDate != null ? serviceDate.hashCode() : 0);
        result = 31 * result + equipmentId;
        result = 31 * result + (technician != null ? technician.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public InfoEntity getInfoByEquipmentId() {
        return infoByEquipmentId;
    }

    public void setInfoByEquipmentId(InfoEntity infoByEquipmentId) {
        this.infoByEquipmentId = infoByEquipmentId;
    }
}
