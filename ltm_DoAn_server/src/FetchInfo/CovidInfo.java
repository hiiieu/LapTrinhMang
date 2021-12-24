package FetchInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

import DTO.Covid;

public class CovidInfo {
		public static ArrayList<Covid> lstCovid= new ArrayList<Covid>();
		
		//lấy json đổ dữ liệu vào lstCovid
		public void getLstCovid(String quocgia, String time1,String time2 ) {	
			//String apiCovidInfo="https://api.covid19api.com/live/country/"+quocgia+"/status/confirmed/date/"+time1;
			String apiLink = "https://api.covid19api.com/country/"+quocgia+"?from="+time1+"T00:00:00Z&to="+time2+"T00:00:00Z";
			JSONArray jsonLstCovid = new JSONArray(getJson(replaceSpace(apiLink)));
			
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
		
		public static String replaceSpace(String url) {
			return url.trim().replaceAll(" ", "%20");
		}
		
		public static void main(String[] args) {			
			LocalDate date1 = LocalDate.of(2020, 1, 1);
			LocalDate date2 = LocalDate.of(2021, 12, 24);
			//api chỉ lấy ngày kế tiếp nên phải giảm xuống 1 ngày so với ngày nhập
			//String strdate1 = date1.minusDays(1).toString();
			//String strdate2 = date2.minusDays(1).toString();
			String quocgia="viet nam";
			CovidInfo ls= new CovidInfo();
			System.out.println(date1);
			System.out.println(date2);
			ls.getLstCovid(quocgia, date1.toString(),date2.toString());
			int m1=0,m2=0,m3=0;
			for (Covid i : lstCovid) {
				if(i.getThoiGian().compareTo(date1.toString())>0) {
					if(m1<i.getCaNhiem())		
					m1= i.getCaNhiem();
					if(m2<i.getKhoiBenh())
					m2=i.getKhoiBenh();
					if(m3<i.getChet())		
					m3=i.getChet();
				}
			
				
			}
			System.out.println("Tu ngay : "+date1+" Den ngay: "+date2);
			System.out.println(quocgia+": so ca nhiem :"+m1+" so ca khoi benh: "+m2+" so ca chet: "+m3);
		}
		public String getMax(String quocgia) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();
			String time1= "2020-01-01";
			String time2=now.toString();
			String apiLink = "https://api.covid19api.com/country/"+quocgia+"?from="+time1+"T00:00:00Z&to="+time2+"T00:00:00Z";
			JSONArray jsonLstCovid = new JSONArray(getJson(replaceSpace(apiLink)));
			int max1=0,max2=0,max3=0;
			//loop to get all json objects from data json array
			for(Object obj:jsonLstCovid) {
				JSONObject jobj=(JSONObject) obj;
				String ten ="";
				int canhiem = 0;
				int khoibenh = 0;
				int chet = 0;
				String thoigian ="";
				
				//tổng số ca nhiễm
				canhiem = jobj.getInt("Confirmed");
				if(canhiem>max1)
				max1=canhiem;
				//tổng số ca khỏi bệnh
				khoibenh = jobj.getInt("Recovered");
				if(khoibenh >max2)
				max2=khoibenh;
				//tổng số ca chết
				chet = jobj.getInt("Deaths");
				if(chet>max3)
				max3=chet;
				

			}		
			return max1+";"+max2+";"+max3;
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