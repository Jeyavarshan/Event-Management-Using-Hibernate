package Dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;
	private String serviceName;
	private double serviceCostPerDay;
	private double ServiceCostPerPerson;
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public double getServiceCostPerDay() {
		return serviceCostPerDay;
	}
	public void setServiceCostPerDay(double serviceCostPerDay) {
		this.serviceCostPerDay = serviceCostPerDay;
	}
	public double getServiceCostPerPerson() {
		return ServiceCostPerPerson;
	}
	public void setServiceCostPerPerson(double serviceCostPerPerson) {
		ServiceCostPerPerson = serviceCostPerPerson;
	}
	@Override
	public String toString() {
		return "service [serviceId=" + serviceId + ", serviceName=" + serviceName + ", serviceCostPerDay="
				+ serviceCostPerDay + ", ServiceCostPerPerson=" + ServiceCostPerPerson + "]";
	}
	
	
	
	
	

}
