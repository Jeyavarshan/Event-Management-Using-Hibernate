package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import Dto.service;


public class serviceDao {
	
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("tenma").createEntityManager();
	}
	
	public service saveService(service s)
	{
		EntityManager em = getEm();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		return s;
	}
	
	public service findService(int id)
	{
		EntityManager em = getEm();
		service s = em.find(service.class, id);
		if(s != null)
		{
			return s;
		}
		else
		{
			return null;
		}
	}
	
	public service deleteService(int id)
	{
		EntityManager em = getEm();
		service s = em.find(service.class, id);
		if(s != null)
		{
			em.getTransaction().begin();
			em.remove(s);
			em.getTransaction().commit();
			return s;
		}
		return null;
	}
	
	public service updateService(service s, int id)
	{
		EntityManager em = getEm();
		service exService = em.find(service.class, id);
		if(exService != null)
		{
			s.setServiceId(id);
			em.getTransaction().begin();
			service ad = em.merge(s);
			em.getTransaction().commit();
			return ad;
		}
		return null;
	}


}
