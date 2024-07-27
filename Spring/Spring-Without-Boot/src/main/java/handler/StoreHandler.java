package handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import handler.response.ErrorResponse;
import models.BookStore;
import org.springframework.stereotype.Component;
import service.StoreService;
import utils.HttpUtils;

import java.io.IOException;
import java.util.Map;

@Component
public class StoreHandler implements HttpHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    private final StoreService service;

    public StoreHandler(StoreService service, HttpServer server) {
        this.service = service;
        server.createContext("/store", this);
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            if (exchange.getRequestMethod().contains("GET")) {
                Map<String, String> queries = HttpUtils.parseQuery(exchange.getRequestURI().getQuery());
                BookStore store = service.getStoreById(Long.parseLong(queries.get("id")));
                HttpUtils.sendResponse(exchange, 200, store);
            } else if (exchange.getRequestMethod().contains("POST")) {
                service.addStore(mapper.readValue(exchange.getRequestBody(), BookStore.class));
                exchange.sendResponseHeaders(200, 0);
                exchange.close();
            }
             else if (exchange.getRequestMethod().contains("PUT")) {
                Map<String, String> queries = HttpUtils.parseQuery(exchange.getRequestURI().getQuery());
                BookStore store = mapper.readValue(exchange.getRequestBody(), BookStore.class);
                store = service.updateStoreData(store, Long.parseLong(queries.get("id")));
                HttpUtils.sendResponse(exchange, 200, store);
            } else if (exchange.getRequestMethod().contains("DELETE")) {
                Map<String, String> queries = HttpUtils.parseQuery(exchange.getRequestURI().getQuery());
                service.deleteStore(Long.parseLong(queries.get("id")));
                exchange.sendResponseHeaders(200, 0);
                exchange.close();
            }
        }
        catch (IllegalArgumentException e) {
            HttpUtils.sendResponse(exchange, 400, new ErrorResponse(400,"Invalid request"));
        }
         catch (Exception e) {
            HttpUtils.sendResponse(exchange, 500, new ErrorResponse(500,"Internal server error"));
        }
    }
}
