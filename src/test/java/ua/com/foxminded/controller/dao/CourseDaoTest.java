package ua.com.foxminded.controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.model.entity.CourseEntity;

class CourseDaoTest extends DaoBaseTest {

    public static final String COURSE_NAME = "Test course";
    public static final String COURSE_DESC = "Test course description";

    @Autowired
    private CourseDao courseDao;

    @Test
    @SneakyThrows
    void testCreateCourse() {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(COURSE_NAME);
        courseEntity.setCourseDescription(COURSE_DESC);
        courseDao.save(courseEntity);
        assertEquals(COURSE_DESC, courseDao.getById(1).getCourseDescription());
    }

    @Test
    @SneakyThrows
    void testDeleteCourse() {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(COURSE_NAME);
        courseEntity.setCourseDescription(COURSE_DESC);
        courseDao.save(courseEntity);
        assertEquals(COURSE_DESC, courseDao.getById(1).getCourseDescription());
        courseDao.remove(1);
        assertNull(courseDao.getById(1));
    }

}
