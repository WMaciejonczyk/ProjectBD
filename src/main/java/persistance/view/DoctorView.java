package persistance.view;


import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import types.EqType;
import types.EqStatus;


@Entity
@Immutable
@Table(name = "doctor_view", schema = "medical_equipment")
public class DoctorView {
    @Id
    @Column(name = "eq_id")
    private String eqID;
    @Basic
    @Column(name = "eq_name")
    private String eqName;
    @Basic
    @Column(name = "eq_type")
    @Enumerated(EnumType.STRING)
    private EqType eqType;

    public String getEqID() {
        return eqID;
    }
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorView that = (DoctorView) o;
        if (eqID != null ? !eqID.equals(that.eqID) : that.eqID != null) return false;
        if (eqName != null ? !eqName.equals(that.eqName) : that.eqName != null) return false;
        if (eqType != null ? !eqType.equals(that.eqType) : that.eqType != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = eqName != null ? eqName.hashCode() : 0;
        result = 31 * result + (eqType != null ? eqType.hashCode() : 0);
        return result;
    }
}