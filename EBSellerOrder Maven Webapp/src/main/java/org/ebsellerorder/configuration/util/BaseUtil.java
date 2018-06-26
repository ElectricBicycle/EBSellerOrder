package org.ebsellerorder.configuration.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.ebsellerorder.configuration.pojo.AccessToken;
import org.ebsellerorder.configuration.pojo.Button;
import org.ebsellerorder.configuration.pojo.Menu;
import org.ebsellerorder.configuration.pojo.Ticket;
import org.ebsellerorder.configuration.pojo.ViewButton;

import net.sf.json.JSONObject;


public class BaseUtil {
	
	public static String baseUrl = "http://supplywechat.tunnel.qydev.com/EBSellerOrder/";
	//public static String baseUrl = "http://supplierwechat.free.ngrok.cc/EBSellerOrder/";
	
	private static final String APPID = "wxf1172d4291aafa24";
	private static final String APPSECRET = "e945f6f799c771f2133054bf677f97e2";
	
	private static final String ACCESS_TOKEN_URL= "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";	
	
	private static final String SNSAPI_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}
	
	/**
	 * POST请求
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}
	
	//获得AccessToken
	public static AccessToken getAccessToken(String appId ,String appSecret) {
		System.out.println("调用获取access_token");
		AccessToken accessToken = new AccessToken();
		try {
			//获取accessToken地址的url
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret);
			//打开链接
			URLConnection connection = url.openConnection();
			//建立时间链接
			connection.connect();
			//构建输出流
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//转换为json
			String line = in.readLine();
			JSONObject jo = JSONObject.fromObject(line);
			accessToken.setAccessToken(jo.getString("access_token"));
			accessToken.setExpiresin(jo.getInt("expires_in"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	//获得ticket
		public static Ticket getTicket(String accessTokenStr) {
			Ticket ticket  = null;
			try {
				//获取ticket地址的url
				URL url = new URL("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessTokenStr+"&type=jsapi");
				//打开链接
				URLConnection connection = url.openConnection();
				//建立时间链接
				connection.connect();
				//构建输出流
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				//转换为json
				String line = in.readLine();
				JSONObject jo = JSONObject.fromObject(line);
				String ticketString = jo.getString("ticket");
				if(ticketString==null){
					return null;
				}else{
					ticket = new Ticket();
					ticket.setTicket(jo.getString("ticket"));
					ticket.setExpiresin(jo.getInt("expires_in"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ticket;
		}
	
	/**
	 * 组装菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		
		/**
		 * 用户中心
		 */
		ViewButton login = new ViewButton();
		login.setName("登录");
		login.setType("view");
		login.setUrl(baseUrl+"views/login/login.html");
		
		Button userButton = new Button();
		userButton.setName("用户中心");
		userButton.setSub_button(new Button[]{login});
		
		menu.setButton(new Button[]{userButton});
		return menu;
	}
	
	public static int createMenu(String token,String menu) throws ParseException, IOException{
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
			String msg = jsonObject.getString("errmsg");
			System.out.println(msg);
		}
		return result;
	}
		
}
