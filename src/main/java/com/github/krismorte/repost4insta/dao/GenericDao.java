/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author c007329
 */
public abstract class GenericDao<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("H2PU");
    private EntityManager entityManager;

    private Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void beginTransaction() {
        setEntityManager(emf.createEntityManager());

        getEntityManager().getTransaction().begin();
    }

    public void commit() {
        getEntityManager().getTransaction().commit();
    }

    public void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public void closeTransaction() {
        getEntityManager().close();
    }

    public void commitAndCloseTransaction() {
        commit();
        closeTransaction();
    }

    public void flush() {
        getEntityManager().flush();
    }

    public void joinTransaction() {
        setEntityManager(emf.createEntityManager());
        getEntityManager().joinTransaction();
    }

    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    public void delete(Object id, Class<T> classe) {
        T entityToBeRemoved = getEntityManager().getReference(classe, id);

        getEntityManager().remove(entityToBeRemoved);
    }

    public void delete(String id) {
        T entityToBeRemoved = getEntityManager().getReference(entityClass, id);

        getEntityManager().remove(entityToBeRemoved);
    }

    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    public T find(String entityID) {
        return getEntityManager().find(entityClass, entityID);
    }

    public T findReferenceOnly(String entityID) {
        return getEntityManager().getReference(entityClass, entityID);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;

        try {
            Query query = getEntityManager().createQuery(namedQuery);

            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (T) query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<T> findQuery(String queryString) {
        List<T> result = null;

        try {
            Query query = getEntityManager().createQuery(queryString);
            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for query: " + queryString);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<Object[]> genericQuery(String queryString) {
        List<Object[]> result = null;

        try {
            Query query = getEntityManager().createQuery(queryString);
            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for query: " + queryString);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<Object[]> genericQuery(String queryString, int limit) {
        List<Object[]> result = null;

        try {
            Query query = getEntityManager().createQuery(queryString);
            query.setMaxResults(limit);
            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for query: " + queryString);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    protected List<T> findQuery(String queryString, int limit) {
        List<T> result = null;

        try {
            Query query = getEntityManager().createQuery(queryString);
            query.setMaxResults(limit);
            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for query: " + queryString);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<Object[]> genericFilterQuery(String queryString, Map<String, Object> parameters) {
        List<Object[]> result = null;

        try {
            Query query = getEntityManager().createQuery(queryString);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for query: " + queryString);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<Object[]> genericFilterQuery(String queryString, Map<String, Object> parameters, int limit) {
        List<Object[]> result = null;

        try {
            Query query = getEntityManager().createQuery(queryString);

            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            query.setMaxResults(limit);
            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for query: " + queryString);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<T> findFilterQuery(String queryString, Map<String, Object> parameters) {
        List<T> result = null;

        try {
            Query query = getEntityManager().createQuery(queryString);

            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for query: " + queryString);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<T> findFilterQuery(String queryString, Map<String, Object> parameters, int limit) {
        List<T> result = null;

        try {
            Query query = getEntityManager().createQuery(queryString);

            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            query.setMaxResults(limit);
            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for query: " + queryString);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<T> findFilterResult(String namedQuery, Map<String, Object> parameters) {
        List<T> result = null;

        try {
            Query query = getEntityManager().createNamedQuery(namedQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
