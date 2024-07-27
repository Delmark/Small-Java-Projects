package service;

import models.BookStore;

public interface StoreService {
    BookStore addStore(BookStore bookStore);
    BookStore updateStoreData(BookStore bookStore, Long id);
    BookStore getStoreById(Long id);
    void deleteStore(Long id);
}
