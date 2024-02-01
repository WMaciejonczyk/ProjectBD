package persistance.users;

import jakarta.persistence.EntityManagerFactory;
import persistance.entity.UsersEntity;
import java.util.List;

public class Users implements IUsers {
    private final EntityManagerFactory sessionManager;

    public Users (EntityManagerFactory sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void addUser(UsersEntity usersEntity) {
        var entityManager = sessionManager.createEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(usersEntity);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public void deleteUser(String login) {
        var entityManager = sessionManager.createEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();

        UsersEntity u = entityManager.find(UsersEntity.class, login);
        entityManager.remove(u);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        try (var entityManager = sessionManager.createEntityManager()) {
            var stringQuery = "SELECT u FROM UsersEntity u";
            var query = entityManager.createQuery(stringQuery, UsersEntity.class);
            return query.getResultList();
        }
    }
}
