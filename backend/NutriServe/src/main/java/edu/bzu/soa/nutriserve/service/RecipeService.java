package edu.bzu.soa.nutriserve.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
 
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.bzu.soa.nutriserve.common.CommonHttp;
import edu.bzu.soa.nutriserve.common.RecipesData;
import edu.bzu.soa.nutriserve.common.HttpReturnData;
import edu.bzu.soa.nutriserve.model.Recipe;
@Component
public class RecipeService {

	@Value("${EDAMAM_APP_KEY}")
	private String EDammamAppKey ="370290f0badaac18891e791a65afc639" ;
	
	@Value("${EDAMAM_APP_ID}")
	private String EDammamAppId ="6256e308";
	
	public String getEDammamAppKey() {
		return EDammamAppKey ;
	}

	public void setEDammamAppKey(String eDammamAppKey) {
		EDammamAppKey = eDammamAppKey;
	}

	public String getEDammamAppId() {
		return EDammamAppId;
	}

	public void setEDammamAppId(String eDammamAppId) {
		EDammamAppId = eDammamAppId;
	}

	public List<Recipe> getRecipes(String q ) throws Exception {
	 
		
		
		String url = "https://api.edamam.com/search?q=" + q + "&app_id="+ EDammamAppId+"&app_key="+EDammamAppKey+"&from=0&to=3&calories=gte%20591,%20lte%20722&health=alcohol-free";
		//System.out.println(url);
		HttpReturnData getRes = CommonHttp.getAPI(url);
		//System.out.println(getRes.getReturnContent());
		JSONObject resDetails = new JSONObject( getRes.getReturnContent() );
        for (int i=0; i<1; i++ ) {
        	Recipe recipe = new Recipe ();
        	recipe.setCalories((int) resDetails.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getDouble("calories"));
        	recipe.setCuisine("Westren");
        	recipe.setDescription(resDetails.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getString("label"));
        	recipe.setId(i);
        	recipe.setImageUrl(resDetails.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getString("image"));
        	recipe.setName(resDetails.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getString("source") + resDetails.getString("q"));
        	recipe.setRecipeurl(resDetails.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getString("uri"));
    	    
        	
        	RecipesData.recipes.add(recipe);
        	RecipesData.addIds();
        }
		 
 
		return RecipesData.recipes;

	}

}
