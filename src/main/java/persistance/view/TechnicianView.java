package persistance.view;


import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import types.EqType;

import java.sql.Date;

@Entity
@Immutable
@Table(name = "technician_view", schema = "medical_equipment")
public class TechnicianView {
    @Id
    @Column(name = "eq_name")
    private String eqName;
    @Basic
    @Column(name = "eq_type")
    @Enumerated(EnumType.STRING)
    private EqType eqType;
    @Basic
    @Column(name = "next_service_date")
    private Date nextServiceDate;

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public EqType getEqType() {
        return eqType;
    }

    public void setEqType(EqType eqType) {
        this.eqType = eqType;
    }

    public Date getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(Date nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TechnicianView that = (TechnicianView) o;

        if (eqName != null ? !eqName.equals(that.eqName) : that.eqName != null) return false;
        if (eqType != null ? !eqType.equals(that.eqType) : that.eqType != null) return false;
        if (nextServiceDate != null ? !nextServiceDate.equals(that.nextServiceDate) : that.nextServiceDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eqName != null ? eqName.hashCode() : 0;
        result = 31 * result + (eqType != null ? eqType.hashCode() : 0);
        result = 31 * result + (nextServiceDate != null ? nextServiceDate.hashCode() : 0);
        return result;
    }
}
