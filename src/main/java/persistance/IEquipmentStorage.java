package persistance;

import persistance.entity.InfoEntity;

import java.util.List;

public interface IEquipmentStorage {
    void addEquipment(InfoEntity infoEntity);
    List<InfoEntity> getAllEquipment();
    InfoEntity getOneEquipment(int id);
}
