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
            jdbcTemplate.batchUpdate("INSERT INTO teachers (first_name, last_name, teacher_number) VALUES (?, ?, ?)",
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
            log.debug("Completed inserting into TEACHERS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into TEACHERS table", e);
        }

    }

    public List<Teacher> getAllTeachers() throws DaoException {
        List<Teacher> listTeachers = new ArrayList<>();

        try {
            log.debug("Inserting into TEACHERS table");
            log.debug("select first_name, last_name, teacher_number from teachers");
            listTeachers = jdbcTemplate.query("select first_name, last_name, teacher_number from teachers",
                    (rs, rowNum) -> {

                        Teacher teacher = new Teacher();
                        teacher.setFirstName(rs.getString("first_name"));
                        teacher.setLastName(rs.getString("last_name"));
                        teacher.setId(rs.getString("teacher_number"));

                        return teacher;
                    });
            log.debug("Completed inserting into TEACHERS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into TEACHERS table", e);
        }
        return listTeachers;
    }

    public Teacher getTeacherById(String id) throws DaoException {
        Teacher teacher = null;
        try {
            log.debug("fetching data from TEACHERS table");
            log.debug("select teachers.first_name, teachers.last_name, teachers.teacher_number from teachers\r\n"
                    + " where teachers.teacher_number = {}", id);
            teacher = jdbcTemplate.queryForObject(
                    "select teachers.first_name, teachers.last_name, teachers.teacher_number from teachers\r\n"
                            + " where teachers.teacher_number = ?",
                    new Object[] { id }, (rs, rowNum) -> {
                        Teacher teach = new Teacher();
                        teach.setId(rs.getString("teacher_number"));
                        teach.setFirstName(rs.getString("first_name"));
                        teach.setLastName(rs.getString("last_name"));
                        return teach;
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

    public void deleteTeacherById(String id) throws DaoException {
        log.debug("deleting data in TEACHERS table");
        log.debug("delete from teachers where teacher_number = {}", id);
        try {
            jdbcTemplate.update("delete from teachers where teacher_number = ?", id);
            log.debug("Completed deleting in TEACHERS table");
        } catch (DataAccessException e) {
            throw new DaoException("error while deleting data from TEACHERS table", e);
        }

    }

    public void create(Teacher teacher) throws DaoException {
        log.debug("Inserting into TEACHERS table");
        log.debug("INSERT INTO teachers (first_name, last_name, teacher_number) VALUES ({}, {}, {})",
                teacher.getFirstName(), teacher.getLastName(), teacher.getId());
        try {
            jdbcTemplate.update("INSERT INTO teachers (first_name, last_name, teacher_number) VALUES (?, ?, ?)",
                    teacher.getFirstName(), teacher.getLastName(), teacher.getId());
            log.debug("Completed inserting into TEACHERS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into TEACHERS table", e);
        }

    }

}
