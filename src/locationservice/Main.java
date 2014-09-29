package locationservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main.java
 * @author Robin Mukanganise
 * @date 25-September
 */
public class Main {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) throws IOException, JSONException {
	   
		//Validate and prevent user from entering empty parameter
		if(args.length ==0){
			System.out.println("Bad Query Parsed...Exitting!");
			System.exit(0);
		}
		
		String query = args[0]; // user query
		
		System.out.println("Querying Location API!");
		String url = "http://api.goeuro.com/api/v2/position/suggest/en/" + query;
		JSONArray json = DataProcessing.readJsonFromUrl(url);
	    Location newLocation = null;
	    List<Location> locations = new ArrayList<Location>();
	    
	    for(int i=0; i<json.length(); i++){
	        JSONObject object = json.getJSONObject(i);
	       
	         newLocation = new Location(object.get("type").toString(),object.get("_id").toString(),object.get("name").toString(),
	           object.getJSONObject("geo_position").get("latitude").toString(),object.getJSONObject("geo_position").get("longitude").toString());
	         locations.add(newLocation);

	         
	     }
	     DataProcessing.createCSV("results.csv", locations);
	}
}

