package service;

import persistance.entity.InfoEntity;
import persistance.entity.UsersEntity;
import persistance.equipment.IEquipmentStorage;
import types.Department;
import types.EqStatus;
import types.EqType;

import java.sql.Date;

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

    private Equipment mapUsersEntityToStaff(InfoEntity infoEntity) {
        if (infoEntity == null) {
            return null;
        }
        return new Equipment(infoEntity.getEqName(), infoEntity.getEqType(),
                infoEntity.getProductionDate(), infoEntity.getLastServiceDate(),
                infoEntity.getEqStatus(), infoEntity.getServiceValidityPeriod());
    }
}
