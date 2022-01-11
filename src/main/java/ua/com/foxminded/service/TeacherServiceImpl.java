package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.Dao;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.TeacherEntity;

@Service
@AllArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    private Dao<TeacherEntity> teacherDao;

    public void createTeachers(final List<TeacherEntity> listTeachers) throws ServiceException {
        log.info("inserting teachers into DB");
        teacherDao.saveAll(listTeachers);
    }

    public List<TeacherEntity> getAllTeachers() throws ServiceException {

        log.info("fetching teachers from DB");
        return teacherDao.getAll();
    }

    public TeacherEntity getTeacherById(int id) throws ServiceException {
        TeacherEntity teacher = null;
        log.info("fetching teacher into DB");
        teacher = teacherDao.getById(id);
        return teacher;
    }

    public void deleteTeacherById(int id) throws ServiceException {
        log.info("deleting teacher in DB");
        teacherDao.remove(id);
    }

    public void createTeacher(TeacherEntity teacher) throws ServiceException {
        log.info("inserting teacher into DB");
        teacherDao.save(teacher);
    }

}
