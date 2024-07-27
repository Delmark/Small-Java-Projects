package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendResponse(HttpExchange exchange, int status, Object body) {
        try {
            exchange.sendResponseHeaders(status, 0);
            Headers headers = exchange.getResponseHeaders();
            headers.set("Content-Type", "application/json");
            exchange.getResponseBody().write(mapper.writeValueAsString(body) .getBytes());
            exchange.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx > 0) {
                result.put(pair.substring(0, idx), pair.substring(idx + 1));
            }
        }
        return result;
    }

}
