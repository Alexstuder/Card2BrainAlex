package ch.zhaw.card2brain.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {


    @Value("${info.app.version}")
    String appVersion;

    @Value("${info.app.buildtime}")
    String buildTime;

    @GetMapping("/healthCheck")
    public String index() {
        return "<!DOCTYPE html><html><head></head><titel></titel><body><h1>Card2Brain is running!</h1> <p>App.Version :" + appVersion + "</p> <p> BuildTime :" + buildTime + "</p></body></html>";
    }
}
