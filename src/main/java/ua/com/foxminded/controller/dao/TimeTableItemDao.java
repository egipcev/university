package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTableItem;

@Component
public class TimeTableItemDao {

    public static final DateTimeFormatter DB_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter DB_TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_TIME;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int[] insertTimeTableItems(final List<TimeTableItem> listTimeTableItems) {

        return jdbcTemplate.batchUpdate(
                "INSERT INTO time_table_items ("
                        + "date, start_time, end_time, course_id, group_id, teacher_id) VALUES(?, ?, ?, "
                        + "(select course_id from courses where course_name = ?), "
                        + "(select group_id from groups where group_name = ?), "
                        + "(select teacher_id from teachers where first_name = ? AND last_name = ?))",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, listTimeTableItems.get(i).getDate().format(DB_DATE_FORMAT));
                        ps.setString(2, listTimeTableItems.get(i).getStartTime().format(DB_TIME_FORMAT));
                        ps.setString(3, listTimeTableItems.get(i).getEndTime().format(DB_TIME_FORMAT));
                        ps.setString(4, listTimeTableItems.get(i).getCourse().getCourseName());
                        ps.setString(5, listTimeTableItems.get(i).getGroup().getGroupName());
                        ps.setString(6, listTimeTableItems.get(i).getTeacher().getFirstName());
                        ps.setString(7, listTimeTableItems.get(i).getTeacher().getLastName());
                    }

                    public int getBatchSize() {
                        return listTimeTableItems.size();
                    }

                });

    }

    public void create(TimeTableItem timeTableItem) {
        jdbcTemplate.update(
                "INSERT INTO time_table_items ("
                        + "date, start_time, end_time, course_id, group_id, teacher_id) VALUES(?, ?, ?, "
                        + "(select course_id from courses where course_name = ?), "
                        + "(select group_id from groups where group_name = ?), "
                        + "(select teacher_id from teachers where first_name = ? AND last_name = ?))",
                timeTableItem.getDate(), timeTableItem.getStartTime(), timeTableItem.getEndTime(),
                timeTableItem.getCourse().getCourseName(), timeTableItem.getGroup().getGroupName(),
                timeTableItem.getTeacher().getFirstName(), timeTableItem.getTeacher().getLastName());
    }

    public List<TimeTableItem> getAllTimeTableItems() {

        return jdbcTemplate.query(
                "select time_table_items.date, time_table_items.start_time, time_table_items.end_time, courses.course_name, courses.course_description, groups.group_name, teachers.first_name, teachers.last_name, teachers.teacher_number from time_table_items\r\n"
                        + "inner join courses on time_table_items.course_id = courses.course_id\r\n"
                        + "inner join groups on time_table_items.group_id = groups.group_id\r\n"
                        + "inner join teachers on time_table_items.teacher_id = teachers.teacher_id\r\n",
                (rs, rowNum) -> {
                    TimeTableItem timeTableItem = new TimeTableItem();
                    Teacher teacher = new Teacher();
                    teacher.setFirstName(rs.getString("first_name"));
                    teacher.setLastName(rs.getString("last_name"));
                    teacher.setId("teacher_number");
                    timeTableItem.setDate(LocalDate.parse(rs.getString("date"), DB_DATE_FORMAT));
                    timeTableItem.setStartTime(LocalTime.parse(rs.getString("start_time"), DB_TIME_FORMAT));
                    timeTableItem.setEndTime(LocalTime.parse(rs.getString("end_time"), DB_TIME_FORMAT));
                    timeTableItem
                            .setCourse(new Course(rs.getString("course_name"), rs.getString("course_description")));
                    timeTableItem.setGroup(new Group(rs.getString("group_name")));
                    timeTableItem.setTeacher(teacher);

                    return timeTableItem;
                });

    }

    public List<TimeTableItem> getDayTimeTablePerTeacher(LocalDate date, Teacher teacher) {
        return jdbcTemplate.query(
                "select time_table_items.date, time_table_items.start_time, time_table_items.end_time, courses.course_name, courses.course_description, groups.group_name, teachers.first_name, teachers.last_name, teachers.teacher_number from time_table_items\r\n"
                        + "inner join courses on time_table_items.course_id = courses.course_id\r\n"
                        + "inner join groups on time_table_items.group_id = groups.group_id\r\n"
                        + "inner join teachers on time_table_items.teacher_id = teachers.teacher_id\r\n"
                        + "where teachers.teacher_number = ? and time_table_items.date = ?",
                new Object[] { teacher.getId(), date.format(DB_DATE_FORMAT) }, (rs, rowNum) -> {
                    TimeTableItem timeTableItem = new TimeTableItem();
                    timeTableItem.setDate(LocalDate.parse(rs.getString("date"), DB_DATE_FORMAT));
                    timeTableItem.setStartTime(LocalTime.parse(rs.getString("start_time"), DB_TIME_FORMAT));
                    timeTableItem.setEndTime(LocalTime.parse(rs.getString("end_time"), DB_TIME_FORMAT));
                    timeTableItem
                            .setCourse(new Course(rs.getString("course_name"), rs.getString("course_description")));
                    timeTableItem.setGroup(new Group(rs.getString("group_name")));
                    timeTableItem.setTeacher(teacher);

                    return timeTableItem;
                });
    }
}
