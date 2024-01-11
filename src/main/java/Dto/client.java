package Dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientID;
	private String clientName;
	private long clientCon;
	private String clientMail;
	@OneToMany(cascade = CascadeType.ALL)
	private List<clientEvent> clientEvent;
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public long getClientCon() {
		return clientCon;
	}
	public void setClientCon(long clientCon) {
		this.clientCon = clientCon;
	}
	public String getClientMail() {
		return clientMail;
	}
	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}
	public List<clientEvent> getClientEvent() {
		return clientEvent;
	}
	public void setClientEvent(List<clientEvent> clientEvent) {
		this.clientEvent = clientEvent;
	}
	@Override
	public String toString() {
		return "client [clientID=" + clientID + ", clientName=" + clientName + ", clientCon=" + clientCon
				+ ", clientMail=" + clientMail + "]";
	}
	
	
	

}
