package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.Dao;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.StudentEntity;

@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private Dao<StudentEntity> studentDao;

    @Override
    public void createStudents(List<StudentEntity> listStudents) throws ServiceException {
        log.info("inserting students into DB");
        studentDao.saveAll(listStudents);

    }

    @Override
    public StudentEntity getStudentById(int studentId) throws ServiceException {
        log.info("fetching student from DB");
        return studentDao.getById(studentId);
    }

    @Override
    public void createStudent(StudentEntity student) throws ServiceException {
        log.info("inserting student into DB");
        studentDao.save(student);

    }

    @Override
    public void deleteStudentById(int studentId) throws ServiceException {
        log.info("deleting student from DB");
        studentDao.remove(studentId);

    }

    @Override
    public List<StudentEntity> getAllStudents() throws ServiceException {
        List<StudentEntity> listStudents = null;
        log.info("fetching students from DB");
        listStudents = studentDao.getAll();
        return listStudents;
    }

}
