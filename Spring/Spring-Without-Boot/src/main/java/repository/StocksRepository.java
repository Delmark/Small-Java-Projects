package repository;

import models.Book;
import models.BookStore;
import models.StoreStock;

import java.util.List;

public interface StocksRepository {
    List<StoreStock> stockQuantityByBook(Book book);
    List<StoreStock> allStocksByStore(BookStore bookStore);
    List<BookStore> allStocksByBook(Book book);
    List<Book> allBooksInStore(BookStore bookStore);
    StoreStock changeBookQuantity(BookStore bookStore, Book book, Integer bookQuantity);
    StoreStock getBookQuantityInStore(BookStore bookStore, Book book);
    StoreStock addBockToStockInStore(StoreStock storeStock);
    void removeBockFromStockInStore(BookStore bookStore, Book book);
}
