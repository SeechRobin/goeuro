package locationservice;
/**
 * Location.java
 * @author Robin Mukanganise
 * @date 25-September
 */
public class Location {
	
	public String type = "";
	public String _id = "";
	public String name = "";
	public String latitude = "";
	public String longitude = "";

    /**
     * Location constructor
     * @param aType
     * @param aId
     * @param aName
     * @param aLatitude
     * @param aLongitude
     */
	public Location(String aType, String aId,  String aName, String aLatitude, String  aLongitude){
		
		type = aType;
		_id = aId;
		name = aName;
		latitude = aLatitude;
		longitude = aLongitude;
	}
    /**
     * Print current object
     * @return
     */
	public String getLocation(){

		return type + " " + _id + " " + name + " " + latitude + " " + longitude; 
	}
}
