package service.impl;

import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;
import service.BookService;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book addNewBook(Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }

    @Override
    public Book editBook(Book book, Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book existingBook = bookOptional.get();
            existingBook.setTitle((book.getTitle() != null) ? book.getTitle() : existingBook.getTitle());
            existingBook.setIsbn((book.getTitle() != null) ? book.getIsbn() : existingBook.getIsbn());
            existingBook.setPages((book.getPages() != null) ? book.getPages() : existingBook.getPages());
            return bookRepository.save(existingBook);
        }
        else {
            throw new IllegalArgumentException("Book not found");
        }
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
