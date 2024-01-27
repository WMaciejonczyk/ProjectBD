package entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "reservations", schema = "medical_equipment")
public class ReservationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reservation_id")
    private int reservationId;
    @Basic
    @Column(name = "start_date")
    private Date startDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @Basic
    @Column(name = "equipment_id")
    private int equipmentId;
    @Basic
    @Column(name = "reserver")
    private String reserver;
    @ManyToOne
    @JoinColumn(name = "equipment_id", referencedColumnName = "eq_id", nullable = false)
    private InfoEntity infoByEquipmentId;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getReserver() {
        return reserver;
    }

    public void setReserver(String reserver) {
        this.reserver = reserver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationsEntity that = (ReservationsEntity) o;

        if (reservationId != that.reservationId) return false;
        if (equipmentId != that.equipmentId) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (reserver != null ? !reserver.equals(that.reserver) : that.reserver != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + equipmentId;
        result = 31 * result + (reserver != null ? reserver.hashCode() : 0);
        return result;
    }

    public InfoEntity getInfoByEquipmentId() {
        return infoByEquipmentId;
    }

    public void setInfoByEquipmentId(InfoEntity infoByEquipmentId) {
        this.infoByEquipmentId = infoByEquipmentId;
    }
}
