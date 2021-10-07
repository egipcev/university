package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.model.Student;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Override
    public void createStudents(List<Student> listStudents) {
        studentDao.insertStudents(listStudents);

    }

    @Override
    public Student getStudentById(String studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public void createStudent(Student student) {
        studentDao.create(student);

    }

    @Override
    public void deleteStudentById(String studentId) {
        studentDao.deleteStudentById(studentId);

    }

    @Override
    public void updateStudentGroup(String studentNumber, String groupName) {
        studentDao.updateStudentGroup(studentNumber, groupName);

    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

}
