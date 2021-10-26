package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Student;

public interface StudentService {

    public void createStudents(List<Student> listStudents) throws ServiceException;

    public Student getStudentById(String studentId) throws ServiceException;

    public void createStudent(Student student) throws ServiceException;

    public void deleteStudentById(String studentId) throws ServiceException;

    public void updateStudentGroup(String studentNumber, String groupName) throws ServiceException;

    public List<Student> getAllStudents() throws ServiceException;

}
