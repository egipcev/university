package controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import ua.com.foxminded.model.Course;

class CourseDaoTest extends DaoBaseTest {

    public static final String COURSE_NAME = "Test course";
    public static final String COURSE_DESC = "Test course description";

    @Test
    @SneakyThrows
    void testCreateCourse() {

        courseDao.create(new Course(COURSE_NAME, COURSE_DESC));
        assertEquals(COURSE_DESC, courseDao.getCourseByName(COURSE_NAME).getCourseDescription());
    }

    @Test
    @SneakyThrows
    void testDeleteCourse() {
        courseDao.create(new Course(COURSE_NAME, COURSE_DESC));
        assertEquals(COURSE_DESC, courseDao.getCourseByName(COURSE_NAME).getCourseDescription());
        courseDao.deleteCourseByName(COURSE_NAME);
        assertNull(courseDao.getCourseByName(COURSE_NAME));
    }

}
