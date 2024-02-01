package persistance.equipment;

import jakarta.persistence.EntityManagerFactory;
import persistance.entity.InfoEntity;

import java.util.List;

public class EquipmentStorage implements IEquipmentStorage {
    private final EntityManagerFactory sessionManager;

    public EquipmentStorage(EntityManagerFactory sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void addEquipment(InfoEntity infoEntity) {
        var entityManager = sessionManager.createEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(infoEntity);

        transaction.commit();
    }

    @Override
    public List<InfoEntity> getAllEquipment() {
        try (var entityManager = sessionManager.createEntityManager()) {
            var stringQuery = "SELECT b FROM InfoEntity b";
            var query = entityManager.createQuery(stringQuery, InfoEntity.class);

            return query.getResultList();
        }
    }

    @Override
    public InfoEntity getOneEquipment(int id) {
        try (var entityManager = sessionManager.createEntityManager()) {
            return entityManager.find(InfoEntity.class, id);
        }
    }
}