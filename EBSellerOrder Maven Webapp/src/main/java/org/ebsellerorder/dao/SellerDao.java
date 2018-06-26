package org.ebsellerorder.dao;

import java.util.List;

import org.ebsellerorder.pojo.Seller;

/**
 * 经销商基本信息表
 * @author 
 *
 */
public interface SellerDao {
	/**
	 * 获取所有的经销商信息
	 * @return
	 */
	public List<Seller> findAllSeller();
	
	/**
	 * 登录时获取名称
	 * @return
	 */
	public Seller searchSellerNameByAccount(String sellerCode);
	
}
