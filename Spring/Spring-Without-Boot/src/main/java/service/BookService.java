package service;

import models.Book;

public interface BookService {
    Book addNewBook(Book book);
    Book editBook(Book book, Long id);
    Book getBookById(Long id);
    void deleteBookById(Long id);
}
