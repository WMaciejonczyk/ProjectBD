package persistance.equipment;

import persistance.entity.InfoEntity;

import java.time.LocalDate;
import java.util.List;

public interface IEquipmentStorage {
    void addEquipment(InfoEntity infoEntity);
    List<InfoEntity> getAllEquipment();
    InfoEntity getOneEquipment(int id);
    void updateEquipmentStatus(int id, String status);
    void updateEquipmentServiceDate(int id, LocalDate data);
}
