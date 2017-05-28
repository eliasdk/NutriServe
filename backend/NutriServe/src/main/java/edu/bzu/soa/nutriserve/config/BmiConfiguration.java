package edu.bzu.soa.nutriserve.config;
 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import edu.bzu.soa.nutriserve.soap.client.BmiClient;

@Configuration
public class BmiConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("bmiservice.wsdl");
		return marshaller;
	}

	@Bean
	public BmiClient bmiClient(Jaxb2Marshaller marshaller) {
		BmiClient client = new BmiClient();
		client.setDefaultUri("http://www.beetledev.com/webservices/bmiservice.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}