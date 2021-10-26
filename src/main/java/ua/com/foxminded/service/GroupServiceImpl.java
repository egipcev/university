package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.dao.GroupDao;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Group;

@Service
@AllArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    @Override
    public void createGroups(List<Group> listGroups) throws ServiceException {
        try {
            log.info("inserting groups into DB");
            groupDao.insertGroups(listGroups);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting groups into DB", e);
        }

    }

    @Override
    public void createGroup(Group group) throws ServiceException {
        try {
            log.info("inserting group into DB");
            groupDao.create(group);
        } catch (DaoException e) {
            throw new ServiceException("error while inserting group into DB", e);
        }

    }

    @Override
    public Group getGroupByName(String groupName) throws ServiceException {
        Group group = null;
        try {
            log.info("fetching group from DB");
            group = groupDao.getGroupByName(groupName);
        } catch (DaoException e) {
            throw new ServiceException("error while fetching group from DB", e);
        }
        return group;
    }

    @Override
    public void deleteGroupByName(String groupName) throws ServiceException {
        try {
            log.info("deleting group from DB");
            groupDao.deleteGroupByName(groupName);
        } catch (DaoException e) {
            throw new ServiceException("error while deleting group from DB", e);
        }

    }

}
