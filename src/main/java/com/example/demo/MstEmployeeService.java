package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

@Service
public class MstEmployeeService {

	@PersistenceContext
	private EntityManager entityManager;

	/* 全件取得 */
	@SuppressWarnings("unchecked")
	public List<MstEmployee> getAll() {
		return (List<MstEmployee>) entityManager
				.createQuery("from MstEmployee").getResultList();
	}

	/* ID指定で取得 */
	public MstEmployee get(int num) {
		return (MstEmployee)entityManager
				.createNamedQuery("from MstEmployee where id = " + num)
				.getSingleResult();
	}

	/*  */
	public List<MstEmployee> find(String fstr) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MstEmployee> query = builder.createQuery(MstEmployee.class);
		Root<MstEmployee> root = query.from(MstEmployee.class);
		query.select(root).where(builder.equal(root.get("name"), fstr));
		List<MstEmployee> list = null;
		list = (List<MstEmployee>) entityManager.createQuery(query).getResultList();
		return list;
	}
}
