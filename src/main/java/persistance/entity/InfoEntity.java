package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "info", schema = "medical_equipment")
public class InfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "eq_id")
    private int eqId;
    @Basic
    @Column(name = "eq_name")
    private String eqName;
    @Basic
    @Column(name = "eq_type")
    private Object eqType;
    @Basic
    @Column(name = "production_date")
    private Date productionDate;
    @Basic
    @Column(name = "last_service_date")
    private Date lastServiceDate;
    @Basic
    @Column(name = "eq_status")
    private Object eqStatus;
    @Basic
    @Column(name = "service_validity_period")
    private short serviceValidityPeriod;
    @OneToMany(mappedBy = "infoByEquipmentId")
    private Collection<ReservationsEntity> reservationsByEqId;
    @OneToMany(mappedBy = "infoByEquipmentId")
    private Collection<ServiceArchivesEntity> serviceArchivesByEqId;

    public int getEqId() {
        return eqId;
    }

    public void setEqId(int eqId) {
        this.eqId = eqId;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public Object getEqType() {
        return eqType;
    }

    public void setEqType(Object eqType) {
        this.eqType = eqType;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public Object getEqStatus() {
        return eqStatus;
    }

    public void setEqStatus(Object eqStatus) {
        this.eqStatus = eqStatus;
    }

    public short getServiceValidityPeriod() {
        return serviceValidityPeriod;
    }

    public void setServiceValidityPeriod(short serviceValidityPeriod) {
        this.serviceValidityPeriod = serviceValidityPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoEntity that = (InfoEntity) o;

        if (eqId != that.eqId) return false;
        if (serviceValidityPeriod != that.serviceValidityPeriod) return false;
        if (eqName != null ? !eqName.equals(that.eqName) : that.eqName != null) return false;
        if (eqType != null ? !eqType.equals(that.eqType) : that.eqType != null) return false;
        if (productionDate != null ? !productionDate.equals(that.productionDate) : that.productionDate != null)
            return false;
        if (lastServiceDate != null ? !lastServiceDate.equals(that.lastServiceDate) : that.lastServiceDate != null)
            return false;
        if (eqStatus != null ? !eqStatus.equals(that.eqStatus) : that.eqStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eqId;
        result = 31 * result + (eqName != null ? eqName.hashCode() : 0);
        result = 31 * result + (eqType != null ? eqType.hashCode() : 0);
        result = 31 * result + (productionDate != null ? productionDate.hashCode() : 0);
        result = 31 * result + (lastServiceDate != null ? lastServiceDate.hashCode() : 0);
        result = 31 * result + (eqStatus != null ? eqStatus.hashCode() : 0);
        result = 31 * result + (int) serviceValidityPeriod;
        return result;
    }

    public Collection<ReservationsEntity> getReservationsByEqId() {
        return reservationsByEqId;
    }

    public void setReservationsByEqId(Collection<ReservationsEntity> reservationsByEqId) {
        this.reservationsByEqId = reservationsByEqId;
    }

    public Collection<ServiceArchivesEntity> getServiceArchivesByEqId() {
        return serviceArchivesByEqId;
    }

    public void setServiceArchivesByEqId(Collection<ServiceArchivesEntity> serviceArchivesByEqId) {
        this.serviceArchivesByEqId = serviceArchivesByEqId;
    }
}
