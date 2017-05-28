package application.entity;
public class Customer extends Entity{
	private String type;
	private String currency;
	private String customerBirthDate;
	private String customerLastName;
	private String customerFirstName;
	private Payment paymentInfo;
	private String id;
	private int accountBalance;
	private String email;

	public Customer(String type, String currency, String customerbirthdate, String customerlastname,
			String customerfirstname, Payment paymentinfo, String id, int accountbalance, String email) {
		this.type = type;
		this.currency = currency;
		this.customerBirthDate = customerbirthdate;
		this.customerLastName = customerlastname;
		this.customerFirstName = customerfirstname;
		this.paymentInfo = paymentinfo;
		this.id = id;
		this.accountBalance = accountbalance;
		this.email = email;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCustomerbirthdate(String customerbirthdate) {
		this.customerBirthDate = customerbirthdate;
	}

	public String getCustomerbirthdate() {
		return customerBirthDate;
	}

	public void setCustomerlastname(String customerlastname) {
		this.customerLastName = customerlastname;
	}

	public String getCustomerlastname() {
		return customerLastName;
	}

	public void setCustomerfirstname(String customerfirstname) {
		this.customerFirstName = customerfirstname;
	}

	public String getCustomerfirstname() {
		return customerFirstName;
	}

	public void setPaymentinfo(Payment paymentinfo) {
		this.paymentInfo = paymentinfo;
	}

	public Payment getPaymentinfo() {
		return paymentInfo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAccountbalance(int accountbalance) {
		this.accountBalance = accountbalance;
	}

	public int getAccountbalance() {
		return accountBalance;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return customerFirstName;
	}
	public String getJson(){
		return"{\"accountBalance\":"+ getAccountbalance() +",\"currency\":\""+ getCurrency()+"\","+
				"\"customerBirthDate\":\""+ getCustomerbirthdate()+"\",\"customerFirstName\":\""+ getCustomerfirstname()+"\","+
				"\"customerLastName\":\""+ getCustomerlastname()+"\",\"email\":\""+ getEmail() +"\",\"id\":\""+getId()+"\","+
				"\"paymentInfo\":"+getPaymentinfo().getJson() +",\"type\":\""+ getType()+ "\"}";
	}
}