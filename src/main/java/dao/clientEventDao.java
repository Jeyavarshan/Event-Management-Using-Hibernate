package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import Dto.clientEvent;



public class clientEventDao {
	
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("tenma").createEntityManager();
	}
	
	public clientEvent saveclientEvent(clientEvent ce)
	{
		EntityManager em = getEm();
		em.getTransaction().begin();
		em.persist(ce);
		em.getTransaction().commit();
		return ce;
	}
	
	public clientEvent findClientEvent(int id)
	{
		EntityManager em = getEm();
		clientEvent cle = em.find(clientEvent.class, id);
		if(cle != null)
		{
			return cle;
		}
		else
		{
			return null;
		}
	}
	
	public clientEvent deleteClientEvent(int id)
	{
		EntityManager em = getEm();
		clientEvent ce = em.find(clientEvent.class, id);
		if(ce != null)
		{
			em.getTransaction().begin();
			em.remove(ce);
			em.getTransaction().commit();
			return ce;
		}
		return null;
	}
	
	public clientEvent updateClientEvent(clientEvent clientE, int id)
	{
		EntityManager em = getEm();
		clientEvent exClientEvent = em.find(clientEvent.class, id);
		if(exClientEvent != null)
		{
			clientE.setClientEventId(id);
			em.getTransaction().begin();
			clientEvent ad = em.merge(clientE);
			em.getTransaction().commit();
			return ad;
		}
		return null;
	}


}
