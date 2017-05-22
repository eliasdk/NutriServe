package edu.bzu.soa.nutriserve.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import edu.bzu.soa.nutriserve.common.CommonHttp;
import edu.bzu.soa.nutriserve.common.HttpReturnData;
import edu.bzu.soa.nutriserve.model.Recipe;

public class RecipeService {
	
	List<Recipe> getRecipes () throws ClientProtocolException, IOException, JSONException {
		 HttpReturnData getRes = CommonHttp.getAPI ("http://localhost:9090/api/v1/employee") ;
		 System.out.println(getRes.getReturnContent());
		JSONObject resDetails = new JSONObject("{\"employees\":" + getRes.getReturnContent()+ "}");
 
		 
		return null;
		
	}

}
