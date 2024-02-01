package persistance.archives;

import jakarta.persistence.EntityManager;
import persistance.entity.ServiceArchivesEntity;

import java.util.List;

public class Archives implements IArchives {
    private final EntityManager entityManager;

    public Archives (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addToArchives(ServiceArchivesEntity serviceArchivesEntity) {
        var transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(serviceArchivesEntity);

        transaction.commit();
    }

    @Override
    public List<ServiceArchivesEntity> getAllServices() {
        var stringQuery = "SELECT b FROM ServiceArchivesEntity b";
        var query = entityManager.createQuery(stringQuery, ServiceArchivesEntity.class);

        return query.getResultList();
    }

    @Override
    public ServiceArchivesEntity getOneService(int id) {
        return entityManager.find(ServiceArchivesEntity.class, id);
    }
}
