package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;

@Component
@Slf4j
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getAllStudents() throws DaoException {
        List<Student> listStudent = new ArrayList<>();

        try {
            log.debug("fetching from students table");
            log.debug(
                    "select students.student_id, students.first_name, students.last_name, students.student_id, groups.group_name from students inner join groups on students.group_id = groups.group_id");
            listStudent = jdbcTemplate.query(
                    "select students.first_name, students.last_name, students.student_id, groups.group_name from students\r\n"
                            + "inner join groups on students.group_id = groups.group_id",
                    (rs, rowNum) -> {
                        Group group = new Group(rs.getString("group_name"));
                        Student student = new Student(rs.getString("student_id"), rs.getString("first_name"),
                                rs.getString("last_name"));
                        student.setGroup(group);
                        return student;
                    });
            log.debug("Completed fetching from students table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while fetching data from students table", e);
        }

        return listStudent;
    }

    public Student getStudentById(int id) throws DaoException {
        Student student = null;

        try {
            log.debug("fetching data from STUDENTS table");
            log.info(
                    "select students.first_name, students.last_name, students.student_id, groups.group_name from students\r\n"
                            + "inner join groups on students.group_id = groups.group_id\r\n"
                            + "where students.student_id = {}",
                    id);
            student = jdbcTemplate.queryForObject(
                    "select students.first_name, students.last_name, students.student_id, groups.group_name from students\r\n"
                            + "inner join groups on students.group_id = groups.group_id\r\n"
                            + " where students.student_id = ?",
                    new Object[] { id }, (rs, rowNum) -> {
                        Group group = new Group(rs.getString("group_name"));
                        Student stud = new Student(rs.getString("student_id"), rs.getString("first_name"),
                                rs.getString("last_name"));
                        stud.setGroup(group);
                        return stud;
                    });
            log.debug("Completed fetching data from STUDENTS table");
        } catch (EmptyResultDataAccessException e) {
            log.debug("empty result is returned");
            log.debug("Completed fetching data from STUDENTS table");
        } catch (DataAccessException e) {
            throw new DaoException("error while fetching data from STUDENTS table", e);

        }
        return student;

    }

    public void deleteStudentById(int id) throws DaoException {
        log.debug("deleting data in STUDENTS table");
        log.debug("delete from students where student_id = {}", id);
        try {
            jdbcTemplate.update("delete from students where student_id = ?", id);
            log.debug("Completed deleting in STUDENTS table");
        } catch (DataAccessException e) {
            throw new DaoException("error while deleting data from STUDENTS table", e);
        }

    }

    public void updateStudentGroup(int id, String groupName) throws DaoException {
        log.debug("updating data in STUDENTS table");
        log.debug("update students\r\n"
                + "set students.group_id = (select groups.group_id from groups where groups.group_name = {})\r\n"
                + "where students.student_id = {}", groupName, id);
        try {
            jdbcTemplate.update("update students\r\n"
                    + "set students.group_id = (select groups.group_id from groups where groups.group_name = ?)\r\n"
                    + "where students.student_id = ?", groupName, id);
        } catch (DataAccessException e) {
            throw new DaoException("error while updating data in STUDENTS table", e);
        }

    }

    public void create(Student student) throws DaoException {
        log.debug("Inserting into STUDENTS table");
        log.debug(
                "INSERT INTO students (first_name, last_name, group_id) SELECT {}, {}, group_id from GROUPS where group_name = {}",
                student.getFirstName(), student.getLastName(), student.getGroup().getGroupName());
        try {
            jdbcTemplate.update(
                    "INSERT INTO students (first_name, last_name, group_id) SELECT ?, ?, group_id from GROUPS where group_name = ?",
                    student.getFirstName(), student.getLastName(), student.getGroup().getGroupName());
            log.debug("Completed inserting into STUDENTS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into STUDENTS table", e);
        }

    }

    public void insertStudents(final List<Student> listStudents) throws DaoException {
        log.debug("Inserting into STUDENTS table");
        try {
            jdbcTemplate.batchUpdate(
                    "INSERT INTO students (first_name, last_name, group_id) SELECT ?, ?, group_id from GROUPS where group_name = ?",
                    new BatchPreparedStatementSetter() {

                        public void setValues(PreparedStatement ps, int i) throws SQLException {

                            ps.setString(1, listStudents.get(i).getFirstName());
                            ps.setString(2, listStudents.get(i).getLastName());
                            ps.setObject(3, listStudents.get(i).getGroup().getGroupName());

                        }

                        public int getBatchSize() {
                            return listStudents.size();
                        }

                    });
            log.debug("Completed inserting into STUDENTS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into STUDENTS table", e);
        }

    }

}
