package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.GroupDao;
import ua.com.foxminded.model.Group;

class GroupServiceImplTest extends BaseServiceTest {

    private GroupDao groupDao;

    private GroupServiceImpl service;

    @Autowired
    public GroupServiceImplTest(GroupDao groupDao, GroupServiceImpl service) {
        this.groupDao = groupDao;
        this.service = service;
    }

    @Test
    @SneakyThrows
    void testGetGroupByName() {
        String GROUP_NAME = "Name";
        Group group = new Group(GROUP_NAME);
        when(groupDao.getGroupByName(GROUP_NAME)).thenReturn(group);
        assertEquals(group, service.getGroupByName(GROUP_NAME));
    }

}
