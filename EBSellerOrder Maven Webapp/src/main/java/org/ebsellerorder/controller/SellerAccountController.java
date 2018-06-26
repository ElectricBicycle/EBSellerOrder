package org.ebsellerorder.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ebsellerorder.pojo.SellerAccount;
import org.ebsellerorder.pojo.request.sellerAccount.SellerAccountParam;
import org.ebsellerorder.service.SellerAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 账号登录
 * @author 
 *
 */
@Controller
@RequestMapping("/sellerLogin")
public class SellerAccountController {
	
	private static Logger logger = Logger.getLogger(SellerAccountController.class);
	
	@Autowired
	private SellerAccountService sellerAccountService;
	
	/**
	 * 检查账号
	 * @param request
	 * @param model
	 * @param sellerLoginParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkAccount")
	public Object checkAccount(HttpServletRequest request,Model model,SellerAccountParam sellerAccountParam)throws Exception{
		if(sellerAccountParam!=null){
			SellerAccount sellerAccount = new SellerAccount();
			BeanUtils.copyProperties(sellerAccountParam, sellerAccount);
			Map<String, Object> map = sellerAccountService.checkifAccount(sellerAccount);
			return map;
		}
		logger.error("参数为null！");
		return "false";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param model
	 * @param sellerLoginParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/sellerAccountLogin")
	public String SellerLogin(HttpServletRequest request,Model model,SellerAccountParam sellerAccountParam)throws Exception{
		if(sellerAccountParam!=null){
			HttpSession session=request.getSession();
			//登录
			SellerAccount sellerAccount = new SellerAccount();
			BeanUtils.copyProperties(sellerAccountParam, sellerAccount);
			sellerAccount = sellerAccountService.sellerAccountLogin(sellerAccount);
			if(sellerAccount==null){//登录失败
				logger.error("登录失败！");
				return "false";
			}else{
				//保存session中
				session.setAttribute("CurrentAccount",sellerAccount);
				session.setAttribute("name",sellerAccountParam.getName());
				logger.info("登录成功！");
				return "true";
			}
		}
		logger.error("登录失败！");
		return "false";
	}
	
	/**
	 * 获取当前登录用户信息
	 * @param request
	 * @param model
	 * @param sellerLoginParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getCurrentUser")
	public Object getCurrentUser(HttpServletRequest request,Model model)throws Exception{
		HttpSession session=request.getSession();
		String name = (String) session.getAttribute("name");
		SellerAccount sellerAccount = (SellerAccount) session.getAttribute("CurrentAccount");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("currentAccount", sellerAccount);
		logger.info("获取当前登录用户信息成功！");
		return map;
	}
}
