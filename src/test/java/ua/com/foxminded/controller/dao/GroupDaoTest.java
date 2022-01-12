package ua.com.foxminded.controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import ua.com.foxminded.model.entity.GroupEntity;

class GroupDaoTest extends DaoBaseTest {

    public static final String GROUP_NAME = "XX-00";

    @Autowired
    private GroupDao groupDao;

    @Test
    @SneakyThrows
    void testCreateGroup() {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(GROUP_NAME);
        groupDao.save(groupEntity);
        assertEquals(GROUP_NAME, groupDao.getByName(GROUP_NAME).getGroupName());
    }

    @Test
    @SneakyThrows
    void testDeleteGroup() {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(GROUP_NAME);
        groupDao.save(groupEntity);
        assertEquals(GROUP_NAME, groupDao.getByName(GROUP_NAME).getGroupName());
        groupDao.remove(groupEntity.getId());
        assertNull(groupDao.getByName(GROUP_NAME));
    }

}
