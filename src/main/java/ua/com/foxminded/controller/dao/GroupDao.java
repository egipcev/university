package ua.com.foxminded.controller.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import ua.com.foxminded.model.entity.GroupEntity;

@Component
public class GroupDao extends JpaDao<GroupEntity> {

    public List<GroupEntity> getAll() {
        return entityManager.createQuery("from GroupEntity").getResultList();
    }

    @Override
    public void update(GroupEntity entity) {
        entityManager.merge(entity);

    }

    @Override
    public GroupEntity getByName(String name) {
        TypedQuery<GroupEntity> typedQuery = entityManager
                .createQuery("SELECT g FROM GroupEntity g WHERE g.groupName = :name", GroupEntity.class);
        typedQuery.setParameter("name", name);
        return typedQuery.getSingleResult();
    }

}
