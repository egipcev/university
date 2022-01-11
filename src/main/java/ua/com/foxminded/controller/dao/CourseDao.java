package ua.com.foxminded.controller.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import ua.com.foxminded.model.entity.CourseEntity;

@Component
public class CourseDao extends JpaDao<CourseEntity> {

    @Override
    public List<CourseEntity> getAll() {
        return entityManager.createQuery("from CourseEntity").getResultList();
    }

    @Override
    public void update(CourseEntity entity) {
        entityManager.merge(entity);

    }

    @Override
    public CourseEntity getByName(String name) {
        TypedQuery<CourseEntity> typedQuery = entityManager
                .createQuery("SELECT c FROM CourseEntity c WHERE c.courseName = :name", CourseEntity.class);
        typedQuery.setParameter("name", name);
        return typedQuery.getSingleResult();

    }

}
