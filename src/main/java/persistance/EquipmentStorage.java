package persistance;

import jakarta.persistence.EntityManager;
import persistance.entity.InfoEntity;

import java.util.List;

public class EquipmentStorage implements IEquipmentStorage {
    private final EntityManager entityManager;

    public EquipmentStorage(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addEquipment(InfoEntity infoEntity) {
        var transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(infoEntity);

        transaction.commit();
    }

    @Override
    public List<InfoEntity> getAllEquipment() {
        var stringQuery = "SELECT b FROM InfoEntity b";
        var query = entityManager.createQuery(stringQuery, InfoEntity.class);

        return query.getResultList();
    }

    @Override
    public InfoEntity getOneEquipment(int id) {
        return entityManager.find(InfoEntity.class, id);
    }
}