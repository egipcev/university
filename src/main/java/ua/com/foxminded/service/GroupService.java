package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.GroupEntity;

public interface GroupService {

    public void createGroups(List<GroupEntity> listGroups) throws ServiceException;

    public void createGroup(GroupEntity group) throws ServiceException;

    public void delete(int id) throws ServiceException;

    List<GroupEntity> getAllGroups() throws ServiceException;

    GroupEntity getGroupById(int id) throws ServiceException;

}
