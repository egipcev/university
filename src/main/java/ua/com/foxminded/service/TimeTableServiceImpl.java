package ua.com.foxminded.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ua.com.foxminded.controller.dao.TimeTableItemDao;
import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTable;
import ua.com.foxminded.model.TimeTableItem;

@Service
@AllArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private TimeTableItemDao timeTableItemDao;

    @Override
    public void createTimeTable(List<TimeTableItem> listTimeTableItems) {
        timeTableItemDao.insertTimeTableItems(listTimeTableItems);
    }

    @Override
    public TimeTable getDayTimeTablePerTeacher(LocalDate date, Teacher teacher) {
        return new TimeTable(timeTableItemDao.getDayTimeTablePerTeacher(date, teacher));
    }

}
