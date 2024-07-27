package config;

import com.sun.net.httpserver.HttpServer;
import handler.BookHandler;
import handler.StoreHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
public class WebConfiguration {
    @Bean
    public HttpServer httpServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.setExecutor(null);
        server.start();
        return server;
    }
}
