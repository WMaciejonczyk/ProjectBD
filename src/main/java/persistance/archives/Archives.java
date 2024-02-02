package persistance.archives;

import jakarta.persistence.EntityManagerFactory;
import persistance.entity.ServiceArchivesEntity;

import java.util.List;

public class Archives implements IArchives {
    private final EntityManagerFactory sessionManager;

    public Archives (EntityManagerFactory sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void addToArchives(ServiceArchivesEntity serviceArchivesEntity) {
        var entityManager = sessionManager.createEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();

        transaction.commit();
    }

    @Override
    public List<ServiceArchivesEntity> getAllServices() {
        try (var entityManager = sessionManager.createEntityManager()) {
            var stringQuery = "SELECT b FROM ServiceArchivesEntity b";
            var query = entityManager.createQuery(stringQuery, ServiceArchivesEntity.class);
            return query.getResultList();
        }
    }

    @Override
    public ServiceArchivesEntity getOneService(int id) {
        try (var entityManager = sessionManager.createEntityManager()) {
            return entityManager.find(ServiceArchivesEntity.class, id);
        }
    }
}
