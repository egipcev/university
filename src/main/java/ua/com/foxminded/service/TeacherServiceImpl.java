package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.TeacherDao;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Teacher;

@Service
@AllArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;

    public void createTeachers(final List<Teacher> listTeachers) throws ServiceException {
        try {
            log.info("inserting teachers into DB");
            teacherDao.insertTeachers(listTeachers);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting teachers into DB", e);
        }
    }

    public List<Teacher> getAllTeachers() throws ServiceException {
        List<Teacher> listTeachers = null;
        try {
            log.info("fetching teachers from DB");
            listTeachers = teacherDao.getAllTeachers();
        } catch (DaoException e) {
            throw new ServiceException("error while fetching teachers from DB", e);
        }
        return listTeachers;
    }

    public Teacher getTeacherById(String id) throws ServiceException {
        Teacher teacher = null;
        try {
            log.info("fetching teacher into DB");
            teacher = teacherDao.getTeacherById(id);
        } catch (DaoException e) {
            throw new ServiceException("error while fetching teacher into DB", e);
        }
        return teacher;
    }

    public void deleteTeacherById(String id) throws ServiceException {
        try {
            log.info("deleting teacher in DB");
            teacherDao.deleteTeacherById(id);
        } catch (DaoException e) {
            throw new ServiceException("error while deleting teacher in DB", e);
        }
    }

    public void createTeacher(Teacher teacher) throws ServiceException {
        try {
            log.info("inserting teacher into DB");
            teacherDao.create(teacher);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting teacher into DB", e);
        }
    }

}
