package Controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Dto.admin;
import Dto.clientEvent;
import Dto.service;
import dao.AdminDao;
import dao.clientDao;
import dao.clientServiceDao;
import dao.serviceDao;

public class EventManagement {
	AdminDao adao = new AdminDao();
	serviceDao sdao = new serviceDao();
	clientDao cdao = new clientDao();
	clientEvent cedao = new clientEvent();
	clientServiceDao csdao = new clientServiceDao();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("tenma");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public static void main(String[] args) {
		
		EventManagement em = new EventManagement();
		System.out.println(em.UpdateService());
		
	}
	
	public admin SaveAdmin() 
	{
		admin admin = new admin();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Admin Name: ");
		admin.setName(sc.next());
		System.out.print("Enter Admin Mail: ");
		admin.setEmail(sc.next());
		System.out.print("Enter Admin Password: ");
		admin.setPassword(sc.next());
		System.out.print("Enter Admin Contact Number: ");
		admin.setContact(sc.nextLong());
		
		return adao.saveAdmin(admin);
	}
	
	public admin LoginAdmin() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Admin Email");
		String Email = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		
		Query query = em.createQuery("select a from admin a where a.email=?1");
		query.setParameter(1, Email);
		admin exadmin = (admin) query.getSingleResult();
		if(exadmin != null) {
			if(exadmin.getPassword().equals(password)) {
				return exadmin;
			}return null;
			
		}return null;
		
	}
	
	public service saveService() {
		admin exAdmin = LoginAdmin();
		if(exAdmin != null) {
			service service = new service();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Service Name");
			service.setServiceName(sc.next());
			System.out.println("Enter Service Cost Per Person");
			service.setServiceCostPerPerson(sc.nextDouble());
			System.out.println("Enter Service Cost Per Day");
			service.setServiceCostPerDay(sc.nextDouble());
			service savedService = sdao.saveService(service);
			exAdmin.getService().add(savedService);
			adao.updateAdmin(exAdmin, exAdmin.getId());
			return service;
		}
		return null;
		
		
	}
	
	public List<service> getAllService(){
		System.out.println("Enter Credendiatls to Proceed");
		admin exadmin = LoginAdmin();
		if(exadmin != null) {
		Query query = em.createQuery("select s from service s ");
		List<service>s = (List<service>) query.getResultList();
		return s;
					
			
		}
		return null;
	}
	
	public String UpdateService() {
		Scanner sc = new Scanner(System.in);
		List<service> services = getAllService();
		for(service serv : services) {
			System.out.println("Service Id  " + "Service Name  " + "Cost Per Person  " + "Cost Per Day  ");
			System.out.println("  "+serv.getServiceId() + "  " +serv.getServiceName()+"  "+serv.getServiceCostPerPerson()+"  "+serv.getServiceCostPerDay());
		}
		
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% choose Service Id %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		int id = sc.nextInt();
		service updatedService = sdao.findService(id);
		System.out.println("Enter  Updated Cost per day");
		double UpdatedCpd = sc.nextDouble();
		System.out.println("Rnter Updated cost per Person");
		double updatedCpp = sc.nextDouble();
		
		updatedService.setServiceCostPerDay(UpdatedCpd);
		updatedService.setServiceCostPerPerson(updatedCpp);
		
		service UpdatedService = sdao.updateService(updatedService, id);
		if(UpdatedService!= null) {
			return "service Updated Succcessfully";
			
		}
		else {
			return "Service not updated";
		}
	}
	
}
