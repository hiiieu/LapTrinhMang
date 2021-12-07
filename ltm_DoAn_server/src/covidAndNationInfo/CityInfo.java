package covidAndNationInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

public class CityInfo {
	public static ArrayList<CityInfoDTO> lstCityInfo= new ArrayList<CityInfoDTO>();	
	String apikey="db9440a39b78859e0f9cd68a061be96e";
public  void getLstCity(int city) {
		
		try {
		String apiCityInfo="http://geodb-free-service.wirefreethought.com/v1/geo/cities/"+city;	
		JSONObject jsonCity = new JSONObject(getJson(apiCityInfo));	
			int id;
			int danso;
			String tenthanhpho;
			String tenquocgia;
			float vitri1;
			float vitri2;
			
			id = jsonCity.getJSONObject("data").getInt("id");
			danso = jsonCity.getJSONObject("data").getInt("population");
			tenthanhpho = jsonCity.getJSONObject("data").getString("city");
			tenquocgia = jsonCity.getJSONObject("data").getString("country");
			vitri1 = jsonCity.getJSONObject("data").getFloat("latitude");
			vitri2 = jsonCity.getJSONObject("data").getFloat("longitude");
									
			System.out.print(id+"\n"+tenthanhpho+"\n"+tenquocgia+"\n"+danso+"\n"+vitri1+","+vitri2);
			CityInfoDTO cinf = new CityInfoDTO( id,  danso,  tenthanhpho,  vitri1, tenquocgia);
			lstCityInfo.add(cinf);
		}catch(Exception e) {
			System.out.println("Ko co city");
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
	public static String replaceSpace(String url) {
		return url.trim().replaceAll(" ", "%20");
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		CityInfo ci = new CityInfo();	
		int city = 3453217;
		ci.getLstCity(city);
//		System.out.print("Nhap Id city: ");
//		city = sc.nextInt();
		
//		while (true) {		
//		System.out.print("Nhap city: ");		
//		city = sc.nextLine();		
//		
//		if(city.equals ("bye")) break;		
//		ls.getLstWeather(city);					
//		}	
	}
}
