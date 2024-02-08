package persistance.reservations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import persistance.entity.InfoEntity;
import persistance.entity.ReservationsEntity;

import java.util.List;

public class ReservationsRepository implements IReservationsRepository {
    private final EntityManagerFactory sessionManager;

    public ReservationsRepository(EntityManagerFactory sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void addReservation(ReservationsEntity reservationsEntity) {
        var entityManager = sessionManager.createEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(reservationsEntity);

        transaction.commit();
    }

    @Override
    public List<ReservationsEntity> getAllReservations() {
        try (var entityManager = sessionManager.createEntityManager()) {
            var stringQuery = "SELECT b FROM ReservationsEntity b";
            var query = entityManager.createQuery(stringQuery, ReservationsEntity.class);

            return query.getResultList();
        }
    }

    @Override
    public ReservationsEntity getOneReservation(int id) {
        try (var entityManager = sessionManager.createEntityManager()) {
            return entityManager.find(ReservationsEntity.class, id);
        }
    }
}
