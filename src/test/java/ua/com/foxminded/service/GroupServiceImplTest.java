package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.GroupDao;
import ua.com.foxminded.model.entity.GroupEntity;

class GroupServiceImplTest extends BaseServiceTest {

    private GroupDao groupDao;
    private GroupServiceImpl service;
    private GroupEntity groupEntity;

    @Autowired
    public GroupServiceImplTest(GroupDao groupDao, GroupServiceImpl service, GroupEntity groupEntity) {
        this.groupDao = groupDao;
        this.service = service;
        this.groupEntity = groupEntity;
    }

    @Test
    @SneakyThrows
    void testGetGroupByName() {
        groupEntity.setId(1);
        when(groupDao.getById(1)).thenReturn(groupEntity);
        assertEquals(groupEntity, service.getGroupById(1));
    }

}
