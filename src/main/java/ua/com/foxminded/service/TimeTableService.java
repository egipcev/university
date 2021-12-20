package ua.com.foxminded.service;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTable;
import ua.com.foxminded.model.TimeTableItem;

public interface TimeTableService {

    public void createTimeTable(List<TimeTableItem> listTimeTableItems) throws ServiceException;

    public TimeTable getDayTimeTablePerTeacher(LocalDate date, Teacher teacher) throws ServiceException;

    public List<TimeTableItem> getAllTimeTableItems() throws ServiceException;

    public void createTimeTableRecord(TimeTableItem timeTableItem) throws ServiceException;

    TimeTableItem getTimeTableItemById(int id) throws ServiceException;

    void updateTimeTableRecord(int id, TimeTableItem timeTableItem) throws ServiceException;

    void deleteTimeTableRecord(int id) throws ServiceException;

}
