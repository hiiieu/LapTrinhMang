package covidAndNationInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.ldap.StartTlsRequest;
import javax.swing.JCheckBox;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListCountry {
		public static ArrayList<Country> lstCounty= new ArrayList<Country>();
		//lấy json đổ dữ liệu vào lstCountry
		public  void getLstQuocGia() {
				String apiQuocGia="https://restcountries.com/v3.1/all";
				JSONArray jsonLstQuocGia = new JSONArray(getJson(apiQuocGia));
				int id=-1;
				for (Object obj:jsonLstQuocGia) {
						JSONObject jobj=(JSONObject) obj;
						id++;
						//lấy tên
						String ten ="";
						if(jobj.has("name")){
							ten = jobj.getJSONObject("name").getString("common");
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
						Country c = new Country(id,ten,danSo,dienTich,tienTe,thuDo,chauLuc,ngonNgu,muiGio,tiepGiap,quocKy,cca3,toaDo);
						lstCounty.add(c);
						}
				}
				//đổi hết mã cca3 trong tiếp giáp thành tên quốc gia
				convert_all_cca3_CountryName();
		}
		//trả về danh sách tên quốc gia để đổ vào combobox
		public String  getListName(){
			//chưa viết 
				return null;
		}
		public Country getCountryById(int id) {
				return lstCounty.get(id);
		}
		//đổi hết mã cca3 trong tiếp giáp thành tên quốc gia
		public void convert_all_cca3_CountryName() {
				for (Country i : lstCounty) {
						String cca3 = i.getTiepGiap();
						i.setTiepGiap(getListNameBorders(cca3));
				}
		}
		//hỗ trợ cho convert_all_cca3_CountryName()
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
				for (Country i: lstCounty) {
						if(i.getCca3().equals(cca3))
							return i.getTenQuocGia();
				}
				return "";
		}
		public static void main(String[] args) {
			ListCountry ls= new ListCountry();
			ls.getLstQuocGia();
			for (Country i : lstCounty) {
				System.out.println(i.getTenQuocGia()+",id: "+i.getId()+"\nthu do: "+i.getThuDo()+"\ndan so: "+i.getDanSo()+"\ndien tich: "
						+i.getDienTich() +"\nchau luc: "+i.getChauLuc()+"\nmui gio: "+i.getMuiGio()+"\nngon ngu: "+i.getNgonNgu()
						+"\ntien te: "+i.getTienTe()+"\nquoc ky: "+i.getQuocKy()+"\ntiep giap: "+i.getTiepGiap()
						+"\ntoa do: "+i.getToaDo()+"\n");
			}
			System.out.println("số lượng quốc gia: "+lstCounty.size());
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
