package ua.com.foxminded.controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.model.entity.GroupEntity;
import ua.com.foxminded.model.entity.StudentEntity;

class StudentDaoTest extends DaoBaseTest {

    static final String FIRST_NAME = "FirstName";
    static final String LAST_NAME = "LastName";
    static final String GROUP_NAME = "AA-11";

    @Autowired
    private StudentDao studentDao;

    @Test
    @SneakyThrows
    void testCreateStudent() {
        StudentEntity newStudent = new StudentEntity();
        newStudent.setFirstName(FIRST_NAME);
        newStudent.setLastName(LAST_NAME);
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(GROUP_NAME);
        newStudent.setGroup(groupEntity);
        studentDao.save(newStudent);
        StudentEntity student = studentDao.getById(newStudent.getId());
        assertEquals(FIRST_NAME, student.getFirstName());
        assertEquals(LAST_NAME, student.getLastName());
        assertEquals(GROUP_NAME, student.getGroup().getGroupName());
    }

    @Test
    @SneakyThrows
    void testDeleteStudent() {
        StudentEntity newStudent = new StudentEntity();
        newStudent.setFirstName(FIRST_NAME);
        newStudent.setLastName(LAST_NAME);
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(GROUP_NAME);
        newStudent.setGroup(groupEntity);
        studentDao.save(newStudent);
        StudentEntity student = studentDao.getById(newStudent.getId());
        assertEquals(FIRST_NAME, student.getFirstName());
        assertEquals(LAST_NAME, student.getLastName());
        assertEquals(GROUP_NAME, student.getGroup().getGroupName());
        studentDao.remove(newStudent.getId());
        assertNull(studentDao.getById(newStudent.getId()));

    }

    @Test
    @SneakyThrows
    void testUpdateStudent() {
        StudentEntity newStudent = new StudentEntity();
        newStudent.setFirstName(FIRST_NAME);
        newStudent.setLastName(LAST_NAME);
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(GROUP_NAME);
        newStudent.setGroup(groupEntity);
        studentDao.save(newStudent);
        groupEntity.setGroupName("BB-22");
        newStudent.setGroup(groupEntity);
        studentDao.update(newStudent);
        assertEquals("BB-22", studentDao.getById(newStudent.getId()).getGroup().getGroupName());

    }

}
