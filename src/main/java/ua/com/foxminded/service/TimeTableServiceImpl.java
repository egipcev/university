package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.Dao;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.TimeTableItemEntity;

@Service
@AllArgsConstructor
@Slf4j
public class TimeTableServiceImpl implements TimeTableService {

    private Dao<TimeTableItemEntity> timeTableItemDao;

    @Override
    public void createTimeTable(List<TimeTableItemEntity> listTimeTableItems) throws ServiceException {
        log.info("inserting time tables into DB");
        timeTableItemDao.saveAll(listTimeTableItems);
    }

    @Override
    public void createTimeTableRecord(TimeTableItemEntity timeTableItem) throws ServiceException {
        log.info("inserting time table record into DB");
        timeTableItemDao.save(timeTableItem);
    }

    @Override
    public void updateTimeTableRecord(TimeTableItemEntity timeTableItem) throws ServiceException {
        log.info("updating time table record into DB");
        timeTableItemDao.update(timeTableItem);
    }

    @Override
    public List<TimeTableItemEntity> getAllTimeTableItems() throws ServiceException {
        List<TimeTableItemEntity> listTimeTables = null;
        log.info("fetching time table items from DB");
        listTimeTables = timeTableItemDao.getAll();
        return listTimeTables;
    }

    @Override
    public TimeTableItemEntity getTimeTableItemById(int id) throws ServiceException {
        log.info("fetching timeTableItem from DB");
        return timeTableItemDao.getById(id);
    }

    @Override
    public void deleteTimeTableRecord(int id) throws ServiceException {
        log.info("deleting time table record from DB");
        timeTableItemDao.remove(id);

    }

}
