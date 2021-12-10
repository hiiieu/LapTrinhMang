package FetchInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.ldap.StartTlsRequest;
import javax.swing.JCheckBox;

import org.json.JSONArray;
import org.json.JSONObject;

import DTO.Covid;

public class ListCovid {
		public static ArrayList<Covid> lstCovid= new ArrayList<Covid>();
		
		
		//lấy json đổ dữ liệu vào lstCovid
		public void getLstCovid(String quocgia, String time ) {	
			String apiCovidInfo="https://api.covid19api.com/live/country/"+quocgia+"/status/confirmed/date/"+time;
			JSONArray jsonLstCovid = new JSONArray(getJson(apiCovidInfo));
			
			//loop to get all json objects from data json array
			for(Object obj:jsonLstCovid) {
				JSONObject jobj=(JSONObject) obj;
				String ten ="";
				int canhiem = 0;
				int khoibenh = 0;
				int chet = 0;
				String thoigian ="";
				//tên quốc gia	
				ten = jobj.getString("Country");
				//tổng số ca nhiễm
				canhiem = jobj.getInt("Confirmed");
				//tổng số ca khỏi bệnh
				khoibenh = jobj.getInt("Recovered");
				//tổng số ca chết
				chet = jobj.getInt("Deaths");
				//thời gian
				thoigian = jobj.getString("Date");
				Covid cv = new Covid(ten,canhiem,khoibenh,chet,thoigian);
				lstCovid.add(cv);
			}		
				
		}
		public Covid getCountryById(int id) {
				return lstCovid.get(id);
		}
		
		public static void main(String[] args) {
			String quocgia="vietnam";
			String time="2021-11-27";
			ListCovid ls= new ListCovid();
			ls.getLstCovid(quocgia, time);
			for (Covid i : lstCovid) {
				if(i.getThoiGian().contains("2021-11-28")) {
					System.out.println(i.getQuocGia()+"\nThời gian: "+i.getThoiGian()+"\nTổng số ca nhiễm: "+i.getCaNhiem()+"\nTổng số ca khỏi bệnh: "+i.getKhoiBenh()+"\nTổng số ca chết: "
							+i.getChet()+"\n");
				}
				
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
}