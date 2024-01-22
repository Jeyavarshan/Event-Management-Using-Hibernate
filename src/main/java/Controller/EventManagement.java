package Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Dto.EventType;
import Dto.admin;
import Dto.client;
import Dto.clientEvent;
import Dto.clientService;
import Dto.service;
import dao.AdminDao;
import dao.clientDao;
import dao.clientEventDao;
import dao.clientServiceDao;
import dao.serviceDao;

public class EventManagement {
	AdminDao adao = new AdminDao();
	serviceDao sdao = new serviceDao();
	clientDao cdao = new clientDao();
	clientEventDao cedao = new clientEventDao();
	clientServiceDao csdao = new clientServiceDao();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("tenma");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public static void main(String[] args) {
		
		EventManagement em = new EventManagement();
		System.out.println(em.createClientServices());
		
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
	
	public List<service> getService(){
		System.out.println("Enter Credendiatls to Proceed");
		Query query = em.createQuery("select s from service s ");
		List<service>s = (List<service>) query.getResultList();
		return s;
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
	public service deleteService() {
		Scanner sc = new Scanner(System.in);
		admin exadmin = LoginAdmin();
		if(exadmin != null) {
			List<service> services = exadmin.getService();
			for(service serv : services) 
			{
				System.out.println("Service Id  " + "Service Name  " + "Cost Per Person  " + "Cost Per Day  ");
				System.out.println("  "+serv.getServiceId() + "  " +serv.getServiceName()+"  "+serv.getServiceCostPerPerson()+"  "+serv.getServiceCostPerDay());
			}
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Choose Service Id %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			int id = sc.nextInt();
			service tobedeleted = sdao.findService(id);
			services.remove(tobedeleted);
			exadmin.setService(services);
			adao.updateAdmin(exadmin, exadmin.getId());
			return tobedeleted;
			
			
		}
		return null;
		
	}
	
	public client saveClient()
	{
		client client = new client();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Client Name: ");
		client.setClientName(sc.next());
		System.out.print("Enter client Mail: ");
		client.setClientMail(sc.next());
		System.out.print("Enter Admin Contact Number: ");
		client.setClientCon(sc.nextLong());
		
		return cdao.saveClient(client);
	}
	
	public client clientLogin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter client Email");
		String Email = sc.next();
		Query query = em.createQuery("select a from client a where a.clientMail=?1");
		query.setParameter(1, Email);
		client exclient = (client) query.getSingleResult();
		if(exclient != null) {
			if(exclient.getClientMail().equals(Email)) {
				return exclient;
			}return null;
			
		}return null;
		
	}
	
	public String createClientEvent() 
	{	
		
		clientEvent ce = new clientEvent();
		client client = clientLogin();
		
		if(client!=null) {
		ce.setClient(client);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Client Event Name");
		ce.setClientEventName(sc.nextLine());
		System.out.println("Enter client Event Location");
		ce.setClientEventLocation(sc.next());
		System.out.println("Start Date");
		ce.setStartDate(LocalDate.now());
		System.out.println("NO of Days");
		ce.setClientEventNoOfDays(sc.nextInt());
	
		
		System.out.println("Enter 1 for Marriage");
		System.out.println("Enter 2 for Engagement");
		System.out.println("Enter 3 for Brithday");
		System.out.println("Enter 4 for Aniversary");
		System.out.println("Enter 5 for BabyShower");
		System.out.println("Enter 6 for Reunion");
		System.out.println("Enter 7 for MarriageCeremony");
		System.out.println("Enter 8 for BachlorParty");
		System.out.println("Enter 9 for DeathCeremony");
		
		int num = sc.nextInt();
		
		if(num == 0)
			ce.setEt(EventType.Marriage);
		else if(num == 1)
			ce.setEt(EventType.Engagement);
		else if(num == 2)
			ce.setEt(EventType.Brithday);
		else if(num == 3)
			ce.setEt(EventType.Aniversary);
		else if(num == 4)
			ce.setEt(EventType.BabyShower);
		else if(num == 5)
			ce.setEt(EventType.Reunion);
		else if(num == 6)
			ce.setEt(EventType.MarriageCeremony);
		else if(num == 7)
			ce.setEt(EventType.BachlorParty);
		else if(num == 8)
			ce.setEt(EventType.DeathCeremony);
		
		
			List<clientEvent> clientEve = client.getClientEvent();
			clientEve.add(ce);
			client.setClientEvent(clientEve);
			ce.setClient(client);
			client c = cdao.updateClient(client, client.getClientID());
			if(c!= null) {
				return "client Event Added";
			}
			
		}
		return null;
		
	}
	
	public String createClientServices() {
		client client = clientLogin();
		
		if(client!= null) {
			List <clientEvent> ce = client.getClientEvent();
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			for(clientEvent cli :ce ) {
				if(id == cli.getClientEventId()) {
					double eventcost = cli.getClientEventCost();
					List<clientService> cs = cli.getClientService();
					int noOfServices = sc.nextInt();
					for(int i=1 ; i<=noOfServices;i++) {
						clientService cs1 = new clientService();
						List<service> listOfService = getService();
						for(service serv : listOfService) {
							System.out.println(listOfService);
						}
						System.out.println("Enter Service id");
						int servvalue =sc.nextInt();
						
						service s = sdao.findService(servvalue);
						cs1.setClientServiceName(s.getServiceName());
						cs1.setClientServiceNoOfDays(cli.getClientEventNoOfDays());
						cs1.setClientServiceCostperPerson(s.getServiceCostPerPerson());
						cs1.setClientServiceCost(cs1.getClientServiceCostperPerson()*cs1.getClientServiceNoOfDays());
						eventcost = eventcost+cs1.getClientServiceCost();
						cs.add(cs1);
						
						clientService cs2 = csdao.saveClientService(cs1);
						
					}
					cli.setClientEventCost(eventcost);
					cli.setClientService(cs);
					clientEvent ce2 = cedao.updateClientEvent(cli, cli.getClientEventId());
					
					if(ce2 != null) {
						return "client Service Added";
					}
					
				}
			}
			
		}
		return null;
		
	}
	
	
	
}
