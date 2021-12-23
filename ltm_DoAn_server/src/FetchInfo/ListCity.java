package FetchInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import DTO.City;

public class ListCity {
	public static ArrayList<City> lstCity= new ArrayList<City>();
	//láº¥y json Ä‘á»• dá»¯ liá»‡u vÃ o lstCountry
	public  void getLstThanhPho() {
			String apiCity="https://countriesnow.space/api/v0.1/countries?fbclid=IwAR1D1B2HCKc0-LIMH26sydAMrNlHHgg68cfj2qBBiY_Y0PGWTLS3Ta317pg";
			JSONObject jsonO = new JSONObject(getJson(apiCity));
			JSONArray jsonLstCity = jsonO.getJSONArray("data");
			int id = -1;
			for (Object obj:jsonLstCity) {
				JSONObject jobj = (JSONObject) obj;
				id++;
				String tenquocgia = "";
				if(jobj.has("country")) {
					tenquocgia = jobj.getString("country");
				}
				String tenthanhpho="";
				if(jobj.has("cities")) {
					JSONArray cities = jobj.getJSONArray("cities");
					for(int i = 0; i < cities.length();i++) {
						tenthanhpho+=(cities.getString(i)+ ";");
					}					
				}
				City ci=new City (id,tenquocgia,tenthanhpho);
				lstCity.add(ci);
			}
	}
	public String getJsonMoi(String s) {
		try {
			HttpURLConnection httpConn = null;
			URL url = new URL(s);   
			URLConnection connection = url.openConnection();
			httpConn = (HttpURLConnection) connection;
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("charset", "utf-8");
			BufferedReader in;
			int responseCode = httpConn.getResponseCode();
			if (responseCode==200) {
				 in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			}else
				 in = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
		    String inputLine;
		    StringBuffer res=new StringBuffer();
		    while ( (inputLine = in.readLine()) !=null  ) {
		    	res.append(inputLine);
		    }
		    
		  in.close();
		  return (res.toString());
			} catch (IOException e) {
				System.err.println(e);
				return null;
			}
	  }
	public String getJson(String s) {
		  String sURL = s; //just a string

		    // Connect to the URL using java's native library
		    URL url;
			try {
				url = new URL(sURL);
			
		    URLConnection request = url.openConnection();
		    request.connect();
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		    String inputLine;
		    StringBuffer res=new StringBuffer();
		    while ( (inputLine = in.readLine()) !=null  ) {
		    	res.append(inputLine);
		    }
		    
		    in.close();
		  	return (res.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	  }
	public String ListName() {
		ListCity lsct= new ListCity();
		lsct.getLstThanhPho();
		String lst="";
		for(City i : lstCity) {
			lst+=i.getTenThanhPho();
		}
		
		return lst;
	}
	public static void main(String[] args) {
		ListCity lsct= new ListCity();
		
		System.out.print(lsct.ListName());
		
	}
}
