package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import config.TestConfig;
import ua.com.foxminded.controller.dao.CourseDao;
import ua.com.foxminded.model.Course;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
class CourseServiceImplTest {

    public static final String COURSE_NAME = "Test course";
    public static final String COURSE_DESC = "Test course description";

    private final CourseServiceImpl service;
    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImplTest(CourseServiceImpl service, CourseDao courseDao) {
        this.service = service;
        this.courseDao = courseDao;
    }

    @Test
    void testGetCourseByName() {
        Course course = new Course(COURSE_NAME, COURSE_DESC);
        when(courseDao.getCourseByName(COURSE_NAME)).thenReturn(course);
        assertEquals(course, service.getCourseByName(COURSE_NAME));
    }

}
