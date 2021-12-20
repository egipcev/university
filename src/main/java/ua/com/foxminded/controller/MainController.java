package ua.com.foxminded.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.service.DataGenerator;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private DataGenerator dataGenerator;

    @GetMapping("/")
    public String index() {

        try {
            dataGenerator.createTables();
            dataGenerator.createTestData();
        } catch (ServiceException e) {
            log.error("error creating test data", e);
        }
        return "welcome";
    }

}
