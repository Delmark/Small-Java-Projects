package handler.requests;

public class Stock {
    private Long storeId;
    private Long bookId;
    private Integer quantity;

    public Stock(Long storeId, Long bookId, Integer quantity) {
        this.storeId = storeId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
