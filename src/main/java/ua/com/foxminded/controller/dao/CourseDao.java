package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.Course;

@Component
public class CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int[] insertCourses(final List<Course> listCourses) {

        return jdbcTemplate.batchUpdate("INSERT INTO courses (course_name, course_description) VALUES (?, ?)",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, listCourses.get(i).getCourseName());
                        ps.setString(2, listCourses.get(i).getCourseDescription());
                    }

                    public int getBatchSize() {
                        return listCourses.size();
                    }

                });
    }

    public void create(Course course) {
        jdbcTemplate.update("insert into courses (course_name, course_description) values (?, ?)",
                course.getCourseName(), course.getCourseDescription());
    }

    public Course getCourseByName(String courseName) {
        try {
            return jdbcTemplate.queryForObject(
                    "select course_name, course_description from courses where course_name =  ?",
                    new Object[] { courseName },
                    (rs, rowNum) -> new Course(rs.getString("course_name"), rs.getString("course_description")));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public void deleteCourseByName(String courseName) {
        jdbcTemplate.update("delete from courses where course_name = ?", courseName);
    }

}
