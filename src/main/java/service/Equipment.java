package service;

import types.EqStatus;
import types.EqType;

import java.sql.Date;

public class Equipment {
    private String name;
    private EqType type;
    private Date productionDate;
    private Date lastServiceDate;
    private EqStatus status;
    private short validity;

    public Equipment(String name, EqType type, Date productionDate, Date lastServiceDate, EqStatus status, short validity) {
        this.name = name;
        this.type = type;
        this.productionDate = productionDate;
        this.lastServiceDate = lastServiceDate;
        this.status = status;
        this.validity = validity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EqType getType() {
        return type;
    }

    public void setType(EqType type) {
        this.type = type;
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

    public EqStatus getStatus() {
        return status;
    }

    public void setStatus(EqStatus status) {
        this.status = status;
    }

    public short getValidity() {
        return validity;
    }

    public void setValidity(short validity) {
        this.validity = validity;
    }
}