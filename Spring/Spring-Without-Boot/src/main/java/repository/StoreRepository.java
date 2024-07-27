package repository;

import models.BookStore;

import java.util.Optional;

public interface StoreRepository {
    Optional<BookStore> findById(Long id);
    BookStore save(BookStore bookStore);
    void deleteById(Long id);
}
