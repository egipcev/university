package ua.com.foxminded.controller.dao;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.com.foxminded.model.entity.CourseEntity;
import ua.com.foxminded.model.entity.GroupEntity;
import ua.com.foxminded.model.entity.TeacherEntity;
import ua.com.foxminded.model.entity.TimeTableItemEntity;

@Component
public class TimeTableItemDao extends JpaDao<TimeTableItemEntity> {

    public static final DateTimeFormatter DB_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter DB_TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_TIME;

    public List<TimeTableItemEntity> getAll() {
        return entityManager.createQuery("from TimeTableItemEntity").getResultList();
    }

    @Override
    public void save(TimeTableItemEntity timeTable) {
        timeTable.setTeacher(entityManager.find(TeacherEntity.class, timeTable.getTeacher().getId()));
        timeTable.setCourse(entityManager.find(CourseEntity.class, timeTable.getCourse().getId()));
        timeTable.setGroup(entityManager.find(GroupEntity.class, timeTable.getGroup().getId()));
        this.entityManager.persist(timeTable);
    }

    @Override
    public void update(TimeTableItemEntity timeTable) {
        timeTable.setTeacher(entityManager.find(TeacherEntity.class, timeTable.getTeacher().getId()));
        timeTable.setCourse(entityManager.find(CourseEntity.class, timeTable.getCourse().getId()));
        timeTable.setGroup(entityManager.find(GroupEntity.class, timeTable.getGroup().getId()));
        this.entityManager.merge(timeTable);
    }

    @Override
    public TimeTableItemEntity getByName(String name) {
        return null;
    }

}
