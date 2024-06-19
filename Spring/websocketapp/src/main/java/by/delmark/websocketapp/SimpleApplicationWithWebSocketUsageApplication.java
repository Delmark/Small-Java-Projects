package by.delmark.websocketapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SimpleApplicationWithWebSocketUsageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplicationWithWebSocketUsageApplication.class, args);
	}

}
