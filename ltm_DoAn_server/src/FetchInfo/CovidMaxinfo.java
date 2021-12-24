package FetchInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import DTO.MaxCovid;

public class CovidMaxinfo {
	public static ArrayList<MaxCovid> lstMaxcovid = new ArrayList<MaxCovid>();
	
	public void getListmax() {
		String apiCovidmax="https://api.covid19api.com/summary";
	  	JSONObject joCovidmax = new JSONObject(getJson(apiCovidmax));
	  	JSONArray jaCovidmax = joCovidmax.getJSONArray("Countries");
	  	int id=0;
	  	for(Object obj:jaCovidmax) {
	  		id++;
	  		JSONObject jobj=(JSONObject) obj;
	  		//tên quốc gia
	  		String tenquocgia=""; 
	  		tenquocgia =jobj.getString("Country");
	  		//tổng số ca nhiễm bệnh
	  		int nhiemMax;
	  		nhiemMax = jobj.getInt("TotalConfirmed");
	  		//tổng số ca chết
	  		int chetMax;
	  		chetMax = jobj.getInt("TotalDeaths");
	  		//tổng số ca khỏi bệnh (dữ liệu không được cập nhật nên không có số người khỏi bệnh)
	  		int khoibenhMax;
	  		khoibenhMax = jobj.getInt("TotalRecovered");
	  		MaxCovid max = new MaxCovid(id, tenquocgia, nhiemMax, chetMax, khoibenhMax);
	  		lstMaxcovid.add(max);
	  	} 	
	}
	
	public MaxCovid getCountryById(int id) {
		return lstMaxcovid.get(id);
	}
	
	public String canhiemmax() {
		String ten ="";
		int canhiemlon=0;
		for(MaxCovid i: lstMaxcovid) {
			if(i.getCanhiemmax()>canhiemlon) {
				ten=i.getQuocgia();
				canhiemlon=i.getCanhiemmax();
			}
		}
		return ten+";"+canhiemlon;
	}
	
	public String cachetmmax() {
		String ten ="";
		int chetlon=0;
		for(MaxCovid i: lstMaxcovid) {
			if(i.getChetmax()>chetlon) {
				ten=i.getQuocgia();
				chetlon=i.getChetmax();
			}
		}
		System.out.println("\nquốc gia: "+ten+"\nCó số người chết lớn nhất là: "+chetlon);
		return ten+";"+chetlon;
	}
	
	public String khoibenhmmax() {
		String ten ="";
		int khoibenhlon=0;
		for(MaxCovid i: lstMaxcovid) {
			if(i.getKhoibenhmax()>khoibenhlon) {
				ten=i.getQuocgia();
				khoibenhlon=i.getKhoibenhmax();
			}
		}
		String kq = ten+";"+khoibenhlon;
		return ten+";"+khoibenhlon;
	}
	
	public static void main(String[] arg) {
		CovidMaxinfo max = new CovidMaxinfo();
		max.getListmax();
		max.canhiemmax();
		max.cachetmmax();
		max.khoibenhmmax(); //dữ liệu không được cập nhật nên không số người khỏi bệnh
		
		//in toàn bộ các quốc gia
//		for(MaxCovid i: lstMaxcovid) {
//			System.out.println("\nId: "+i.getId()+"\ntên: "+i.getQuocgia()+"\nTổng số ca nhiễm: "+i.getCanhiemmax()+"\nTổng số người chết: "+i.getChetmax()+
//					"\nTổng số người khỏi bệnh: "+i.getKhoibenhmax());
//		}
	}
	
	private static String getJson(String j) {
		String sURL = j; //just a string
		
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

}
