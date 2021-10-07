package ua.com.foxminded.service;

import java.util.List;

import ua.com.foxminded.model.Group;

public interface GroupService {

    public void createGroups(List<Group> listGroups);

    public void createGroup(Group group);

    public Group getGroupByName(String groupName);

    public void deleteGroupByName(String groupName);

}
