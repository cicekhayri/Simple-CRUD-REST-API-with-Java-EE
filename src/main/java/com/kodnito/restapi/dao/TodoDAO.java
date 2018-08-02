package com.kodnito.restapi.dao;

import com.kodnito.restapi.models.Todo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TodoDAO {

    @PersistenceContext(unitName = "restapi_PU")
    EntityManager em;

    public List<Todo> getAll() {
        return em.createNamedQuery("Todo.findAll", Todo.class).getResultList();
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public void update(Todo todo) {
        em.merge(todo);
    }

    public void create(Todo todo) {
        em.persist(todo);
    }

    public void delete(Todo todo) {
        if (!em.contains(todo)) {
            todo = em.merge(todo);
        }

        em.remove(todo);
    }
}
