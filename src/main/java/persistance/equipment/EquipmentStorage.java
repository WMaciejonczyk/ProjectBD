package persistance.equipment;

import jakarta.persistence.EntityManagerFactory;
import persistance.entity.InfoEntity;
import types.EqStatus;

import java.sql.Date;
import java.time.LocalDate;
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

    @Override
    public void updateEquipmentStatus(int id, String status) {
        var entityManager = sessionManager.createEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();
        var stringQuery = "UPDATE InfoEntity b SET b.eqStatus = :status WHERE b.eqId = :id";
        var query = entityManager.createQuery(stringQuery);
        query.setParameter("status", EqStatus.valueOf(status));
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void updateEquipmentServiceDate(int id, LocalDate date) {
        var entityManager = sessionManager.createEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();
        var stringQuery = "UPDATE InfoEntity b SET b.lastServiceDate = :date WHERE b.eqId = :id";
        var query = entityManager.createQuery(stringQuery);
        query.setParameter("date", Date.valueOf(date));
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }
}