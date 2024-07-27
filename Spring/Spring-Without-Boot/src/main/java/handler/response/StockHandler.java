package handler.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import handler.requests.Stock;
import models.StoreStock;
import org.springframework.stereotype.Component;
import service.StockService;
import utils.HttpUtils;

import java.io.IOException;
import java.util.Map;

@Component
public class StockHandler implements HttpHandler {

    private final StockService stockService;
    private final ObjectMapper mapper = new ObjectMapper();

    public StockHandler(StockService stockService, HttpServer server) {
        this.stockService = stockService;
        server.createContext("/stock", this);

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().contains("GET")) {
                Map<String, String> queries = HttpUtils.parseQuery(exchange.getRequestURI().getQuery());
                Long bookId = Long.parseLong(queries.get("bookId"));
                Long storeId = Long.parseLong(queries.get("bookId"));
                StoreStock stock = stockService.getStockQuantity(bookId, storeId);
                HttpUtils.sendResponse(exchange, 200, stock);
            } else if (exchange.getRequestMethod().contains("POST")) {
                StoreStock stock = stockService.addStock(mapper.readValue(exchange.getRequestBody(), Stock.class));
                HttpUtils.sendResponse(exchange, 200, stock);
            } else if (exchange.getRequestMethod().contains("PUT")) {
                Stock stock = mapper.readValue(exchange.getRequestBody(), Stock.class);
                StoreStock updatedStock = stockService.updateStock(stock);
                HttpUtils.sendResponse(exchange, 200, updatedStock);
            }
             else if (exchange.getRequestMethod().contains("DELETE")) {
                Map<String, String> queries = HttpUtils.parseQuery(exchange.getRequestURI().getQuery());
                stockService.removeStock(Long.parseLong(queries.get("bookId")), Long.parseLong(queries.get("storeId")));
                exchange.sendResponseHeaders(200, 0);
                exchange.close();
            }
        }
        catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
            HttpUtils.sendResponse(exchange, 400, errorResponse);
        }
         catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
            HttpUtils.sendResponse(exchange, 500, errorResponse);
        }
    }
}
