package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.TimeTableItemEntity;

public interface TimeTableService {

    public void createTimeTable(List<TimeTableItemEntity> listTimeTableItems) throws ServiceException;

    public List<TimeTableItemEntity> getAllTimeTableItems() throws ServiceException;

    public void createTimeTableRecord(TimeTableItemEntity timeTableItem) throws ServiceException;

    TimeTableItemEntity getTimeTableItemById(int id) throws ServiceException;

    void deleteTimeTableRecord(int id) throws ServiceException;

    void updateTimeTableRecord(TimeTableItemEntity timeTableItem) throws ServiceException;

}
