package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Teacher;

public interface TeacherService {

    public void createTeachers(final List<Teacher> listTeachers) throws ServiceException;

    public Teacher getTeacherById(int id) throws ServiceException;

    public void deleteTeacherById(int id) throws ServiceException;

    public void createTeacher(Teacher teacher) throws ServiceException;

    public List<Teacher> getAllTeachers() throws ServiceException;

}
