package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ua.com.foxminded.controller.dao.TeacherDao;
import ua.com.foxminded.model.Teacher;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;

    public void createTeachers(final List<Teacher> listTeachers) {
        teacherDao.insertTeachers(listTeachers);
    }

    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    public Teacher getTeacherById(String id) {
        return teacherDao.getTeacherById(id);
    }

    public void deleteTeacherById(String id) {
        teacherDao.deleteTeacherById(id);
    }

    public void createTeacher(Teacher teacher) {
        teacherDao.create(teacher);
    }

}
