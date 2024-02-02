package persistance.view;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class DoctorViewStorage {
    private final EntityManagerFactory sessionManager;

    public DoctorViewStorage(EntityManagerFactory sessionManager) {
        this.sessionManager = sessionManager;
    }

    public List<DoctorView> getAllItems() {
        try (var entityManager = sessionManager.createEntityManager()) {
            var stringQuery = "SELECT b FROM DoctorView b";
            var query = entityManager.createQuery(stringQuery, DoctorView.class);

            return query.getResultList();
        }
    }
}