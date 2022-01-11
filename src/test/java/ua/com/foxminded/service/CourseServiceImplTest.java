package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.CourseDao;
import ua.com.foxminded.model.entity.CourseEntity;

class CourseServiceImplTest extends BaseServiceTest {

    private final CourseServiceImpl service;
    private final CourseDao courseDao;
    private final CourseEntity courseEntity;

    @Autowired
    public CourseServiceImplTest(CourseServiceImpl service, CourseDao courseDao, CourseEntity courseEntity) {
        this.service = service;
        this.courseDao = courseDao;
        this.courseEntity = courseEntity;
    }

    @Test
    @SneakyThrows
    void testGetCourseByName() {
        courseEntity.setId(1);
        when(courseDao.getById(1)).thenReturn(courseEntity);
        assertEquals(courseEntity, service.getCourseById(1));
    }

}
