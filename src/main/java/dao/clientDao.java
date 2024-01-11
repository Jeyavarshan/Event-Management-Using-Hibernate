package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import Dto.client;

public class clientDao {
	
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("tenma").createEntityManager();
	}
	
	public client saveClient(client client)
	{
		EntityManager em = getEm();
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		return client;
	}
	
	public client findClient(int id)
	{
		EntityManager em = getEm();
		client c = em.find(client.class, id);
		if(c != null)
		{
			return c;
		}
		else
		{
			return null;
		}
	}
	
	public client deleteClient(int id)
	{
		EntityManager em = getEm();
		client c = em.find(client.class, id);
		if(c != null)
		{
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return c;
		}
		return null;
	}
	
	public client updateClient(client client, int id)
	{
		EntityManager em = getEm();
		client exAadhar = em.find(client.class, id);
		if(exAadhar != null)
		{
			client.setClientID(id);
			em.getTransaction().begin();
			client cli = em.merge(client);
			em.getTransaction().commit();
			return cli;
		}
		return null;
	}


}
