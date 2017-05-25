package edu.bzu.soa.nutriserve.common;

import java.util.ArrayList;
import java.util.List;

import edu.bzu.soa.nutriserve.model.Gym;

public class GymsData {
	
	public static List<Gym> gyms = new ArrayList<Gym>();

	public static void addIds() {
		for (int i = 0; i < gyms.size(); i++) {
			gyms.get(i).setId(i);
			if (i == 0) {
				gyms.get(i).setPreviousGymApi("");
				gyms.get(i).setNextGymApi("https://bzu-nutriserve.appspot.com/gym/1");
				 
			} else if (i == gyms.size() - 1) {
				gyms.get(i).setPreviousGymApi("https://bzu-nutriserve.appspot.com/gym/" + (i - 1));
				gyms.get(i).setNextGymApi("");
			} else {
				gyms.get(i).setPreviousGymApi("https://bzu-nutriserve.appspot.com/gym/" + (i - 1));
				gyms.get(i).setNextGymApi("https://bzu-nutriserve.appspot.com/gym/" + (i + 1));
			}

		}
	}
}
