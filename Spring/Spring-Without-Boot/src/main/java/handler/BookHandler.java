package handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import handler.response.ErrorResponse;
import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.BookRepository;
import service.BookService;
import utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class BookHandler implements HttpHandler {
    private final ObjectMapper mapper = new ObjectMapper();
    private final BookService bookService;

    @Autowired
    public BookHandler(BookService bookService, HttpServer server) {
        this.bookService = bookService;
        server.createContext("/book", this);
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            if (exchange.getRequestMethod().contains("GET")) {
                Map<String, String> queries = HttpUtils.parseQuery(exchange.getRequestURI().getQuery());
                Book book = bookService.getBookById(Long.parseLong(queries.get("id")));
                HttpUtils.sendResponse(exchange, 200, book);
            } else if (exchange.getRequestMethod().contains("POST")) {
                Book book = mapper.readValue(exchange.getRequestBody(), Book.class);
                bookService.addNewBook(book);
                HttpUtils.sendResponse(exchange, 200, book);
            } else if (exchange.getRequestMethod().contains("PUT")) {
                Map<String, String> queries = HttpUtils.parseQuery(exchange.getRequestURI().getQuery());
                Book book = mapper.readValue(exchange.getRequestBody(), Book.class);
                bookService.editBook(book, Long.parseLong(queries.get("id")));
                HttpUtils.sendResponse(exchange, 200, book);
            } else if (exchange.getRequestMethod().contains("DELETE")) {
                Map<String, String> queries = HttpUtils.parseQuery(exchange.getRequestURI().getQuery());
                bookService.deleteBookById(Long.parseLong(queries.get("id")));
                exchange.sendResponseHeaders(200, 0);
                exchange.close();
            }
        }
        catch (IllegalArgumentException e) {
            HttpUtils.sendResponse(exchange, 400, new ErrorResponse(400, e.getMessage()));
        }
        catch (Exception e) {
            HttpUtils.sendResponse(exchange, 500, new ErrorResponse(500, "Internal server error"));
        }
    }
}
