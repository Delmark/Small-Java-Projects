package service;

import handler.requests.Stock;
import models.Book;
import models.BookStore;
import models.StoreStock;

import java.util.List;

public interface StockService {
    StoreStock addStock(Stock stock);
    void removeStock(Long bookId, Long storeId);
    StoreStock updateStock(Stock stock);
    StoreStock getStockQuantity(Long bookId, Long storeId);
    List<StoreStock> allBookStocks(Long bookId);
    List<StoreStock> allStoreStocks(Long storeId);
    List<BookStore> allStoresWithBook(Long bookId);
    List<Book> allBooksInStore(Long storeId);
}
