package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import Dto.admin;


public class AdminDao {
	
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("tenma").createEntityManager();
	}
	
	public admin saveAdmin(admin admin)
	{
		EntityManager em = getEm();
		em.getTransaction().begin();
		em.persist(admin);
		em.getTransaction().commit();
		return admin;
	}
	
	public admin findAdmin(int id)
	{
		EntityManager em = getEm();
		admin a = em.find(admin.class, id);
		if(a != null)
		{
			return a;
		}
		else
		{
			return null;
		}
	}
	
	public admin deleteAdmin(int id)
	{
		EntityManager em = getEm();
		admin a = em.find(admin.class, id);
		if(a != null)
		{
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();
			return a;
		}
		return null;
	}
	
	public admin updateAdmin(admin admin, int id)
	{
		EntityManager em = getEm();
		admin exAadhar = em.find(admin.class, id);
		if(exAadhar != null)
		{
			admin.setId(id);
			em.getTransaction().begin();
			admin ad = em.merge(admin);
			em.getTransaction().commit();
			return ad;
		}
		return null;
	}

}
