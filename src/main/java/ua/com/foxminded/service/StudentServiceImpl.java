package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Student;

@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Override
    public void createStudents(List<Student> listStudents) throws ServiceException {
        try {
            log.info("inserting students into DB");
            studentDao.insertStudents(listStudents);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting students into DB", e);
        }

    }

    @Override
    public Student getStudentById(String studentId) throws ServiceException {
        Student student = null;
        try {
            log.info("fetching student from DB");
            student = studentDao.getStudentById(studentId);
        } catch (DaoException e) {
            throw new ServiceException("error while fetching student from DB", e);
        }
        return student;
    }

    @Override
    public void createStudent(Student student) throws ServiceException {
        try {
            log.info("inserting student into DB");
            studentDao.create(student);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting student into DB", e);
        }

    }

    @Override
    public void deleteStudentById(String studentId) throws ServiceException {
        try {
            log.info("deleting student from DB");
            studentDao.deleteStudentById(studentId);
        } catch (DaoException e) {
            throw new ServiceException("error while deleting student from DB", e);
        }

    }

    @Override
    public void updateStudentGroup(String studentNumber, String groupName) throws ServiceException {
        try {
            log.info("updating student in DB");
            studentDao.updateStudentGroup(studentNumber, groupName);
        } catch (DaoException e) {
            throw new ServiceException("error while updating student in DB", e);
        }

    }

    @Override
    public List<Student> getAllStudents() throws ServiceException {
        List<Student> listStudents = null;
        try {
            log.info("fetching students from DB");
            listStudents = studentDao.getAllStudents();
        } catch (DaoException e) {
            throw new ServiceException("error while fetching students from DB", e);
        }
        return listStudents;
    }

}
