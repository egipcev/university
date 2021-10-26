package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.TeacherDao;
import ua.com.foxminded.model.Teacher;

class TeacherServiceImplTest extends BaseServiceTest {

    TeacherDao teacherDao;

    TeacherServiceImpl service;

    @Autowired
    public TeacherServiceImplTest(TeacherDao teacherDao, TeacherServiceImpl service) {
        this.teacherDao = teacherDao;
        this.service = service;
    }

    @Test
    @SneakyThrows
    void testGetTeacherById() {
        String TEACHER_ID = "1";
        Teacher teacher = new Teacher();
        teacher.setId(TEACHER_ID);
        when(teacherDao.getTeacherById(TEACHER_ID)).thenReturn(teacher);
        assertEquals(teacher, service.getTeacherById(TEACHER_ID));
    }

}
