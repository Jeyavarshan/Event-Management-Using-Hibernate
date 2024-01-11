package Dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class clientEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientEventId;
	private String clientEventName;
	private LocalDate startDate;
	private int clientEventNoOfDays;
	private String clientEventLocation;
	private double clientEventCost;
	@ManyToOne(cascade = CascadeType.ALL )
	private client client;
	@OneToMany(cascade = CascadeType.ALL )
	private List<clientService> clientService;
	private EventType et;
	public int getClientEventId() {
		return clientEventId;
	}
	public void setClientEventId(int clientEventId) {
		this.clientEventId = clientEventId;
	}
	public String getClientEventName() {
		return clientEventName;
	}
	public void setClientEventName(String clientEventName) {
		this.clientEventName = clientEventName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public int getClientEventNoOfDays() {
		return clientEventNoOfDays;
	}
	public void setClientEventNoOfDays(int clientEventNoOfDays) {
		this.clientEventNoOfDays = clientEventNoOfDays;
	}
	public String getClientEventLocation() {
		return clientEventLocation;
	}
	public void setClientEventLocation(String clientEventLocation) {
		this.clientEventLocation = clientEventLocation;
	}
	public double getClientEventCost() {
		return clientEventCost;
	}
	public void setClientEventCost(double clientEventCost) {
		this.clientEventCost = clientEventCost;
	}
	public client getClient() {
		return client;
	}
	public void setClient(client client) {
		this.client = client;
	}
	public List<clientService> getClientService() {
		return clientService;
	}
	public void setClientService(List<clientService> clientService) {
		this.clientService = clientService;
	}
	public EventType getEt() {
		return et;
	}
	public void setEt(EventType et) {
		this.et = et;
	}
	@Override
	public String toString() {
		return "clientEvent [clientEventId=" + clientEventId + ", clientEventName=" + clientEventName + ", startDate="
				+ startDate + ", clientEventNoOfDays=" + clientEventNoOfDays + ", clientEventLocation="
				+ clientEventLocation + ", clientEventCost=" + clientEventCost + "]";
	}
	
	
	
	
	
}
