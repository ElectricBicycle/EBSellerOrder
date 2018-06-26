package org.ebsellerorder.service;

import java.util.List;

import org.ebsellerorder.pojo.Seller;


public interface SellerService {
	/**
	 * 获取所有的经销商信息
	 * @return
	 */
	public List<Seller> findAllSeller()throws Exception;
	
	
	
}
