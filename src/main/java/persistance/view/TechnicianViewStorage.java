package persistance.view;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class TechnicianViewStorage {
    private final EntityManagerFactory sessionManager;

    public TechnicianViewStorage(EntityManagerFactory sessionManager) {
        this.sessionManager = sessionManager;
    }

    public List<TechnicianView> getAllItems() {
        try (var entityManager = sessionManager.createEntityManager()) {
            var stringQuery = "SELECT b FROM TechnicianView b";
            var query = entityManager.createQuery(stringQuery, TechnicianView.class);

            return query.getResultList();
        }
    }
}
