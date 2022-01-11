package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.Dao;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.GroupEntity;

@Service
@AllArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {

    private Dao<GroupEntity> groupDao;

    @Override
    public void createGroups(List<GroupEntity> listGroups) throws ServiceException {
        log.info("inserting groups into DB");
        groupDao.saveAll(listGroups);

    }

    @Override
    public List<GroupEntity> getAllGroups() throws ServiceException {

        log.info("fetching Groups from DB");
        return groupDao.getAll();
    }

    @Override
    public void createGroup(GroupEntity group) throws ServiceException {
        log.info("inserting group into DB");
        groupDao.save(group);

    }

    @Override
    public GroupEntity getGroupById(int id) throws ServiceException {
        log.info("fetching group from DB");
        return groupDao.getById(id);
    }

    @Override
    public void delete(int id) throws ServiceException {
        log.info("deleting group from DB");
        groupDao.remove(id);

    }

}
