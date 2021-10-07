package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.model.Course;

public interface CourseService {

    public void createCourses(List<Course> listCourses);

    public void createCourse(Course course);

    public void deleteCourseByName(String courseName);

    public Course getCourseByName(String courseName);

}
