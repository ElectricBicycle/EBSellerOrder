package org.ebsellerorder.dao;


import org.ebsellerorder.pojo.SellerAccount;

/**
 * 登录账户信息
 * @author 
 *
 */
public interface SellerAccountDao {
	/**
	 * 登录时检查账户
	 * @return
	 */
	public SellerAccount checkifAccount(SellerAccount sellerAccount);
	
	/**
	 * 登录
	 * @return
	 */
	public SellerAccount sellerAccountLogin(SellerAccount sellerAccount);
}
