package controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.TeacherDao;
import ua.com.foxminded.controller.dao.TimeTableItemDao;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTableItem;

class TimeTableItemDaoTest extends DaoBaseTest {

    private static final String DATE = "2022-12-03";
    private static final String START_TIME = "09:00";
    private static final String END_TIME = "09:45";
    private static final String COURSE_NAME = "Math";
    private static final String GROUP_NAME = "AA-11";
    private static final String FIRST_NAME = "Donald";
    private static final String LAST_NAME = "Trump";

    @Autowired
    private TimeTableItemDao timeTableItemDao;

    @Autowired
    private TeacherDao teacherDao;

    @Test
    @SneakyThrows
    void testCreateTimeTableItem() {
        TimeTableItem timeTableItem = new TimeTableItem();
        Teacher teacher = new Teacher();
        teacher.setFirstName(FIRST_NAME);
        teacher.setLastName(LAST_NAME);
        timeTableItem.setDate(LocalDate.parse(DATE));
        timeTableItem.setStartTime(LocalTime.parse(START_TIME));
        timeTableItem.setEndTime(LocalTime.parse(END_TIME));
        timeTableItem.setCourse(new Course(COURSE_NAME, ""));
        timeTableItem.setGroup(new Group(GROUP_NAME));
        timeTableItem.setTeacher(teacher);
        timeTableItemDao.create(timeTableItem);
        assertTrue(timeTableItemDao.getAllTimeTableItems().stream()
                .filter(tt -> tt.getTeacher().getLastName().equals(LAST_NAME)).findAny().isPresent());
    }

    @Test
    @SneakyThrows
    void testGetDayTimeTablePerTeacher() {
        TimeTableItem timeTableOne = new TimeTableItem();
        TimeTableItem timeTableTwo = new TimeTableItem();

        Teacher teacher = new Teacher();
        teacher.setFirstName("FIRST_NAME");
        teacher.setLastName("LAST_NAME");
        teacherDao.create(teacher);
        timeTableOne.setDate(LocalDate.parse(DATE));
        timeTableOne.setStartTime(LocalTime.parse(START_TIME));
        timeTableOne.setEndTime(LocalTime.parse(END_TIME));
        timeTableOne.setCourse(new Course(COURSE_NAME, ""));
        timeTableOne.setGroup(new Group(GROUP_NAME));
        timeTableOne.setTeacher(teacher);
        timeTableItemDao.create(timeTableOne);

        timeTableTwo.setDate(LocalDate.parse("2021-09-09"));
        timeTableTwo.setStartTime(LocalTime.parse(START_TIME));
        timeTableTwo.setEndTime(LocalTime.parse(END_TIME));
        timeTableTwo.setCourse(new Course(COURSE_NAME, ""));
        timeTableTwo.setGroup(new Group(GROUP_NAME));
        timeTableTwo.setTeacher(teacher);
        timeTableItemDao.create(timeTableTwo);

        assertEquals(1, timeTableItemDao.getDayTimeTablePerTeacher(LocalDate.parse(DATE), teacher).size());
    }

}
