package controller.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import ua.com.foxminded.model.Group;

class GroupDaoTest extends DaoBaseTest {

    public static final String GROUP_NAME = "XX-00";

    @Test
    @SneakyThrows
    void testCreateGroup() {
        groupDao.create(new Group(GROUP_NAME));
        assertEquals(GROUP_NAME, groupDao.getGroupByName(GROUP_NAME).getGroupName());
    }

    @Test
    @SneakyThrows
    void testDeleteGroup() {
        groupDao.create(new Group(GROUP_NAME));
        assertEquals(GROUP_NAME, groupDao.getGroupByName(GROUP_NAME).getGroupName());
        groupDao.deleteGroupByName(GROUP_NAME);
        assertNull(groupDao.getGroupByName(GROUP_NAME));
    }

}
