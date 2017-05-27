package application.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.entity.Customer;
import application.entity.Employee;
import application.entity.Order;
import application.entity.Owner;
import application.entity.Payment;
import application.entity.Product;
import application.entity.PurchasedItemElement;
import application.entity.Shipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GETConnection {
	private HttpClient client;
	private String URI;

	public GETConnection() {
		client = HttpClientBuilder.create().build();
		URI = "http://31.168.143.199:8080/inventory/api/";
	}

	private String getJSON(String param) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(URI + param);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "", json = "";
		while ((line = rd.readLine()) != null)
			json += line + "\n";
		return json;
	}

	public ObservableList<Shipment> getAllShipments() {
		String json;
		ObservableList<Shipment> list = FXCollections.observableArrayList();
		try {
			json = getJSON("shipment");
			JSONArray jArray = new JSONArray(json);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				ObjectMapper m = new ObjectMapper();
				JsonNode root = m.readTree(json_data.toString());
				list.add(getShipment(root));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public ObservableList<Product> getAllProducts() {
		String json;
		ObservableList<Product> list = FXCollections.observableArrayList();
		try {
			json = getJSON("product");
			JSONArray jArray = new JSONArray(json);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				ObjectMapper m = new ObjectMapper();
				JsonNode root = m.readTree(json_data.toString());
				list.add(getProduct(root));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public ObservableList<Payment> getAllPayments() {
		String json;
		ObservableList<Payment> list = FXCollections.observableArrayList();
		try {
			json = getJSON("payment");
			JSONArray jArray = new JSONArray(json);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				ObjectMapper m = new ObjectMapper();
				JsonNode root = m.readTree(json_data.toString());
				list.add(getPayment(root));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public ObservableList<Employee> getAllEmployees() {
		String json;
		ObservableList<Employee> list = FXCollections.observableArrayList();
		try {
			json = getJSON("employee");
			JSONArray jArray = new JSONArray(json);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				ObjectMapper m = new ObjectMapper();
				JsonNode root = m.readTree(json_data.toString());
				list.add(getEmployee(root));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public ObservableList<Customer> getAllCustomers() {
		String json;
		ObservableList<Customer> list = FXCollections.observableArrayList();
		try {
			json = getJSON("customer");
			JSONArray jArray = new JSONArray(json);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				ObjectMapper m = new ObjectMapper();
				JsonNode root = m.readTree(json_data.toString());
				list.add(getCustomer(root));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public ObservableList<Order> getAllOrders() {
		String json;
		ObservableList<Order> list = FXCollections.observableArrayList();
		try {
			json = getJSON("order");
			JSONArray jArray = new JSONArray(json);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				ObjectMapper m = new ObjectMapper();
				JsonNode root = m.readTree(json_data.toString());
				list.add(getOrder(root));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
		return list;
	}
	public ObservableList<Owner> getAllOwners() {
		String json;
		ObservableList<Owner> list = FXCollections.observableArrayList();
		try {
			json = getJSON("shipment");
			JSONArray jArray = new JSONArray(json);
			for (int i = 0; i < jArray.length(); i++) {

				JSONObject json_data = jArray.getJSONObject(i);
				ObjectMapper m = new ObjectMapper();
				JsonNode root = m.readTree(json_data.toString());
				list.add(getOwner(root.get("owner")));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
		return list;
	}


	public ObservableList<PurchasedItemElement> getAllItems() {
		String json;
		ObservableList<PurchasedItemElement> list = FXCollections.observableArrayList();
		try {
			json = getJSON("item");
			JSONArray jArray = new JSONArray(json);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				ObjectMapper m = new ObjectMapper();
				JsonNode root = m.readTree(json_data.toString());
				list.add(getPurchasedItemElement(root));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	private Order getOrder(JsonNode order) throws JsonParseException, JsonMappingException, IOException {
		JSONArray jArray = new JSONArray(order.get("purchasedItems").toString());
		ArrayList<PurchasedItemElement> list = new ArrayList<PurchasedItemElement>();
		for (int i = 0; i < jArray.length(); i++) {
			ObjectMapper m = new ObjectMapper();
			JsonNode root = m.readTree(jArray.getJSONObject(i).toString());
			list.add(getPurchasedItemElement(root));
		}
		return new Order(order.path("id").asText(), order.path("dateReceived").asText(), order.path("number").asText(),
				order.path("type").asText(), order.path("total").asInt(), getShipment(order.get("shipment")),
				getPayment(order.get("payment")), getCustomer(order.get("customer")),
				order.path("addedToStock").asBoolean(), order.path("dateplaced").asText(), list.get(0),
				order.path("quantity").asInt(), order.path("outstanding").asBoolean());
	}

	private Shipment getShipment(JsonNode shipment) throws JsonParseException, JsonMappingException, IOException {
		return new Shipment(shipment.path("shipDate").asText(), shipment.path("type").asText(),
				getOwner(shipment.get("owner")), shipment.path("id").asText(), shipment.path("address").asText());
	}

	private PurchasedItemElement getPurchasedItemElement(JsonNode purchItem)
			throws JsonParseException, JsonMappingException, IOException {
		return new PurchasedItemElement(purchItem.path("quantity").asInt(), getProduct(purchItem.get("product")),
				purchItem.path("type").asText(), purchItem.path("purchasePrice").asInt(),
				purchItem.path("sellingPrice").asInt(), purchItem.path("unit").asText(), purchItem.path("id").asText(),
				purchItem.path("rate").asText(), purchItem.path("status").asText(), purchItem.path("name").asText(),
				purchItem.path("description").asText());
	}

	private Customer getCustomer(JsonNode customer) throws JsonParseException, JsonMappingException, IOException {
		return new Customer(customer.path("type").asText(), customer.path("currency").asText(),
				customer.path("customerBirthDate").asText(), customer.path("customerLastName").asText(),
				customer.path("customerFirstName").asText(), getPayment(customer.get("paymentInfo")),
				customer.path("id").asText(), customer.path("accountBalance").asInt(), customer.path("email").asText());
	}

	private Owner getOwner(JsonNode owner) throws JsonParseException, JsonMappingException, IOException {
		return new Owner(owner.path("companyName").asText(), owner.path("notes").asText(), owner.path("type").asText(),
				owner.path("id").asText(), owner.path("phone").asText(), owner.path("address").asText(),
				owner.path("email").asText(), owner.path("website").asText(), owner.path("name").asText());

	}

	private Payment getPayment(JsonNode payment) throws JsonParseException, JsonMappingException, IOException {
		return new Payment(payment.path("card").asText(), payment.path("state").asInt(),
				payment.path("method").asText(), payment.path("type").asText(), payment.path("createtime").asText(),
				payment.path("id").asText(), payment.path("updatetime").asText());
	}

	private Employee getEmployee(JsonNode employee) throws JsonParseException, JsonMappingException, IOException {
		return new Employee(employee.path("companyName").asText(), employee.path("notes").asText(),
				employee.path("type").asText(), employee.path("id").asText(), employee.path("phone").asText(),
				employee.path("address").asText(), employee.path("email").asText(), employee.path("website").asText(),
				employee.path("name").asText());
	}

	private Product getProduct(JsonNode product) throws JsonParseException, JsonMappingException, IOException {
		return new Product(product.path("type").asText(), product.path("supplier").asText(),
				product.path("id").asText(), product.path("category").asText(), product.path("guaranteeInfo").asText());
	}

}
