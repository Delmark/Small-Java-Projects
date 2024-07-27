package repository.impl;

import org.hibernate.SessionFactory;
import repository.BookRepository;
import models.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final SessionFactory sessionFactory;

    public BookRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Book> findById(Long id) {
        sessionFactory.getCurrentSession().beginTransaction();
        Optional<Book> book = sessionFactory.getCurrentSession().byId(Book.class).loadOptional(id);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return book;
    }

    @Override
    public Book save(Book book) {
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().persist(book);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return book;
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().remove(
                sessionFactory.getCurrentSession().byId(Book.class).load(id)
        );
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
