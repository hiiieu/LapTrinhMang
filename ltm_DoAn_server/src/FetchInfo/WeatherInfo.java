package FetchInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import DTO.Weather;

public class WeatherInfo {
	
	/**
	 * 
	 */
	

	public static ArrayList<Weather> lstWeather= new ArrayList<Weather>();
	
	String apikey="db9440a39b78859e0f9cd68a061be96e";
	public  Weather getWeather(String city) {
		Weather w = null;
		try {
		String apiWeatherInfo="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apikey;	
		JSONObject jsonWeather = new JSONObject(getJsonMoi(apiWeatherInfo));		
		JSONArray array = jsonWeather.getJSONArray("weather");		
		
			int doam ;
			String mua = null;
			double nhietdo;		
			String country;
			String toado;
			JSONObject jobj=array.getJSONObject(0);				
			mua = jobj.getString("main");	
			
			country = jsonWeather.getJSONObject("sys").getString("country");
			
			nhietdo = jsonWeather.getJSONObject("main").getFloat("temp");	
			nhietdo=(double)Math.round((nhietdo-275.15)*100)/100;
			
			doam = jsonWeather.getJSONObject("main").getInt("humidity");
			
			toado = jsonWeather.getJSONObject("coord").getDouble("lat") +", "+ jsonWeather.getJSONObject("coord").getDouble("lon");
			
			w = new Weather(doam, mua, nhietdo,toado,country);
			
		}catch(Exception e) {
			System.out.println("Khong co du lieu cua city\n");
		}
		return w;	
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
	public static String replaceSpace(String url) {
		return url.trim().replaceAll(" ", "%20");
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		WeatherInfo wi= new WeatherInfo();	
		String city = "";
		
		
		while (true) {		
		System.out.print("Nhap city: ");		
		city = sc.nextLine();		
		city = replaceSpace(city);
		if(city.equals ("bye")) break;		
		wi.getWeather(city);
		}	
	}
}
