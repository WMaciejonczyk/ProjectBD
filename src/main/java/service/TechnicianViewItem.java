package service;

import types.EqType;

import java.sql.Date;

public class TechnicianViewItem {
    private String name;
    private EqType type;
    private Date nextServiceDate;

    public TechnicianViewItem(String name, EqType type, Date nextServiceDate) {
        this.name = name;
        this.type = type;
        this.nextServiceDate = nextServiceDate;
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

    public Date getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(Date nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }
}