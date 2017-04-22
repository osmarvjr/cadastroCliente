package br.com.cadastrocliente.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.cadastrocliente.dao.GenericDAO;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, PK> implements GenericDAO<T, PK> {
	private final static String UNIT_NAME = "ClientePU";
	
	@PersistenceContext(unitName=UNIT_NAME)
	protected EntityManager entityManager;

	public void persist(T entity) {
		entityManager.persist(entity);
	}

	public void merge(T entity) {
		entityManager.merge(entity);
	}

	public void remove(T entity) {
		entityManager.remove(entity);
	}

	public void removeById(PK id) {
		T entity = getById(id);
		entityManager.remove(entity);
	}

	public T getById(PK id) {
		return entityManager.find(getTypeClass(), id);
	}

	public Collection<T> findAll() {
		return entityManager.createQuery("FROM " + getTypeClass().getName())
				.getResultList();
	}

	public Query createQuery(String query, Object... parameters) {
		Query q = entityManager.createQuery(query);

		for (int i = 1; i <= parameters.length; i++) {
			q.setParameter(i, parameters[i]);
		}
		
		return q;
	}

	private Class<T> getTypeClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
