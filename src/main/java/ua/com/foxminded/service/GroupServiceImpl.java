package ua.com.foxminded.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ua.com.foxminded.controller.dao.GroupDao;
import ua.com.foxminded.model.Group;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    @Override
    public void createGroups(List<Group> listGroups) {
        groupDao.insertGroups(listGroups);

    }

    @Override
    public void createGroup(Group group) {
        groupDao.create(group);

    }

    @Override
    public Group getGroupByName(String groupName) {
        return groupDao.getGroupByName(groupName);
    }

    @Override
    public void deleteGroupByName(String groupName) {
        groupDao.deleteGroupByName(groupName);

    }

}
