package locationservice;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * DataProcessing.java
 * @author Robin
 * @date 25-September
 */
public class DataProcessing {
	/**
	 * Method to read the output from the buffer
	 * @param rd
	 * @return 
	 * @throws IOException
	 */
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	
      /**
       * Method to read URL with the requested query
       * @param url
       * @return
       * @throws IOException
       * @throws JSONException
       */
	  public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader (new InputStreamReader(is, Charset.forName("UTF-8")));
	     
	      String jsonText = readAll(rd);
	     
	      JSONArray json = new JSONArray (jsonText);
	      //Check if array is empty then the query is wrong!
	      if(json.length()<=0){
	    	  System.out.println("No Matches");
	    	  System.out.println("Exiting!");
	    	  System.exit(0);
	      }
	      return json;

	    } finally {
	      is.close();
	    }
	  }
      /***
       * Method to create the CSV file
       * @param fileName
       * @param locations
       */
	  public static void createCSV(String fileName, List<Location> locations){

	     try
	     {
	       FileWriter writer = new FileWriter(fileName);
	       
	       //write the headings
	       writer.append("type");
	       writer.append(',');
	       writer.append("id");
	       writer.append(",");
	       writer.append("name");
	       writer.append(",");
	       writer.append("latitiude");
	       writer.append(",");
	       writer.append("longitude");
	       writer.append('\n');
	       
	       System.out.println("Populating CSV");
	       //Populate CSV file with values
	       for(int i=0; i<locations.size(); i++){
	    	   writer.append(locations.get(i).type);
	    	   writer.append(",");
	    	   writer.append(locations.get(i)._id);
	    	   writer.append(",");
	    	   writer.append(locations.get(i).name);
	    	   writer.append(",");
	    	   writer.append(locations.get(i).latitude);
	    	   writer.append(",");
	    	   writer.append(locations.get(i).longitude);
	    	   writer.append("\n"); 
	       }
	       System.out.println("Finished: Check 'results.csv' in your directory");
	       writer.flush(); //flush writer
	       writer.close(); //close writer
	   }
	   catch(IOException e)
	   {
		   e.printStackTrace();
	   } 
	 
	  }
}
