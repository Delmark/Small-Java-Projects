import config.HibernateConfiguration;
import config.WebConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan({"config", "repository", "handler", "service"})
public class ApplicationWithoutSpringBoot {

    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationWithoutSpringBoot.class);

    public static void main(String[] args) {
        System.out.println("Application started");
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
