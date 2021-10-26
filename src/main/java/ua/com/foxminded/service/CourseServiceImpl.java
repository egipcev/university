package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.CourseDao;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Course;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    @Override
    public void createCourses(List<Course> listCourses) throws ServiceException {
        try {
            log.info("inserting courses into DB");
            courseDao.insertCourses(listCourses);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting courses into DB", e);
        }

    }

    @Override
    public void createCourse(Course course) throws ServiceException {
        try {
            log.info("inserting course into DB");
            courseDao.create(course);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting course into DB", e);
        }

    }

    @Override
    public void deleteCourseByName(String courseName) throws ServiceException {
        try {
            log.info("deleting course from DB");
            courseDao.deleteCourseByName(courseName);
        } catch (DaoException e) {
            throw new ServiceException("error while deleting course from DB", e);
        }

    }

    @Override
    public Course getCourseByName(String courseName) throws ServiceException {
        Course course = null;
        try {
            log.info("fetching course from DB");
            course = courseDao.getCourseByName(courseName);
        } catch (DaoException e) {
            throw new ServiceException("error while fetching course from DB", e);
        }
        return course;
    }

}
