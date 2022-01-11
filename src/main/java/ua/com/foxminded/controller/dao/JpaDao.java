package ua.com.foxminded.controller.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class JpaDao<E> implements Dao<E> {

    private Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    protected JpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    public void saveAll(List<E> listEntities) {

        listEntities.forEach(entity -> entityManager.persist(entity));

    }

    public void saveOrUpdate(E entity) {
        this.entityManager.merge(entity);
        this.entityManager.persist(entity);
    }

    public void save(E entity) {
        this.entityManager.persist(entity);
    }

    public void remove(int id) {
        entityManager.remove(this.getById(id));
    }

    public E getById(int id) {
        return entityManager.find(entityClass, id);

    }

}
