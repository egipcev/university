package ua.com.foxminded.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ua.com.foxminded.controller.dao.CourseDao;
import ua.com.foxminded.controller.dao.Dao;
import ua.com.foxminded.controller.dao.GroupDao;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.controller.dao.TeacherDao;
import ua.com.foxminded.controller.dao.TimeTableItemDao;
import ua.com.foxminded.model.entity.CourseEntity;
import ua.com.foxminded.model.entity.GroupEntity;
import ua.com.foxminded.model.entity.StudentEntity;

@Configuration
@ComponentScan(basePackages = "ua.com.foxminded.service")
public class TestConfig {

    @Bean
    public Dao<CourseEntity> courseDao() {
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

    @Bean
    public CourseEntity courseEntity() {
        return Mockito.mock(CourseEntity.class);
    }

    @Bean
    public GroupEntity groupEntity() {
        return Mockito.mock(GroupEntity.class);
    }

    @Bean
    public StudentEntity studentEntity() {
        return Mockito.mock(StudentEntity.class);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }

    @Bean
    public DataSource dataSource() {
        return Mockito.mock(DataSource.class);
    }

}
