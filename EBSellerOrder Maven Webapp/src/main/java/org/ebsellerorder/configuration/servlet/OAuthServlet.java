package org.ebsellerorder.configuration.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ebsellerorder.configuration.pojo.WeixinOauth2Token;
import org.ebsellerorder.configuration.util.AdvacedUtil;


/**
 * Servlet implementation class OAuthServlet
 */
@WebServlet("/OAuthServlet")
public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OAuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 	request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
	        
	        String openid = null;
	        String accessToken = null;
	        // 获取code和state
	        String code = request.getParameter("code");
	        String state = request.getParameter("state");
	        
	        HttpSession session = request.getSession();
	 	        // 
	 	        if (!"authdeny".equals(code)) {
	 	        	
	 	        	openid = (String) session.getAttribute("openid");
	 	        	if(openid == null || openid.length() <= 0)
	 	        	{
	 	        		 // 获取access_token
		 	            WeixinOauth2Token weixinOauth2Token = AdvacedUtil.getOauth2AccessToken("wxf1172d4291aafa24", "e945f6f799c771f2133054bf677f97e2", code);
		 	            accessToken = weixinOauth2Token.getAccessToken();
		 	            openid = weixinOauth2Token.getOpenId();
		 	            session.setAttribute("openid", openid);
		 	            session.setAttribute("accessToken", accessToken);
	 	        	}else{
	 	        		accessToken = (String) session.getAttribute("accessToken");
	 	        	}
	 	           
	 	            // 使用access_token获取用户信息
	 	            //SNSUserInfo snsUserInfo = AdvacedUtil.getSNSUserInfo(accessToken, openid);
	 	            //String nickname = snsUserInfo.getNickname();
	 	        	Object currentAccount = session.getAttribute("CurrentAccount");
	 	           if(currentAccount!=null){
	 	        	  response.sendRedirect("http://supplywechat.tunnel.qydev.com/EBSellerOrder/views/login/index.html");
	 	           }else{
	 	        	 response.sendRedirect("http://supplywechat.tunnel.qydev.com/EBSellerOrder/views/login/login.html?" + openid);
	 	           }
			 	   
	 	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
