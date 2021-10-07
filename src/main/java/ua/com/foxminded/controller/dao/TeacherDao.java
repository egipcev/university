package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.Teacher;

@Component
public class TeacherDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int[] insertTeachers(final List<Teacher> listTeachers) {

        return jdbcTemplate.batchUpdate("INSERT INTO teachers (first_name, last_name, teacher_number) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, listTeachers.get(i).getFirstName());
                        ps.setString(2, listTeachers.get(i).getLastName());
                        ps.setString(3, listTeachers.get(i).getId());
                    }

                    public int getBatchSize() {
                        return listTeachers.size();
                    }

                });

    }

    public List<Teacher> getAllTeachers() {

        return jdbcTemplate.query("select first_name, last_name, teacher_number from teachers", (rs, rowNum) -> {

            Teacher teacher = new Teacher();
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
            teacher.setId(rs.getString("teacher_number"));

            return teacher;
        });

    }

    public Teacher getTeacherById(String id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select teachers.first_name, teachers.last_name, teachers.teacher_number from teachers\r\n"
                            + " where teachers.teacher_number = ?",
                    new Object[] { id }, (rs, rowNum) -> {
                        Teacher teacher = new Teacher();
                        teacher.setId(rs.getString("teacher_number"));
                        teacher.setFirstName(rs.getString("first_name"));
                        teacher.setLastName(rs.getString("last_name"));
                        return teacher;
                    });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public void deleteTeacherById(String id) {
        jdbcTemplate.update("delete from teachers where teacher_number = ?", id);
    }

    public void create(Teacher teacher) {
        jdbcTemplate.update("INSERT INTO teachers (first_name, last_name, teacher_number) VALUES (?, ?, ?)",
                teacher.getFirstName(), teacher.getLastName(), teacher.getId());
    }

}
