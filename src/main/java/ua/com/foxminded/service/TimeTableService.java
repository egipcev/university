package ua.com.foxminded.service;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTable;
import ua.com.foxminded.model.TimeTableItem;

public interface TimeTableService {

    public void createTimeTable(List<TimeTableItem> listTimeTableItems);

    public TimeTable getDayTimeTablePerTeacher(LocalDate date, Teacher teacher);

}
