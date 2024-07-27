package service.impl;

import handler.requests.Stock;
import models.Book;
import models.BookStore;
import models.StoreStock;
import org.springframework.stereotype.Service;
import repository.StocksRepository;
import service.BookService;
import service.StockService;
import service.StoreService;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StocksRepository stocksRepository;
    private final StoreService bookStoreService;
    private final BookService bookService;

    public StockServiceImpl(StocksRepository stocksRepository, StoreService bookStoreService, BookService bookService) {
        this.stocksRepository = stocksRepository;
        this.bookStoreService = bookStoreService;
        this.bookService = bookService;
    }

    @Override
    public StoreStock addStock(Stock storeStock) {
        BookStore bookStore = bookStoreService.getStoreById(storeStock.getStoreId());
        Book book = bookService.getBookById(storeStock.getBookId());
        StoreStock stock = new StoreStock(book, bookStore, storeStock.getQuantity());
        return stocksRepository.addBockToStockInStore(stock);
    }

    @Override
    public void removeStock(Long bookId, Long storeId) {
        BookStore bookStore = bookStoreService.getStoreById(storeId);
        Book book = bookService.getBookById(bookId);
        stocksRepository.removeBockFromStockInStore(bookStore, book);
    }

    @Override
    public StoreStock updateStock(Stock storeStock) {
        BookStore bookStore = bookStoreService.getStoreById(storeStock.getStoreId());
        Book book = bookService.getBookById(storeStock.getBookId());
        return stocksRepository.changeBookQuantity(bookStore, book, storeStock.getQuantity());
    }

    @Override
    public StoreStock getStockQuantity(Long bookId, Long storeId) {
        BookStore bookStore = bookStoreService.getStoreById(storeId);
        Book book = bookService.getBookById(bookId);
        return stocksRepository.getBookQuantityInStore(bookStore, book);
    }

    @Override
    public List<StoreStock> allBookStocks(Long bookId) {
        return stocksRepository.stockQuantityByBook(bookService.getBookById(bookId));
    }

    @Override
    public List<StoreStock> allStoreStocks(Long storeId) {
        return stocksRepository.allStocksByStore(bookStoreService.getStoreById(storeId));
    }

    @Override
    public List<BookStore> allStoresWithBook(Long bookId) {
        return stocksRepository.allStocksByBook(bookService.getBookById(bookId));
    }

    @Override
    public List<Book> allBooksInStore(Long storeId) {
        return stocksRepository.allBooksInStore(bookStoreService.getStoreById(storeId));
    }
}
