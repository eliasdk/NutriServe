package application.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import application.entity.Customer;
import application.entity.Employee;
import application.entity.Entity;
import application.entity.Order;
import application.entity.Owner;
import application.entity.Payment;
import application.entity.Product;
import application.entity.Shipment;

public class PostConnection {
	public PostConnection(Entity entity){
		postEntity(entity);
	}
	private void postEntity(Entity entity){
		HttpClient client = HttpClientBuilder.create().build();
		String URI = "http://31.168.143.199:8080/inventory/api/";
		  try {
			  String parameter;
			  if(entity instanceof Shipment)
				  parameter = "shipment";
			  else if(entity instanceof Product)
				  parameter = "product";
			  else if( entity instanceof Payment)
				  parameter = "payment";
			  else if(entity instanceof Employee)
				  parameter = "employee";
			  else if(entity instanceof Customer)
				  parameter = "customer";
			  else if(entity instanceof Order)
				  parameter = "order";
			  else if(entity instanceof Owner)
				  parameter = "owner";
			  else
				  parameter = "item";
				HttpPost postRequest = new HttpPost(URI+parameter);

				StringEntity input = new StringEntity(entity.getJson());
				input.setContentType("application/json");
				postRequest.setEntity(input);

				HttpResponse response = client.execute(postRequest);

				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				}

				BufferedReader br = new BufferedReader(
		                        new InputStreamReader((response.getEntity().getContent())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				//client.getConnectionManager().shutdown();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }

	}

}
