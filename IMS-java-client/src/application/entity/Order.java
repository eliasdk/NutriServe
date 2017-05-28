package application.entity;

import java.util.ArrayList;

public class Order extends Entity{
	private String id;
	private String dateReceived;
	private String number;
	private String type;
	private int total;
	private Shipment shipment;
	private Payment payment;
	private Customer customer;
	private boolean addedToStock;
	private String datePlaced;
	private ArrayList<PurchasedItemElement> purchasedItems;
	private int quantity;
	private boolean outstanding;

 	public Order(String id, String datereceived, String number, String type, int total, Shipment shipment,
			Payment payment, Customer customer, boolean addedtostock, String dateplaced,
			PurchasedItemElement purchaseditems, int quantity, boolean outstanding) {
		this.id = id;
		this.dateReceived = datereceived;
		this.number = number;
		this.type = type;
		this.total = total;
		this.shipment = shipment;
		this.payment = payment;
		this.customer = customer;
		this.addedToStock = addedtostock;
		this.datePlaced = dateplaced;
		this.purchasedItems  = new ArrayList<>();
		this.purchasedItems.add(purchaseditems);
		this.quantity = quantity;
		this.outstanding = outstanding;
	}

	public void setDatereceived(String datereceived) {
		this.dateReceived = datereceived;
	}

	public String getDatereceived() {
		return dateReceived;
	}

 	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

 	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

 	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

 	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Shipment getShipment() {
		return shipment;
	}

 	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Payment getPayment() {
		return payment;
	}

 	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

 	public void setAddedtostock(boolean addedtostock) {
		this.addedToStock = addedtostock;
	}

	public boolean getAddedtostock() {
		return addedToStock;
	}

 	public void setDateplaced(String dateplaced) {
		this.datePlaced = dateplaced;
	}

	public String getDateplaced() {
		return datePlaced;
	}

 	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

 	public void setOutstanding(boolean outstanding) {
		this.outstanding = outstanding;
	}

	public boolean getOutstanding() {
		return outstanding;
	}

 	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

 	public void setPurchaseditems(PurchasedItemElement purchaseditems) {
		this.purchasedItems.remove(0);
		this.purchasedItems.add(purchaseditems);
	}

	public PurchasedItemElement getPurchaseditems() {
		return purchasedItems.get(0);
	}

	public String getJson(){
		return"{\"addedToStock\":"+ getAddedtostock()+",\"customer\": "+getCustomer().getJson()+","+
			  "\"datePlaced\": \""+getDateplaced()+"\" ,\"dateReceived\": \""+getDatereceived()+"\","+
			  "\"dateplaced\": \""+getDateplaced()+"\",\"id\": \""+getId()+"\",\"number\": \""+getNumber()+"\","+
			  "\"outstanding\":"+ getOutstanding()+", \"payment\":"+getPayment().getJson() +","+
			  "\"purchasedItems\": ["+getPurchaseditems().getJson()+"],\"quantity\":" + getQuantity()+","+
			  "\"shipment\":"+getShipment().getJson()+",\"total\":" + getTotal() +",\"type\": \""+getType()+"\"}";
			}
}