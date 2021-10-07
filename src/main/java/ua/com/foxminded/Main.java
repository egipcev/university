package ua.com.foxminded;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.config.AppConfig;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

}
