package edu.bzu.soa.nutriserve.service;

import java.util.List;

import org.json.JSONObject;

import edu.bzu.soa.nutriserve.common.CommonHttp;
import edu.bzu.soa.nutriserve.common.GymsData;
import edu.bzu.soa.nutriserve.common.HttpReturnData;
import edu.bzu.soa.nutriserve.common.RecipesData;
import edu.bzu.soa.nutriserve.model.Gym;
import edu.bzu.soa.nutriserve.model.Recipe;

public class GymService {
	//
	public List<Gym> getGyms(String location ) throws Exception {

		
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=31.9037641,35.2034184&radius=5000&type=gym&key=AIzaSyCxhU9nFW7jnpcpVzJ-7SNUymGWXym833U";
 
		//System.out.println(url);
		HttpReturnData getRes = CommonHttp.getAPI(url);
		//System.out.println(getRes.getReturnContent());
		JSONObject resDetails = new JSONObject( getRes.getReturnContent() );
        for (int i=0; i<resDetails.getJSONArray("results").length(); i++ ) {
        	Gym gym = new Gym ();
        	  
        	
        	
        	gym.setAddress(resDetails.getJSONArray("results").getJSONObject(i).getString("vicinity"));
        	try {
        	gym.setRating(resDetails.getJSONArray("results").getJSONObject(i).getDouble("rating"));
        	}
        	catch (Exception e) {
        		gym.setRating(0);	
        	}
        	gym.setLocation(resDetails.getJSONArray("results").getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat") +"," + resDetails.getJSONArray("results").getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng") );
        	gym.setId(1);
        	gym.setName(resDetails.getJSONArray("results").getJSONObject(i).getString("name"));
        	gym.setNextGymApi("");
        	gym.setPreviousGymApi("");
        	
        	 
        	GymsData.gyms.add(gym);
        	GymsData.addIds();
        }
		 
 
		return GymsData.gyms;

	
	}
}
