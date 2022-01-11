package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.StudentEntity;

public interface StudentService {

    public void createStudents(List<StudentEntity> listStudents) throws ServiceException;

    public StudentEntity getStudentById(int studentId) throws ServiceException;

    public void createStudent(StudentEntity student) throws ServiceException;

    public void deleteStudentById(int studentId) throws ServiceException;

    public List<StudentEntity> getAllStudents() throws ServiceException;

}
