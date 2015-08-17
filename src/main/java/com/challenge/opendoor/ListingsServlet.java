package com.challenge.opendoor;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * ListingsServlet serves the listings based on the input parameters
 */
public class ListingsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

		String minPrice = request.getParameter("min_price");
		String maxPrice = request.getParameter("max_price");
		String minBed = request.getParameter("min_bed");
		String maxBed = request.getParameter("max_bed");
		String minBath = request.getParameter("min_bath");
		String maxBath = request.getParameter("max_bath");

		JSONObject listings = new JSONObject();
		listings.put("type", "FeatureCollection");

		JSONArray featuresArray = new JSONArray();

		BufferedReader br = new BufferedReader(new FileReader("listings.csv"));
		String line = br.readLine();

		while((line = br.readLine()) != null)
		{
			String[] currentListing = line.split(",");
			Listing listing = new Listing();
			listing.Load(currentListing);

			if((minPrice == null || listing.price >= Integer.parseInt(minPrice)) &&
					(maxPrice == null || listing.price <= Integer.parseInt(maxPrice)) &&
					(minBed == null   || listing.bedrooms >= Integer.parseInt(minBed))&&
					(maxBed == null   || listing.bedrooms <= Integer.parseInt(maxBed))&&
					(minBath == null  || listing.bathrooms >= Integer.parseInt(minBath))&&
					(maxBath == null  || listing.bathrooms <= Integer.parseInt(maxBath)))
			{
				JSONObject feature = listing.buildListingJsonObject();
				featuresArray.put(feature);
			}

		}

		listings.put("features", featuresArray);		

		response.setContentType("application/json");

		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();

		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(listings);

		br.close();
		out.flush();
	}

}
