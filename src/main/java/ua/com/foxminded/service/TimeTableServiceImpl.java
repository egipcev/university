package ua.com.foxminded.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.TimeTableItemDao;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTable;
import ua.com.foxminded.model.TimeTableItem;

@Service
@AllArgsConstructor
@Slf4j
public class TimeTableServiceImpl implements TimeTableService {

    private TimeTableItemDao timeTableItemDao;

    @Override
    public void createTimeTable(List<TimeTableItem> listTimeTableItems) throws ServiceException {
        try {
            log.info("inserting time tables into DB");
            timeTableItemDao.insertTimeTableItems(listTimeTableItems);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting time tables into DB", e);
        }
    }

    @Override
    public void createTimeTableRecord(TimeTableItem timeTableItem) throws ServiceException {
        try {
            log.info("inserting time table record into DB");
            timeTableItemDao.create(timeTableItem);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting time table record into DB", e);
        }
    }

    @Override
    public void updateTimeTableRecord(int id, TimeTableItem timeTableItem) throws ServiceException {
        try {
            log.info("inserting time table record into DB");
            timeTableItemDao.updateTimeTableItem(id, timeTableItem);
        } catch (DaoException e) {
            throw new ServiceException("error while updating time table record into DB", e);
        }
    }

    @Override
    public TimeTable getDayTimeTablePerTeacher(LocalDate date, Teacher teacher) throws ServiceException {
        TimeTable timeTable = null;
        try {
            log.info("fetching time table from DB");
            timeTable = new TimeTable(timeTableItemDao.getDayTimeTablePerTeacher(date, teacher));
        } catch (DaoException e) {
            throw new ServiceException("error while fetching time table from DB", e);
        }
        return timeTable;
    }

    @Override
    public List<TimeTableItem> getAllTimeTableItems() throws ServiceException {
        List<TimeTableItem> listTimeTables = null;
        try {
            log.info("fetching time table items from DB");
            listTimeTables = timeTableItemDao.getAllTimeTableItems();
        } catch (DaoException e) {
            throw new ServiceException("error while fetching time table items from DB", e);
        }
        return listTimeTables;
    }

    @Override
    public TimeTableItem getTimeTableItemById(int id) throws ServiceException {
        TimeTableItem timeTableItem = null;
        try {
            log.info("fetching timeTableItem from DB");
            timeTableItem = timeTableItemDao.getTimeTableItemById(id);
        } catch (DaoException e) {
            throw new ServiceException("error while fetching timeTableItem from DB", e);
        }
        return timeTableItem;
    }

    @Override
    public void deleteTimeTableRecord(int id) throws ServiceException {
        try {
            log.info("deleting time table record from DB");
            timeTableItemDao.deleteTimeTableItem(id);
        } catch (DaoException e) {
            throw new ServiceException("error while deleting time table record from DB", e);
        }

    }

}
