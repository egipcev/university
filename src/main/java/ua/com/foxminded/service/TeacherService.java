package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.TeacherEntity;

public interface TeacherService {

    public void createTeachers(final List<TeacherEntity> listTeachers) throws ServiceException;

    public TeacherEntity getTeacherById(int id) throws ServiceException;

    public void deleteTeacherById(int id) throws ServiceException;

    public void createTeacher(TeacherEntity teacher) throws ServiceException;

    public List<TeacherEntity> getAllTeachers() throws ServiceException;

}
