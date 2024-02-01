package persistance.reservations;

import persistance.entity.ReservationsEntity;

import java.util.List;

public interface IReservationsRepository {
    void addReservation(ReservationsEntity reservationsEntity);
    List<ReservationsEntity> getAllReservations();
    ReservationsEntity getOneReservation(int id);
}
