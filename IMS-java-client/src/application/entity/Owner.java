package application.entity;

public class Owner extends Entity{
	private String companyName;
	private String notes;
	private String type;
	private String id;
	private String phone;
	private String address;
	private String email;
	private String website;
	private String name;

	public Owner(String companyname, String notes, String type, String id, String phone, String address, String email,
			String website, String name) {
		this.companyName = companyname;
		this.notes = notes;
		this.type = type;
		this.id = id;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.website = website;
		this.name = name;
	}

	public void setCompanyname(String companyname) {
		this.companyName = companyname;
	}

	public String getCompanyname() {
		return companyName;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWebsite() {
		return website;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	public String getJson(){
		return "{ \"address\": \""+getAddress()+"\",\"companyName\": \""+getCompanyname()+"\","+
				  "\"email\": \""+getEmail()+"\",\"id\": \""+getId()+"\",\"name\": \""+getName()+"\","+
				  "\"notes\": \""+getNotes()+"\",\"phone\":\""+getPhone()+"\",\"type\": \""+getType()+"\","+
				  "\"website\": \""+getWebsite()+"\" }";
	}


}