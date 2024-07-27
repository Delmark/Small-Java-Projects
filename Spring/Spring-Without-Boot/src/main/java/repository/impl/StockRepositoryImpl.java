package repository.impl;

import models.Book;
import models.BookStore;
import models.StoreStock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import repository.StocksRepository;

import java.util.List;

@Repository
public class StockRepositoryImpl implements StocksRepository {

    private final SessionFactory sessionFactory;

    public StockRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<StoreStock> stockQuantityByBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<StoreStock> stocks = session.createQuery("from StoreStock where book = :book", StoreStock.class)
                .setParameter("book", book)
                .list();
        session.getTransaction().commit();
        return stocks;
    }

    @Override
    public List<StoreStock> allStocksByStore(BookStore bookStore) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<StoreStock> stocks = session.createQuery("from StoreStock where store = :bookStore", StoreStock.class).setParameter("bookStore", bookStore).list();
        session.getTransaction().commit();
        return stocks;
    }

    @Override
    public List<BookStore> allStocksByBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        List<StoreStock> stocksEntries = session.createQuery("from StoreStock where book = :book", StoreStock.class)
                .setParameter("book", book).list();
        return stocksEntries.stream().map(StoreStock::getStore).toList();
    }

    @Override
    public List<Book> allBooksInStore(BookStore store) {
        Session session = sessionFactory.getCurrentSession();
        List<StoreStock> stocksEntries = session.createQuery("from StoreStock where store = :store", StoreStock.class)
                .setParameter("store", store).list();
        return stocksEntries.stream().map(StoreStock::getBook).toList();
    }

    @Override
    public StoreStock changeBookQuantity(BookStore bookStore, Book book, Integer bookQuantity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StoreStock storeStock = session.createQuery("from StoreStock where store = :store and book = :book", StoreStock.class)
                .setParameter("store", bookStore)
                .setParameter("book", book)
                .getSingleResult();
        storeStock.setQuantity(bookQuantity);
        session.persist(storeStock);
        session.getTransaction().commit();
        return storeStock;
    }

    @Override
    public StoreStock getBookQuantityInStore(BookStore bookStore, Book book) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from StoreStock where store = :store and book = :book", StoreStock.class)
                .setParameter("store", bookStore)
                .setParameter("book", book)
                .getSingleResult();
    }

    @Override
    public StoreStock addBockToStockInStore(StoreStock storeStock) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(storeStock);
        session.getTransaction().commit();
        return storeStock;
    }

    @Override
    public void removeBockFromStockInStore(BookStore bookStore, Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StoreStock storeStock = session.createQuery("from StoreStock where store = :store and book = :book", StoreStock.class)
                .setParameter("store", bookStore)
                .setParameter("book", book)
                .getSingleResult();
        session.delete(storeStock);
        session.getTransaction().commit();
    }
}
