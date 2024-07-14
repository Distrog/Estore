package ru.stroganov;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Estore Management",
                description = "Приложение для управления магазином", version = "1.0.0",
                contact = @Contact(
                        name = "Stroganov Dmitriy",
                        email = "distrog@gmail.com"
                )
        )
)
public class EstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(EstoreApplication.class,args);
    }
}
