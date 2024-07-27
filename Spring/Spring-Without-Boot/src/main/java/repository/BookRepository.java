package repository;

import models.Book;

import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}
