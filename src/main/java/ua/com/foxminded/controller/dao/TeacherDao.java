package ua.com.foxminded.controller.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import ua.com.foxminded.model.entity.TeacherEntity;

@Component
public class TeacherDao extends JpaDao<TeacherEntity> {

    public List<TeacherEntity> getAll() {
        return entityManager.createQuery("from TeacherEntity").getResultList();
    }

    @Override
    public void update(TeacherEntity entity) {

    }

    @Override
    public TeacherEntity getByName(String name) {
        TypedQuery<TeacherEntity> typedQuery = entityManager
                .createQuery("SELECT t FROM TeacherEntity t WHERE t.lastName = :name", TeacherEntity.class);
        typedQuery.setParameter("name", name);
        return typedQuery.getSingleResult();

    }

}
