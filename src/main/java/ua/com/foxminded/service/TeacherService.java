package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Teacher;

public interface TeacherService {

    public void createTeachers(final List<Teacher> listTeachers) throws ServiceException;

    public Teacher getTeacherById(String id) throws ServiceException;

    public void deleteTeacherById(String id) throws ServiceException;

    public void createTeacher(Teacher teacher) throws ServiceException;

}
