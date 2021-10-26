package controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import ua.com.foxminded.model.Teacher;

class TeacherDaoTest extends DaoBaseTest {

    static final String FIRST_NAME = "FirstName";
    static final String LAST_NAME = "LastName";
    static final String GROUP_NAME = "AA-11";

    @Test
    @SneakyThrows
    void testCreateTeacher() {
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(FIRST_NAME);
        newTeacher.setLastName(LAST_NAME);
        newTeacher.setId(UUID.randomUUID().toString());
        teacherDao.create(newTeacher);
        Teacher teacher = teacherDao.getTeacherById(newTeacher.getId());
        assertEquals(FIRST_NAME, teacher.getFirstName());
        assertEquals(LAST_NAME, teacher.getLastName());
    }

    @Test
    @SneakyThrows
    void testDeleteTeacher() {
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(FIRST_NAME);
        newTeacher.setLastName(LAST_NAME);
        newTeacher.setId(UUID.randomUUID().toString());
        teacherDao.create(newTeacher);
        Teacher teacher = teacherDao.getTeacherById(newTeacher.getId());
        assertEquals(FIRST_NAME, teacher.getFirstName());
        assertEquals(LAST_NAME, teacher.getLastName());
        teacherDao.deleteTeacherById(newTeacher.getId());
        assertNull(teacherDao.getTeacherById(newTeacher.getId()));

    }

}
