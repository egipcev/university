package ua.com.foxminded.controller.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.com.foxminded.model.entity.GroupEntity;
import ua.com.foxminded.model.entity.StudentEntity;

@Component
public class StudentDao extends JpaDao<StudentEntity> {

    @Override
    public List<StudentEntity> getAll() {
        return entityManager.createQuery("from StudentEntity").getResultList();
    }

    @Override
    public void save(StudentEntity student) {
        student.setGroup(entityManager.find(GroupEntity.class, student.getGroup().getId()));
        this.entityManager.persist(student);
    }

    @Override
    public void update(StudentEntity student) {
        this.entityManager.merge(student);

    }

    @Override
    public StudentEntity getByName(String name) {
        return (StudentEntity) entityManager
                .createQuery("SELECT s FROM students WHERE s.last_name = :name", StudentEntity.class).getSingleResult();

    }

}
