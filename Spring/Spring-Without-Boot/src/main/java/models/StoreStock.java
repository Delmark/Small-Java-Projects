package models;

import jakarta.persistence.*;

@Entity
public class StoreStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private BookStore store;
    private Integer quantity;

    public StoreStock() {
    }

    public StoreStock(Book book, BookStore store, Integer quantity) {
        this.book = book;
        this.store = store;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookStore getStore() {
        return store;
    }

    public void setStore(BookStore store) {
        this.store = store;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BooksInStock{" +
                "id=" + id +
                ", book=" + book +
                ", store=" + store +
                ", quantity=" + quantity +
                '}';
    }
}
