package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.model.entity.StudentEntity;

class StudentServiceImplTest extends BaseServiceTest {

    private StudentDao studentDao;
    private StudentServiceImpl service;
    private StudentEntity studentEntityOne;
    private StudentEntity studentEntityTwo;

    @Autowired
    public StudentServiceImplTest(StudentDao studentDao, StudentServiceImpl service, StudentEntity studentEntity,
            StudentEntity studentEntityTwo) {
        this.studentDao = studentDao;
        this.service = service;
        this.studentEntityOne = studentEntity;
        this.studentEntityTwo = studentEntityTwo;
    }

    @Test
    @SneakyThrows
    void testGetStudentById() {
        int STUDENT_ID = 1;
        studentEntityOne.setId(STUDENT_ID);
        when(studentDao.getById(STUDENT_ID)).thenReturn(studentEntityOne);
        assertEquals(studentEntityOne, service.getStudentById(STUDENT_ID));
    }

    @Test
    @SneakyThrows
    void testGetAllStudents() {
        List<StudentEntity> listStudents = new ArrayList<>();
        studentEntityOne.setFirstName("ONE");
        studentEntityTwo.setFirstName("TWO");
        listStudents.add(studentEntityOne);
        listStudents.add(studentEntityTwo);
        when(studentDao.getAll()).thenReturn(listStudents);
        assertEquals(listStudents, service.getAllStudents());
    }

}
