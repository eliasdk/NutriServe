package edu.bzu.soa.nutriserve.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmiservice.wsdl.GetBmiValueResponse;
import edu.bzu.soa.nutriserve.config.BmiConfiguration;
import edu.bzu.soa.nutriserve.model.Bmi;
import edu.bzu.soa.nutriserve.model.Plan;
import edu.bzu.soa.nutriserve.soap.client.BmiClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bmi")
@Api(value = "NutriServe daily plan", description = "User daily food plan by NutriServe online service")
public class BmiController {

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get user Daily diet plan including recipes and newarby gyms", response = Plan.class)
	Bmi getBmi(@RequestParam("weight") float weight, @RequestParam("length") float length) {
		
		BmiConfiguration conf = new BmiConfiguration();
		
		BmiClient soapClient = conf.bmiClient(conf.marshaller());
		GetBmiValueResponse soapRes = soapClient.getBmi((double) length, (double)weight);
		
		 
		 
		 Bmi bmi = new Bmi(weight, length);
		 bmi.setBmi((float) soapRes.getGetBmiValueResult());
		 System.out.println("=========== BMI = " + soapRes.getGetBmiValueResult());
		 return bmi;

	}
	 
}
