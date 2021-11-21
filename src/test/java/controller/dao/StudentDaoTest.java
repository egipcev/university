package controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;

class StudentDaoTest extends DaoBaseTest {

    static final String FIRST_NAME = "FirstName";
    static final String LAST_NAME = "LastName";
    static final String GROUP_NAME = "AA-11";

    @Autowired
    private StudentDao studentDao;

    @Test
    @SneakyThrows
    void testCreateStudent() {
        Student newStudent = new Student();
        newStudent.setFirstName(FIRST_NAME);
        newStudent.setLastName(LAST_NAME);
        newStudent.setGroup(new Group(GROUP_NAME));
        studentDao.create(newStudent);
        Student student = studentDao.getStudentById(Integer.parseInt(newStudent.getId()));
        assertEquals(FIRST_NAME, student.getFirstName());
        assertEquals(LAST_NAME, student.getLastName());
        assertEquals(GROUP_NAME, student.getGroup().getGroupName());
    }

    @Test
    @SneakyThrows
    void testDeleteStudent() {
        Student newStudent = new Student();
        newStudent.setFirstName(FIRST_NAME);
        newStudent.setLastName(LAST_NAME);
        newStudent.setGroup(new Group(GROUP_NAME));
        studentDao.create(newStudent);
        Student student = studentDao.getStudentById(Integer.parseInt(newStudent.getId()));
        assertEquals(FIRST_NAME, student.getFirstName());
        assertEquals(LAST_NAME, student.getLastName());
        assertEquals(GROUP_NAME, student.getGroup().getGroupName());
        studentDao.deleteStudentById(Integer.parseInt(newStudent.getId()));
        assertNull(studentDao.getStudentById(Integer.parseInt(newStudent.getId())));

    }

    @Test
    @SneakyThrows
    void testUpdateStudent() {
        Student newStudent = new Student();
        newStudent.setFirstName(FIRST_NAME);
        newStudent.setLastName(LAST_NAME);
        newStudent.setGroup(new Group(GROUP_NAME));
        studentDao.create(newStudent);
        studentDao.updateStudentGroup(Integer.parseInt(newStudent.getId()), "BB-22");
        assertEquals("BB-22",
                studentDao.getStudentById(Integer.parseInt(newStudent.getId())).getGroup().getGroupName());

    }

}
