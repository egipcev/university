package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.TimeTableItemDao;
import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTable;
import ua.com.foxminded.model.TimeTableItem;

class TimeTableServiceImplTest extends BaseServiceTest {

    TimeTableServiceImpl service;

    TimeTableItemDao timeTableItemDao;

    @Autowired
    public TimeTableServiceImplTest(TimeTableServiceImpl service, TimeTableItemDao timeTableItemDao) {
        this.timeTableItemDao = timeTableItemDao;
        this.service = service;
    }

    @Test
    @SneakyThrows
    void testGetDayTimeTablePerTeacher() {
        List<TimeTableItem> list = new ArrayList<>();
        String TEACHER_ID = "1";
        Teacher teacher = new Teacher();
        teacher.setId(TEACHER_ID);
        LocalDate date = LocalDate.now();

        TimeTable timeTable = new TimeTable(list);
        when(timeTableItemDao.getDayTimeTablePerTeacher(date, teacher)).thenReturn(list);
        assertEquals(timeTable.getListItems(), service.getDayTimeTablePerTeacher(date, teacher).getListItems());
    }

}
