package persistance;

import jakarta.persistence.EntityManager;
import persistance.entity.UsersEntity;

import java.util.List;

public class Users implements IUsers {
    private final EntityManager entityManager;

    public Users (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(UsersEntity usersEntity) {
        var transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(usersEntity);

        transaction.commit();
    }

    @Override
    public void deleteUser(String login) {
        var transaction = entityManager.getTransaction();
        transaction.begin();

        UsersEntity u = entityManager.find(UsersEntity.class, login);
        entityManager.remove(u);
        transaction.commit();
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        var stringQuery = "SELECT u FROM UsersEntity u";
        var query = entityManager.createQuery(stringQuery, UsersEntity.class);
        List<UsersEntity> list = query.getResultList();
        list.remove(0); // admin

        return list;
    }
}
