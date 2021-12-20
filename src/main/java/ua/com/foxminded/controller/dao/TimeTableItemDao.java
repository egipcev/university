package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTableItem;

@Component
@Slf4j
public class TimeTableItemDao {

    public static final DateTimeFormatter DB_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter DB_TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_TIME;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertTimeTableItems(final List<TimeTableItem> listTimeTableItems) throws DaoException {
        log.debug("Inserting into time_table_items table");
        try {
            jdbcTemplate.batchUpdate(
                    "INSERT INTO time_table_items ("
                            + "course_date, start_time, end_time, course_id, group_id, teacher_id) VALUES(?, ?, ?, "
                            + "(select course_id from courses where course_name = ?), "
                            + "(select group_id from groups where group_name = ?), "
                            + "(select teacher_id from teachers where first_name = ? AND last_name = ?))",
                    new BatchPreparedStatementSetter() {

                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setObject(1, listTimeTableItems.get(i).getDate());
                            ps.setObject(2, listTimeTableItems.get(i).getStartTime());
                            ps.setObject(3, listTimeTableItems.get(i).getEndTime());
                            ps.setString(4, listTimeTableItems.get(i).getCourse().getCourseName());
                            ps.setString(5, listTimeTableItems.get(i).getGroup().getGroupName());
                            ps.setString(6, listTimeTableItems.get(i).getTeacher().getFirstName());
                            ps.setString(7, listTimeTableItems.get(i).getTeacher().getLastName());
                        }

                        public int getBatchSize() {
                            return listTimeTableItems.size();
                        }

                    });
            log.debug("Completed inserting into time_table_items table without errors");

        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into time_table_items table", e);
        }

    }

    public void create(TimeTableItem timeTableItem) throws DaoException {
        log.debug("Inserting into time_table_items table");

        try {
            jdbcTemplate.update(
                    "INSERT INTO time_table_items ("
                            + "course_date, start_time, end_time, course_id, group_id, teacher_id) VALUES(?, ?, ?, "
                            + "(select course_id from courses where course_name = ?), "
                            + "(select group_id from groups where group_name = ?), "
                            + "(select teacher_id from teachers where teacher_id = ?))",
                    timeTableItem.getDate(), timeTableItem.getStartTime(), timeTableItem.getEndTime(),
                    timeTableItem.getCourse().getCourseName(), timeTableItem.getGroup().getGroupName(),
                    Integer.parseInt(timeTableItem.getTeacher().getId()));
            log.debug("Completed inserting into time_table_items table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into time_table_items table", e);
        }

    }

    public void updateTimeTableItem(int id, TimeTableItem timeTableItem) throws DaoException {
        log.debug("updating data in time_table_items table");
        try {
            jdbcTemplate.update(
                    "update time_table_items set course_date = ?, start_time = ?, end_time = ?,\r\n"
                            + "course_id = (select course_id from courses where course_name = ?),\r\n"
                            + "group_id = (select group_id from groups where group_name = ?),\r\n"
                            + "teacher_id = (select teacher_id from teachers where teacher_id = ?)\r\n"
                            + "where time_table_items.item_id = ?",
                    timeTableItem.getDate(), timeTableItem.getStartTime(), timeTableItem.getEndTime(),
                    timeTableItem.getCourse().getCourseName(), timeTableItem.getGroup().getGroupName(),
                    Integer.parseInt(timeTableItem.getTeacher().getId()), id);
        } catch (DataAccessException e) {
            throw new DaoException("error while updating data in time_table_items table", e);
        }

    }

    public void deleteTimeTableItem(int id) throws DaoException {
        log.debug("deleting data from time_table_items table");
        try {
            jdbcTemplate.update("delete from time_table_items where time_table_items.item_id = ?", id);
        } catch (DataAccessException e) {
            throw new DaoException("error while deleting data in time_table_items table", e);
        }

    }

    public List<TimeTableItem> getAllTimeTableItems() throws DaoException {
        log.debug("Fetching from time_table_items table");
        List<TimeTableItem> listTeimTableItem = new ArrayList<>();
        try {
            listTeimTableItem = jdbcTemplate.query(
                    "select time_table_items.item_id, time_table_items.course_date, time_table_items.start_time, time_table_items.end_time, courses.course_name, courses.course_description, groups.group_name, teachers.first_name, teachers.last_name, teachers.teacher_id from time_table_items\r\n"
                            + "inner join courses on time_table_items.course_id = courses.course_id\r\n"
                            + "inner join groups on time_table_items.group_id = groups.group_id\r\n"
                            + "inner join teachers on time_table_items.teacher_id = teachers.teacher_id\r\n",
                    (rs, rowNum) -> {
                        TimeTableItem timeTableItem = new TimeTableItem();
                        timeTableItem.setId(rs.getInt("item_id"));
                        timeTableItem.setDate(LocalDate.parse(rs.getString("course_date"), DB_DATE_FORMAT));
                        timeTableItem.setStartTime(LocalTime.parse(rs.getString("start_time"), DB_TIME_FORMAT));
                        timeTableItem.setEndTime(LocalTime.parse(rs.getString("end_time"), DB_TIME_FORMAT));
                        timeTableItem
                                .setCourse(new Course(rs.getString("course_name"), rs.getString("course_description")));
                        timeTableItem.setGroup(new Group(rs.getString("group_name")));
                        timeTableItem.setTeacher(new Teacher(rs.getString("teacher_id"), rs.getString("first_name"),
                                rs.getString("last_name")));

                        return timeTableItem;
                    });
            log.debug("Completed fetching from time_table_items table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into time_table_items table", e);
        }
        return listTeimTableItem;

    }

    public List<TimeTableItem> getDayTimeTablePerTeacher(LocalDate date, Teacher teacher) throws DaoException {
        log.debug("Fetching from time_table_items table");
        List<TimeTableItem> listTimeTableItem = new ArrayList<>();
        try {
            listTimeTableItem = jdbcTemplate.query(
                    "select time_table_items.course_date, time_table_items.start_time, time_table_items.end_time, courses.course_name, courses.course_description, groups.group_name, teachers.first_name, teachers.last_name, teachers.teacher_id from time_table_items\r\n"
                            + "inner join courses on time_table_items.course_id = courses.course_id\r\n"
                            + "inner join groups on time_table_items.group_id = groups.group_id\r\n"
                            + "inner join teachers on time_table_items.teacher_id = teachers.teacher_id\r\n"
                            + "where teachers.teacher_id = ? and time_table_items.date = ?",
                    new Object[] { teacher.getId(), date.format(DB_DATE_FORMAT) }, (rs, rowNum) -> {
                        TimeTableItem timeTableItem = new TimeTableItem();
                        timeTableItem.setDate(LocalDate.parse(rs.getString("course_date"), DB_DATE_FORMAT));
                        timeTableItem.setStartTime(LocalTime.parse(rs.getString("start_time"), DB_TIME_FORMAT));
                        timeTableItem.setEndTime(LocalTime.parse(rs.getString("end_time"), DB_TIME_FORMAT));
                        timeTableItem
                                .setCourse(new Course(rs.getString("course_name"), rs.getString("course_description")));
                        timeTableItem.setGroup(new Group(rs.getString("group_name")));
                        timeTableItem.setTeacher(teacher);

                        return timeTableItem;
                    });
            log.debug("Completed fetching from time_table_items table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into time_table_items table", e);
        }
        return listTimeTableItem;

    }

    public TimeTableItem getTimeTableItemById(int id) throws DaoException {
        TimeTableItem timeTableItem = null;

        try {
            log.debug("fetching data from TIMETABLES table");
            log.info(
                    "select time_table_items.item_id, time_table_items.course_date, time_table_items.start_time, time_table_items.end_time, courses.course_name, courses.course_description, groups.group_name, teachers.first_name, teachers.last_name, teachers.teacher_id from time_table_items\r\n"
                            + "inner join courses on time_table_items.course_id = courses.course_id\r\n"
                            + "inner join groups on time_table_items.group_id = groups.group_id\r\n"
                            + "inner join teachers on time_table_items.teacher_id = teachers.teacher_id\r\n"
                            + "where time_table_items.item_id = {}",
                    id);
            timeTableItem = jdbcTemplate.queryForObject(
                    "select time_table_items.item_id, time_table_items.course_date, time_table_items.start_time, time_table_items.end_time, courses.course_name, courses.course_description, groups.group_name, teachers.first_name, teachers.last_name, teachers.teacher_id from time_table_items\r\n"
                            + "inner join courses on time_table_items.course_id = courses.course_id\r\n"
                            + "inner join groups on time_table_items.group_id = groups.group_id\r\n"
                            + "inner join teachers on time_table_items.teacher_id = teachers.teacher_id\r\n"
                            + "where time_table_items.item_id = ?",
                    new Object[] { id }, (rs, rowNum) -> {
                        TimeTableItem timeTableIt = new TimeTableItem();
                        timeTableIt.setId(rs.getInt("item_id"));
                        timeTableIt.setDate(LocalDate.parse(rs.getString("course_date"), DB_DATE_FORMAT));
                        timeTableIt.setStartTime(LocalTime.parse(rs.getString("start_time"), DB_TIME_FORMAT));
                        timeTableIt.setEndTime(LocalTime.parse(rs.getString("end_time"), DB_TIME_FORMAT));
                        timeTableIt
                                .setCourse(new Course(rs.getString("course_name"), rs.getString("course_description")));
                        timeTableIt.setGroup(new Group(rs.getString("group_name")));
                        timeTableIt.setTeacher(new Teacher(rs.getString("teacher_id"), rs.getString("first_name"),
                                rs.getString("last_name")));

                        return timeTableIt;
                    });
            log.debug("Completed fetching data from TIMETABLES table");
        } catch (EmptyResultDataAccessException e) {
            log.debug("empty result is returned");
            log.debug("Completed fetching data from TIMETABLES table");
        } catch (DataAccessException e) {
            throw new DaoException("error while fetching data from TIMETABLES table", e);

        }
        return timeTableItem;
    }
}
