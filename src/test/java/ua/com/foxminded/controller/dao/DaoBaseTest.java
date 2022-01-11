package ua.com.foxminded.controller.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.config.IntegrationTestConfig;
import ua.com.foxminded.service.DataGenerator;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class DaoBaseTest {

    @Autowired
    protected DataGenerator dataGenerator;

    @BeforeEach
    public void setup() throws Exception {

        dataGenerator.createTables();
        dataGenerator.createTestData();
    }

}
