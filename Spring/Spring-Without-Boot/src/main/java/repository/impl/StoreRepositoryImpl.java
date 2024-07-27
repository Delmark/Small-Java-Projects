package repository.impl;

import models.BookStore;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import repository.StoreRepository;

import java.util.Optional;

@Repository
public class StoreRepositoryImpl implements StoreRepository {

    private final SessionFactory sessionFactory;

    public StoreRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<BookStore> findById(Long id) {
        sessionFactory.getCurrentSession().beginTransaction();
        Optional<BookStore> store = sessionFactory.getCurrentSession().byId(BookStore.class).loadOptional(id);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return store;
    }

    @Override
    public BookStore save(BookStore bookStore) {
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().persist(bookStore);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return bookStore;
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().remove(
                sessionFactory.getCurrentSession().byId(BookStore.class).load(id)
        );
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
