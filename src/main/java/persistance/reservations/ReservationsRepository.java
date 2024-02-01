package persistance.reservations;

import jakarta.persistence.EntityManager;
import persistance.entity.ReservationsEntity;

import java.util.List;

public class ReservationsRepository implements IReservationsRepository {
    private final EntityManager entityManager;

    public ReservationsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addReservation(ReservationsEntity reservationsEntity) {
        var transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(reservationsEntity);

        transaction.commit();
    }

    @Override
    public List<ReservationsEntity> getAllReservations() {
        var stringQuery = "SELECT b FROM ReservationsEntity b";
        var query = entityManager.createQuery(stringQuery, ReservationsEntity.class);

        return query.getResultList();
    }

    @Override
    public ReservationsEntity getOneReservation(int id) {
        return entityManager.find(ReservationsEntity.class, id);
    }
}
