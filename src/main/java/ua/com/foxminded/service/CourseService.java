package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Course;

public interface CourseService {

    public void createCourses(List<Course> listCourses) throws ServiceException;

    public void createCourse(Course course) throws ServiceException;

    public void deleteCourseByName(String courseName) throws ServiceException;

    public Course getCourseByName(String courseName) throws ServiceException;

}
