package com.challenge.opendoor;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Encapsulates the properties of a listing
 */
public class Listing {
	
	//id of the listing
	public String id;	
	
	//street address of the listing
	public String street;
	
	//status of the listing
	public String status;
	
	//price of the listing
	public int price;
	
	//number of bedrooms for the listing
	public int bedrooms;
	
	//number of bathrooms for the listing
	public int bathrooms;
	
	//The size of the listing in square feet
	public String sq_ft;
	
	//The latitude of the listing
	public String lat;
	
	//The longitude of the listing
	public String lng;

	/*
	 * Loading the Listing Object based on the record read from the csv file
	 */
    public void Load(String[] currentListing)
    {
    	int i = 0;
    	id = currentListing[i++];
    	street = currentListing[i++];
    	status	= currentListing[i++];
    	price	= Integer.parseInt(currentListing[i++]);
    	bedrooms = Integer.parseInt(currentListing[i++]);
    	bathrooms	= Integer.parseInt(currentListing[i++]);
    	sq_ft	= currentListing[i++];
    	lat	= currentListing[i++];
    	lng= currentListing[i++];

    }
    
    
    /*
	 * Building the json object with all the properties of the given listing
	 */
	public JSONObject buildListingJsonObject()
	{
		JSONObject feature = new JSONObject();
		feature.put("type", "feature");
		
		JSONArray coordinatesArray = new JSONArray();
		coordinatesArray.put(this.lat);
		coordinatesArray.put(this.lng);
		
		JSONObject geometryObject = new JSONObject();
		geometryObject.put("type", "Point");
		geometryObject.put("coordinates", coordinatesArray);
		feature.put("geometry", geometryObject);
		
		JSONObject propertiesObject = new JSONObject();
		propertiesObject.put("id", this.id);
		propertiesObject.put("price", this.price);
		propertiesObject.put("street", this.street);
		propertiesObject.put("bedrooms", this.bedrooms);
		propertiesObject.put("bathrooms", this.bathrooms);
		propertiesObject.put("sq_ft", this.sq_ft);
		feature.put("properties", propertiesObject);
		
		return feature;
		
	}

}
