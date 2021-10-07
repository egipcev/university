package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;

@Component
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getAllStudents() {

        return jdbcTemplate.query("select students.first_name, students.last_name, groups.group_name from students\r\n"
                + "inner join groups on students.group_id = groups.group_id", (rs, rowNum) -> {
                    Group group = new Group(rs.getString("group_name"));
                    Student student = new Student();
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setGroup(group);
                    return student;
                });

    }

    public Student getStudentById(String id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select students.first_name, students.last_name, students.student_number, groups.group_name from students\r\n"
                            + "inner join groups on students.group_id = groups.group_id\r\n"
                            + " where students.student_number = ?",
                    new Object[] { id }, (rs, rowNum) -> {
                        Group group = new Group(rs.getString("group_name"));
                        Student student = new Student();
                        student.setId(rs.getString("student_number"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setGroup(group);
                        return student;
                    });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public void deleteStudentById(String id) {
        jdbcTemplate.update("delete from students where student_number = ?", id);
    }

    public void updateStudentGroup(String studentNumber, String groupName) {
        jdbcTemplate.update("update students\r\n"
                + "set students.group_id = (select groups.group_id from groups where groups.group_name = ?)\r\n"
                + "where students.student_number = ?", groupName, studentNumber);
    }

    public void create(Student student) {
        jdbcTemplate.update(
                "INSERT INTO students (first_name, last_name, student_number, group_id) SELECT ?, ?, ?, group_id from GROUPS where group_name = ?",
                student.getFirstName(), student.getLastName(), student.getId(), student.getGroup().getGroupName());
    }

    public int[] insertStudents(final List<Student> listStudents) {

        return jdbcTemplate.batchUpdate(
                "INSERT INTO students (first_name, last_name, student_number, group_id) SELECT ?, ?, ?, group_id from GROUPS where group_name = ?",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, listStudents.get(i).getFirstName());
                        ps.setString(2, listStudents.get(i).getLastName());
                        ps.setString(3, listStudents.get(i).getId());
                        ps.setObject(4, listStudents.get(i).getGroup().getGroupName());

                    }

                    public int getBatchSize() {
                        return listStudents.size();
                    }

                });

    }

}
