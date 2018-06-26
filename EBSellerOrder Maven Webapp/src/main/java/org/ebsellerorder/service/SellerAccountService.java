package org.ebsellerorder.service;

import java.util.Map;

import org.ebsellerorder.pojo.SellerAccount;



public interface SellerAccountService {
	
	/**
	 * 登录时检查账户
	 * @return
	 */
	public Map<String,Object> checkifAccount(SellerAccount sellerAccount)throws Exception;
	
	/**
	 * 登录
	 * @return
	 */
	public SellerAccount sellerAccountLogin(SellerAccount sellerAccount)throws Exception;
	
}
