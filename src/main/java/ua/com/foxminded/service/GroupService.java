package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.Group;

public interface GroupService {

    public void createGroups(List<Group> listGroups) throws ServiceException;

    public void createGroup(Group group) throws ServiceException;

    public Group getGroupByName(String groupName) throws ServiceException;

    public void deleteGroupByName(String groupName) throws ServiceException;

}
