package ua.com.foxminded.controller.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.model.entity.CourseEntity;
import ua.com.foxminded.model.entity.GroupEntity;
import ua.com.foxminded.model.entity.TeacherEntity;
import ua.com.foxminded.model.entity.TimeTableItemEntity;

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

    @Test
    @SneakyThrows
    void testCreateTimeTableItem() {
        TimeTableItemEntity timeTableItem = new TimeTableItemEntity();
        TeacherEntity teacher = new TeacherEntity();
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(GROUP_NAME);
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(COURSE_NAME);
        teacher.setFirstName(FIRST_NAME);
        teacher.setLastName(LAST_NAME);
        timeTableItem.setDate(LocalDate.parse(DATE));
        timeTableItem.setStartTime(LocalTime.parse(START_TIME));
        timeTableItem.setEndTime(LocalTime.parse(END_TIME));
        timeTableItem.setCourse(courseEntity);
        timeTableItem.setGroup(groupEntity);
        timeTableItem.setTeacher(teacher);
        timeTableItemDao.save(timeTableItem);
        assertTrue(timeTableItemDao.getAll().stream().filter(tt -> tt.getTeacher().getLastName().equals(LAST_NAME))
                .findAny().isPresent());
    }

}
