package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ua.com.foxminded.controller.dao.CourseDao;
import ua.com.foxminded.model.Course;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    @Override
    public void createCourses(List<Course> listCourses) {
        courseDao.insertCourses(listCourses);

    }

    @Override
    public void createCourse(Course course) {
        courseDao.create(course);

    }

    @Override
    public void deleteCourseByName(String courseName) {
        courseDao.deleteCourseByName(courseName);

    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseDao.getCourseByName(courseName);
    }

}
