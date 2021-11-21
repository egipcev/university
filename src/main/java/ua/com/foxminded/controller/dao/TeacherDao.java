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
import ua.com.foxminded.model.Teacher;

@Component
@Slf4j
public class TeacherDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertTeachers(final List<Teacher> listTeachers) throws DaoException {
        log.debug("Inserting into TEACHERS table");
        try {
            jdbcTemplate.batchUpdate("INSERT INTO teachers (first_name, last_name) VALUES (?, ?)",
                    new BatchPreparedStatementSetter() {

                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, listTeachers.get(i).getFirstName());
                            ps.setString(2, listTeachers.get(i).getLastName());

                        }

                        public int getBatchSize() {
                            return listTeachers.size();
                        }

                    });
            log.debug("Completed inserting into TEACHERS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into TEACHERS table", e);
        }

    }

    public List<Teacher> getAllTeachers() throws DaoException {
        List<Teacher> listTeachers = new ArrayList<>();

        try {
            log.debug("Fetching from TEACHERS table");
            log.debug("select first_name, last_name, teacher_id from teachers");
            listTeachers = jdbcTemplate.query("select first_name, last_name, teacher_id from teachers",
                    (rs, rowNum) -> {

                        return new Teacher(rs.getString("teacher_id"), rs.getString("first_name"),
                                rs.getString("last_name"));
                    });
            log.debug("Completed Fetching from TEACHERS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into TEACHERS table", e);
        }
        return listTeachers;
    }

    public Teacher getTeacherById(int id) throws DaoException {
        Teacher teacher = null;
        try {
            log.debug("fetching data from TEACHERS table");
            log.debug("select teachers.first_name, teachers.last_name, teachers.teacher_id from teachers\r\n"
                    + " where teachers.teacher_id = {}", id);
            teacher = jdbcTemplate.queryForObject(
                    "select teachers.first_name, teachers.last_name, teachers.teacher_id from teachers\r\n"
                            + " where teachers.teacher_id = ?",
                    new Object[] { id }, (rs, rowNum) -> {
                        return new Teacher(rs.getString("teacher_id"), rs.getString("first_name"),
                                rs.getString("last_name"));
                    });
            log.debug("Completed fetching data from TEACHERS table");
        } catch (EmptyResultDataAccessException e) {
            log.debug("empty result is returned");
            log.debug("Completed fetching data from TEACHERS table");
        } catch (DataAccessException e) {
            throw new DaoException("error while fetching data from TEACHERS table", e);

        }
        return teacher;

    }

    public void deleteTeacherById(int id) throws DaoException {
        log.debug("deleting data in TEACHERS table");
        log.debug("delete from teachers where teacher_id = {}", id);
        try {
            jdbcTemplate.update("delete from teachers where teacher_id = ?", id);
            log.debug("Completed deleting in TEACHERS table");
        } catch (DataAccessException e) {
            throw new DaoException("error while deleting data from TEACHERS table", e);
        }

    }

    public void create(Teacher teacher) throws DaoException {
        log.debug("Inserting into TEACHERS table");
        log.debug("INSERT INTO teachers (first_name, last_name) VALUES ({}, {})", teacher.getFirstName(),
                teacher.getLastName());
        try {
            jdbcTemplate.update("INSERT INTO teachers (first_name, last_name) VALUES (?, ?)", teacher.getFirstName(),
                    teacher.getLastName());
            log.debug("Completed inserting into TEACHERS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into TEACHERS table", e);
        }

    }

}
