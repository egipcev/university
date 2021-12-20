package controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.TeacherDao;
import ua.com.foxminded.model.Teacher;

class TeacherDaoTest extends DaoBaseTest {

    static final String FIRST_NAME = "FirstName";
    static final String LAST_NAME = "LastName";
    static final String GROUP_NAME = "AA-11";

    @Autowired
    private TeacherDao teacherDao;

    @Test
    @SneakyThrows
    void testCreateTeacher() {
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(FIRST_NAME);
        newTeacher.setLastName(LAST_NAME);
        teacherDao.create(newTeacher);
        Teacher teacher = teacherDao.getTeacherById(Integer.parseInt(newTeacher.getId()));
        assertEquals(FIRST_NAME, teacher.getFirstName());
        assertEquals(LAST_NAME, teacher.getLastName());
    }

    @Test
    @SneakyThrows
    void testDeleteTeacher() {
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(FIRST_NAME);
        newTeacher.setLastName(LAST_NAME);
        teacherDao.create(newTeacher);
        Teacher teacher = teacherDao.getTeacherById(Integer.parseInt(newTeacher.getId()));
        assertEquals(FIRST_NAME, teacher.getFirstName());
        assertEquals(LAST_NAME, teacher.getLastName());
        teacherDao.deleteTeacherById(Integer.parseInt(newTeacher.getId()));
        assertNull(teacherDao.getTeacherById(Integer.parseInt(newTeacher.getId())));

    }

}
