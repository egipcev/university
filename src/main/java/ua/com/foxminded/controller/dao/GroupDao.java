package ua.com.foxminded.controller.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.Group;

@Component
public class GroupDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int[] insertGroups(final List<Group> listGroups) {

        return jdbcTemplate.batchUpdate("INSERT INTO groups (group_name) VALUES (?)",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, listGroups.get(i).getGroupName());

                    }

                    public int getBatchSize() {
                        return listGroups.size();
                    }

                });
    }

    public void create(Group group) {
        jdbcTemplate.update("insert into groups (group_name) values (?)", group.getGroupName());
    }

    public Group getGroupByName(String groupName) {
        try {
            return jdbcTemplate.queryForObject("select group_name from groups where group_name =  ?",
                    new Object[] { groupName }, (rs, rowNum) -> new Group(rs.getString("group_name")));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void deleteGroupByName(String groupName) {
        jdbcTemplate.update("delete from groups where group_name = ?", groupName);
    }

}
