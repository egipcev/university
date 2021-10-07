package config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ua.com.foxminded.controller.dao.CourseDao;
import ua.com.foxminded.controller.dao.GroupDao;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.controller.dao.TeacherDao;
import ua.com.foxminded.controller.dao.TimeTableItemDao;

@Configuration
@ComponentScan(basePackages = "ua.com.foxminded")
public class TestConfig {

    @Bean
    public CourseDao courseDao() {
        return Mockito.mock(CourseDao.class);
    }

    @Bean
    public StudentDao studentDao() {
        return Mockito.mock(StudentDao.class);
    }

    @Bean
    public TeacherDao teacherDao() {
        return Mockito.mock(TeacherDao.class);
    }

    @Bean
    public TimeTableItemDao timeTableItemDao() {
        return Mockito.mock(TimeTableItemDao.class);
    }

    @Bean
    public GroupDao groupDao() {
        return Mockito.mock(GroupDao.class);
    }
}
