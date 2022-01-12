package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.Dao;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.CourseEntity;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private Dao<CourseEntity> courseDao;

    @Override
    public void createCourses(List<CourseEntity> listCourses) throws ServiceException {
        log.info("inserting courses into DB");
        courseDao.saveAll(listCourses);

    }

    @Override
    public void createCourse(CourseEntity course) throws ServiceException {
        log.info("inserting course into DB");
        courseDao.save(course);

    }

    @Override
    public List<CourseEntity> getAllCourses() throws ServiceException {

        log.info("fetching Courses from DB");
        return courseDao.getAll();
    }

    @Override
    public void deleteCourseById(int id) throws ServiceException {
        log.info("deleting course from DB");
        courseDao.remove(id);

    }

    @Override
    public CourseEntity getCourseById(int id) throws ServiceException {
        log.info("fetching course from DB");
        return courseDao.getById(id);
    }

}
