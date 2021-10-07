package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.model.Teacher;

public interface TeacherService {

    public void createTeachers(final List<Teacher> listTeachers);

    public Teacher getTeacherById(String id);

    public void deleteTeacherById(String id);

    public void createTeacher(Teacher teacher);

}
