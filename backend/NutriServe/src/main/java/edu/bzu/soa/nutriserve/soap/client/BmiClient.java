package edu.bzu.soa.nutriserve.soap.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import bmiservice.wsdl.GetBmiValue;
import bmiservice.wsdl.GetBmiValueResponse;

public class BmiClient extends WebServiceGatewaySupport {
 

	public GetBmiValueResponse getBmi(double h, double w) {

		GetBmiValue request = new GetBmiValue();
		request.setH(h);
		request.setW(w);
 

		GetBmiValueResponse response = (GetBmiValueResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.beetledev.com/webservices/bmiservice.asmx",
						request,
						new SoapActionCallback("http://www.beetledev.com/getBmiValue"));

		return response;
	}

}