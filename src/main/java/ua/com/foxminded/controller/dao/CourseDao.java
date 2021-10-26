package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.model.Course;

@Component
@Slf4j
public class CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertCourses(final List<Course> listCourses) throws DaoException {
        log.debug("Inserting into COURSES table");
        try {
            jdbcTemplate.batchUpdate("INSERT INTO courses (course_name, course_description) VALUES (?, ?)",
                    new BatchPreparedStatementSetter() {

                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            String courseName = listCourses.get(i).getCourseName();
                            String courseDescription = listCourses.get(i).getCourseDescription();
                            log.info("INSERT INTO courses (course_name, course_description) VALUES ({}, {})",
                                    courseName, courseDescription);
                            ps.setString(1, courseName);
                            ps.setString(2, courseDescription);

                        }

                        public int getBatchSize() {
                            return listCourses.size();
                        }

                    });
            log.debug("Completed inserting into COURSES table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into COURSES table", e);
        }

    }

    public void create(Course course) throws DaoException {
        String courseName = course.getCourseName();
        String courseDescription = course.getCourseDescription();
        log.debug("Inserting into COURSES table");
        log.debug("insert into courses (course_name, course_description) values ({}, {})", courseName,
                courseDescription);
        try {
            jdbcTemplate.update("insert into courses (course_name, course_description) values (?, ?)", courseName,
                    courseDescription);
            log.debug("Completed inserting into COURSES table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into COURSES table", e);
        }

    }

    public Course getCourseByName(String courseName) throws DaoException {
        Course course = null;
        try {
            log.debug("fetching data from COURSES table");
            log.debug("select course_name, course_description from courses where course_name = {}", courseName);
            course = jdbcTemplate.queryForObject(
                    "select course_name, course_description from courses where course_name =  ?",
                    new Object[] { courseName },
                    (rs, rowNum) -> new Course(rs.getString("course_name"), rs.getString("course_description")));
            log.debug("Completed fetching data from COURSES table");
        } catch (EmptyResultDataAccessException e) {
            log.debug("empty result is returned");
            log.debug("Completed fetching data from COURSES table");
        } catch (DataAccessException e) {
            throw new DaoException("error while fetching data from COURSES table", e);

        }

        return course;

    }

    public void deleteCourseByName(String courseName) throws DaoException {
        log.debug("deleting data in COURSES table");
        log.debug("delete from courses where course_name = {}", courseName);
        try {
            jdbcTemplate.update("delete from courses where course_name = ?", courseName);
            log.debug("Completed deleting in COURSES table");
        } catch (DataAccessException e) {
            throw new DaoException("error while deleting data from COURSES table", e);

        }

    }

}
