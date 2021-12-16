package FetchInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.naming.ldap.StartTlsRequest;
import javax.swing.JCheckBox;

import org.json.JSONArray;
import org.json.JSONObject;

import DTO.Country;

public class ListCountry {
		public static ArrayList<String> LstTenQuocGia = new ArrayList<>();
		public static ArrayList<String> LstCca3 = new ArrayList<>();
		public String fileDsQuocGia = "dsTenQuocGia.txt";
		public String fileCca3 = "dsCca3.txt";

		public void init() {
				LstTenQuocGia = getDsFromFile(fileDsQuocGia);
				LstCca3 = getDsFromFile(fileCca3);
		}
		//đổi hết mã cca3 trong tiếp giáp thành tên quốc gia
		public String getListNameBorders(String listcca3) {
				String listName="";
				StringTokenizer st = new StringTokenizer(listcca3,",");
				while ( st.hasMoreTokens() ) {
						String cca3 = st.nextToken();
						listName+=( getNameby_cca3(cca3) + ";");
				}
				return listName;
		}
		//hỗ trợ cho getListNameBorders(String listcca3)
		public String getNameby_cca3(String cca3) {
				int i=LstCca3.indexOf(cca3);
				return LstTenQuocGia.get(i);
		}
		public static void main(String[] args) {
			ListCountry ls= new ListCountry();
			ls.init();
			System.out.print(ls.getInfoByID(227).toString());
		}
		//trả về danh sách tên quốc gia để đổ vào combobox
		public String  ListNameToString(){
				LstTenQuocGia = getDsFromFile(fileDsQuocGia); //mở server lên chạy cái này 
				String lstToString="";
				for (String i: LstTenQuocGia)
				lstToString+=(i+";");
				return lstToString;
		}
		public ArrayList<String> getDsFromFile(String filename) {
				ArrayList<String> temp= new ArrayList<>();
				String currentDir = new File("").getAbsolutePath();
				String url = currentDir+"/"+filename;
				File file=new File(url);
				// Đọc dữ liệu từ File với Scanner
		        FileInputStream fileInputStream=null;
		        Scanner scanner = null; 
		        
		        try {
		        	fileInputStream = new FileInputStream(file);
		        	scanner = new Scanner(fileInputStream);
		            while (scanner.hasNextLine()) {
		                	temp.add(scanner.nextLine());
		            }
		        } catch (FileNotFoundException e) {
						System.err.println(e);
				} finally {
		            try {
		                scanner.close();
		                fileInputStream.close();
		            } catch (IOException ex) {
		            	return null;
		            }
		        }
		        return temp;
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


		//
		public Country getInfoByID(int id) {
			String apiQuocGia="https://restcountries.com/v3.1/name/";
			String countryName = LstTenQuocGia.get(id);
			String url = apiQuocGia + countryName;
			url = url.trim().replaceAll(" ", "%20");
			Country c=null;
			JSONArray ja = new JSONArray(getJson(url));
			for (Object obj: ja) {
					JSONObject jobj = (JSONObject)obj;
					//lấy tên
					String ten ="";
					if(jobj.has("name")){
						ten = jobj.getJSONObject("name").getString("common");
					}
					if(!ten.equals(countryName)) continue;
					//lấy thủ đô
					String thuDo = "";
					if(jobj.has("capital")){
						thuDo = jobj.getJSONArray("capital").getString(0);
					}
					//lấy dân số
					int danSo =0;
					if(jobj.has("population")){
							danSo = jobj.getInt("population");
					}
					//lấy diện tích
					float dienTich =0;
					if(jobj.has("area")){
							dienTich = jobj.getFloat("area");
					}
					//lấy tiền tệ
					String tienTe = "";
					if(jobj.has("currencies")){
						Iterator<String> keysTienTe= jobj.getJSONObject("currencies").keys();
						while (keysTienTe.hasNext()) 
						{
						        String keyValue = (String)keysTienTe.next();
						        tienTe+= jobj.getJSONObject("currencies").getJSONObject(keyValue).getString("name");
						        tienTe+="; ";
						}
					}
					//lấy châu lục
					String chauLuc = "";
					if(jobj.has("region")){
							chauLuc += jobj.getString("region");
							if(jobj.has("subregion")){
									chauLuc += ("--"+ jobj.getString("subregion"));
							}
					}
					//lấy ngôn ngữ
					String ngonNgu ="";
					if(jobj.has("languages")){
							Iterator<String> keys= jobj.getJSONObject("languages").keys();
							while (keys.hasNext()) 
							{
							        String keyValue = (String)keys.next();
							        ngonNgu += jobj.getJSONObject("languages").getString(keyValue);
							        ngonNgu+="; ";
							}
					}
					//lấy múi giờ 
					String muiGio = "";
					if(jobj.has("timezones")){
							muiGio = jobj.getJSONArray("timezones").getString(0);
					}
					//lấy mã đại diện cca3 của quốc gia để tra quốc gia liền kề
					String cca3="";
					if(jobj.has("cca3"))
							cca3=jobj.getString("cca3");
					//lấy mã cca3 các quốc gia liền kề
					String tiepGiap = "";
					if (jobj.has("borders")) {
						JSONArray borders = jobj.getJSONArray("borders");int  n=borders.length();
						for (int i=0;i<n;i++) {
								tiepGiap+=(","+borders.getString(i));
						}
					}
					tiepGiap=getListNameBorders(tiepGiap);
					//lấy đường link quốc kì
					String quocKy ="";
					if (jobj.has("flags")) {
							if (jobj.getJSONObject("flags").has("png")) 
							quocKy = jobj.getJSONObject("flags").getString("png");
							else
								quocKy = jobj.getJSONObject("flags").getString("svg");
					}
					//lấy tọa độ
					String toaDo = "";
					if (jobj.has("latlng")) {
							JSONArray jsToaDo = jobj.getJSONArray("latlng");
							toaDo = jsToaDo.getInt(0)+", "+ jsToaDo.getInt(1);
					}
					c = new Country(ten,danSo,dienTich,tienTe,thuDo,chauLuc,ngonNgu,muiGio,tiepGiap,quocKy,cca3,toaDo);
						//đổi hết mã cca3 trong tiếp giáp thành tên quốc gia
						//convert_all_cca3_CountryName();
			}
			return c;
		}

}
