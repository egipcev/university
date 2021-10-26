package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.model.Group;

@Component
@Slf4j
public class GroupDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertGroups(final List<Group> listGroups) throws DaoException {
        log.debug("Inserting into GROUPS table");
        try {
            jdbcTemplate.batchUpdate("INSERT INTO groups (group_name) VALUES (?)", new BatchPreparedStatementSetter() {

                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    String groupName = listGroups.get(i).getGroupName();
                    log.debug("INSERT INTO groups (group_name) VALUES ({})", groupName);
                    ps.setString(1, groupName);

                }

                public int getBatchSize() {
                    return listGroups.size();
                }

            });
            log.debug("Completed inserting into GROUPS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into GROUPS table", e);
        }

    }

    public void create(Group group) throws DaoException {
        String groupName = group.getGroupName();
        log.debug("Inserting into GROUPS table");
        log.debug("insert into groups (group_name) values ({})", groupName);
        try {
            jdbcTemplate.update("insert into groups (group_name) values (?)", groupName);
            log.debug("Completed inserting into GROUPS table without errors");
        } catch (DataAccessException e) {
            throw new DaoException("error while inserting data into GROUPS table", e);
        }

    }

    public Group getGroupByName(String groupName) throws DaoException {
        Group group = null;
        try {
            log.debug("fetching data from GROUPS table");
            log.debug("select group_name from groups where group_name =  {}", groupName);
            group = jdbcTemplate.queryForObject("select group_name from groups where group_name =  ?",
                    new Object[] { groupName }, (rs, rowNum) -> new Group(rs.getString("group_name")));
            log.debug("Completed fetching data from GROUPS table");
        } catch (EmptyResultDataAccessException e) {
            log.debug("empty result is returned");
            log.debug("Completed fetching data from GROUPS table");
        } catch (DataAccessException e) {
            throw new DaoException("error while fetching data from GROUPS table", e);

        }

        return group;
    }

    public void deleteGroupByName(String groupName) throws DaoException {
        log.debug("deleting data in GROUPS table");
        log.debug("delete from groups where group_name = {}", groupName);
        try {
            jdbcTemplate.update("delete from groups where group_name = ?", groupName);
            log.debug("Completed deleting in GROUPS table");
        } catch (DataAccessException e) {
            throw new DaoException("error while deleting data from GROUPS table", e);
        }

    }

}
