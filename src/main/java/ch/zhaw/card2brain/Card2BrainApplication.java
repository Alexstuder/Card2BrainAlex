package ch.zhaw.card2brain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Card2BrainApplication {

    @Value("${info.app.version}")
    String appVersion;

    @Value("${info.app.buildtime}")
    String buildTime;

    public static void main(String[] args) {
        SpringApplication.run(Card2BrainApplication.class, args);
    }

}
