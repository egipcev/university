package controller.dao;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ua.com.foxminded.config.AppConfig;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.service.DataGenerator;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class DaoBaseTest {

    @Autowired
    protected DataGenerator dataGenerator;

    @BeforeEach
    void prepareTestData() {
        try {
            dataGenerator.createTables();
            dataGenerator.createTestData();
        } catch (ServiceException e) {
            fail(e);
        }

    }

}
