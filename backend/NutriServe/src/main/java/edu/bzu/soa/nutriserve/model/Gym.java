package edu.bzu.soa.nutriserve.model;

public class Gym {
	int id;
	String name;
	String address;
	String location;
	String description;
	
	String nextGymApi;
	public String getNextGymApi() {
		return nextGymApi;
	}
	public void setNextGymApi(String nextGymApi) {
		this.nextGymApi = nextGymApi;
	}
	public String getPreviousGymApi() {
		return previousGymApi;
	}
	public void setPreviousGymApi(String previousGymApi) {
		this.previousGymApi = previousGymApi;
	}
	String previousGymApi;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
