package com.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class ConnectionJPA<T> implements Interface<T>, Serializable {
	private static final long serialVersionUID = 1L;

	protected static EntityManagerFactory emf = null;
	protected static EntityManager em = null;

	private Class<T> entity;

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public ConnectionJPA() {
		this(null);
	}

	public ConnectionJPA(Class<T> entity) {
		this.entity = entity;
	}

	///////////////////////////////////////////////////////
	// Init
	///////////////////////////////////////////////////////
	public static EntityManager getEm() {
		if (em == null || emf == null) {
			if (emf == null) {
				emf = Persistence.createEntityManagerFactory(Variable.APP_PERSISTENCE);
			}
			em = emf.createEntityManager();
		}
		return em;
	}

	public static EntityManager getEmClose() {
		if (em != null) {
			em.close();
		}
		return em;
	}

	public static EntityManager getEmReset() {
		if (em != null) {
			em.close();
			emf = Persistence.createEntityManagerFactory(Variable.APP_PERSISTENCE);
			em = emf.createEntityManager();
		}
		return em;
	}

	///////////////////////////////////////////////////////
	// Method Abstract
	///////////////////////////////////////////////////////
	/**
	 * Metodo que lista todos los datos de la tabla.
	 * 
	 * @return represeta la lista.
	 */
	@Override
	public List<T> list() {
		return listLimit(true, -1, 1);
	}

	/**
	 * Metodo que permite consultar todos los reultados pero con un limite
	 * especifico.
	 */
	public List<T> listLimit(boolean all, int maxResults, int firstResul) {
		TypedQuery<T> query = getEm().createNamedQuery(entity.getSimpleName() + ".findAll", entity);
		if (!all) {
			query.setMaxResults(maxResults);
			query.setFirstResult(firstResul);
		}
		return query.getResultList();
	}
	
	/**
	 * Metodo que trae el elemento mediante su PK.
	 * 
	 * @param <E>, representa el tipo de dato.
	 * @param id,  representa la PK.
	 * @return el elemento generico E.
	 */
	@Override
	public <E> T find(E id) {
		return (T) getEm().find(entity, id);
	}
	
	/**
	 * Metodo que inserta un elemento a la tabla.
	 * 
	 * @param o representa el elemento a insertar.
	 */
	@Override
	public void insert(T o) {
		try {
			getEm().getTransaction().begin();
			getEm().persist(o);
			getEm().getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if (em != null) {
				em = getEmReset();
			}
		}
	}
	
	/**
	 * Metodo que actualiza un elemento de la tabla.
	 * 
	 * @param o representa el elemento a actualizar.
	 */
	@Override
	public void update(T o) {
		try {
			getEm().getTransaction().begin();
			o = getEm().merge(o);
			getEm().getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if (em != null) {
				//em = getEmReset();
			}
		}
	}
	
	/**
	 * Metodo que elimina un elemento en la tabla.
	 * 
	 * @param o representa el elemento.
	 */
	@Override
	public <E> void delete(E o) {
		try {
			getEm().getTransaction().begin();
			T model;
			try {
				model = find(o);
			} catch (EntityNotFoundException not) {
				throw new Exception("The " + entity.getSimpleName() + " with pk " + o + " no exists.",
						not);
			}
			getEm().remove(model);
			getEm().getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if (em != null) {
				//em = getEmReset();
			}
		}
	}
	
	/**
	 * Metodo que trae un elemento depediendo de un fila y su valor.
	 * 
	 * @param <E>     representa el valor.
	 * @param column, representa la columna.
	 * @param o,      representa el valor columna.
	 * @return el elemento encontrado.
	 */
	@Override
	public <E> T findByField(String column, E o) {
		List<T> queryResult = findByFieldList(column, o);
		T returnObject = null;
		if (!queryResult.isEmpty()) {
			returnObject = queryResult.get(0);
		}
		return returnObject;
	}
	
	/**
	 * Metodo que lista los elementos depediendo de un columna y su valor.
	 * 
	 * @param <E>        representa el valor.
	 * @param fieldName,  representa la columna.
	 * @param fieldValue, representa el valor fila.
	 * @return la lista.
	 */
	@Override
	public <E> List<T> findByFieldList(String column, E o) {
		CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
		Root<T> root = criteriaQuery.from(entity);
		criteriaQuery.select(root);

		@SuppressWarnings("unchecked")
		ParameterExpression<E> params = criteriaBuilder.parameter((Class<E>) Object.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get(column), params));

		TypedQuery<T> query = (TypedQuery<T>) getEm().createQuery(criteriaQuery);
		query.setParameter(params, (E) o);
		return query.getResultList();
	}
	
	/**
	 * Metodo que permite conocer la cantidad de registros en una tabla.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long count() {
		CriteriaQuery criteriaBuilder = getEm().getCriteriaBuilder().createQuery();
		Root<T> root = criteriaBuilder.from(entity);
		criteriaBuilder.select(getEm().getCriteriaBuilder().count(root));
		TypedQuery<T> query = (TypedQuery<T>) getEm().createQuery(criteriaBuilder);
		return ((Long) query.getSingleResult()).intValue();
	}
	
	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
	public static EntityManagerFactory getEmf() {
		return emf;
	}

	public static void setEmf(EntityManagerFactory emf) {
		ConnectionJPA.emf = emf;
	}

	public Class<T> getEntity() {
		return entity;
	}

	public void setEntity(Class<T> entity) {
		this.entity = entity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setEm(EntityManager em) {
		ConnectionJPA.em = em;
	}
}
