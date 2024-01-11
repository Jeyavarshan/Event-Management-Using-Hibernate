package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import Dto.clientService;


public class clientServiceDao {
	
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("tenma").createEntityManager();
	}
	
	public clientService saveClientService(clientService cs)
	{
		EntityManager em = getEm();
		em.getTransaction().begin();
		em.persist(cs);
		em.getTransaction().commit();
		return cs;
	}
	
	public clientService findClientService(int id)
	{
		EntityManager em = getEm();
		clientService cls = em.find(clientService.class, id);
		if(cls != null)
		{
			return cls;
		}
		else
		{
			return null;
		}
	}
	
	public clientService deleteClientService(int id)
	{
		EntityManager em = getEm();
		clientService cs = em.find(clientService.class, id);
		if(cs != null)
		{
			em.getTransaction().begin();
			em.remove(cs);
			em.getTransaction().commit();
			return cs;
		}
		return null;
	}
	
	public clientService updateClientService(clientService clientS, int id)
	{
		EntityManager em = getEm();
		clientService exClientService = em.find(clientService.class, id);
		if(exClientService != null)
		{
			clientS.setClientServiceId(id);
			em.getTransaction().begin();
			clientService ad = em.merge(clientS);
			em.getTransaction().commit();
			return ad;
		}
		return null;
	}

}
