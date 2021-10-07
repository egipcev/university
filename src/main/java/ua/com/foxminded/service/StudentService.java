package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.model.Student;

public interface StudentService {

    public void createStudents(List<Student> listStudents);

    public Student getStudentById(String studentId);

    public void createStudent(Student student);

    public void deleteStudentById(String studentId);

    public void updateStudentGroup(String studentNumber, String groupName);

    public List<Student> getAllStudents();

}
