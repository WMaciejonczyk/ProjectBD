package service;

import types.EqStatus;
import types.EqType;

import java.sql.Date;

public class DoctorViewItem {

    private String id;
    private String name;
    private EqType type;

    public DoctorViewItem(String id, String name, EqType type) {
        this.name = name;
        this.type = type;
        this.id = id;
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

    public String getId() {
        return id;
    }
}