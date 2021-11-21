package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.model.Student;

class StudentServiceImplTest extends BaseServiceTest {

    private StudentDao studentDao;

    private StudentServiceImpl service;

    @Autowired
    public StudentServiceImplTest(StudentDao studentDao, StudentServiceImpl service) {
        this.studentDao = studentDao;
        this.service = service;
    }

    @Test
    @SneakyThrows
    void testGetStudentById() {
        int STUDENT_ID = 1;
        Student student = new Student();
        when(studentDao.getStudentById(STUDENT_ID)).thenReturn(student);
        assertEquals(student, service.getStudentById(STUDENT_ID));
    }

    @Test
    @SneakyThrows
    void testGetAllStudents() {
        Student studentOne = new Student();
        Student studentTwo = new Student();
        List<Student> listStudents = new ArrayList<>();
        studentOne.setFirstName("ONE");
        studentTwo.setFirstName("TWO");
        listStudents.add(studentOne);
        listStudents.add(studentTwo);
        when(studentDao.getAllStudents()).thenReturn(listStudents);
        assertEquals(listStudents, service.getAllStudents());
    }

}
