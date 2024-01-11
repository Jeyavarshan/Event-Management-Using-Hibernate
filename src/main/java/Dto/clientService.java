package Dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class clientService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientServiceId;
	private String clientServiceName;
	private double clientServiceCost;
	private int clientServiceNoOfDays;
	private double clientServiceCostperPerson;
	
	public int getClientServiceId() {
		return clientServiceId;
	}
	public void setClientServiceId(int clientServiceId) {
		this.clientServiceId = clientServiceId;
	}
	public String getClientServiceName() {
		return clientServiceName;
	}
	public void setClientServiceName(String clientServiceName) {
		this.clientServiceName = clientServiceName;
	}
	public double getClientServiceCost() {
		return clientServiceCost;
	}
	public void setClientServiceCost(double clientServiceCost) {
		this.clientServiceCost = clientServiceCost;
	}
	public int getClientServiceNoOfDays() {
		return clientServiceNoOfDays;
	}
	public void setClientServiceNoOfDays(int clientServiceNoOfDays) {
		this.clientServiceNoOfDays = clientServiceNoOfDays;
	}
	public double getClientServiceCostperPerson() {
		return clientServiceCostperPerson;
	}
	public void setClientServiceCostperPerson(double clientServiceCostperPerson) {
		this.clientServiceCostperPerson = clientServiceCostperPerson;
	}
	
	@Override
	public String toString() {
		return "clientService [clientServiceId=" + clientServiceId + ", clientServiceName=" + clientServiceName
				+ ", clientServiceCost=" + clientServiceCost + ", clientServiceNoOfDays=" + clientServiceNoOfDays
				+ ", clientServiceCostperPerson=" + clientServiceCostperPerson + "]";
	}
	
	
	

}
