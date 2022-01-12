package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.CourseEntity;

public interface CourseService {

    public void createCourses(List<CourseEntity> listCourses) throws ServiceException;

    public void createCourse(CourseEntity course) throws ServiceException;

    List<CourseEntity> getAllCourses() throws ServiceException;

    void deleteCourseById(int id) throws ServiceException;

    CourseEntity getCourseById(int id) throws ServiceException;

}
