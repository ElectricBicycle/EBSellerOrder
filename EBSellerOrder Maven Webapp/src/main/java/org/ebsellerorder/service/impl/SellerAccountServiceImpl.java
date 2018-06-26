package org.ebsellerorder.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.ebsellerorder.dao.SellerAccountDao;
import org.ebsellerorder.dao.SellerDao;
import org.ebsellerorder.pojo.SellerAccount;
import org.ebsellerorder.pojo.Seller;
import org.ebsellerorder.service.SellerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SellerAccountService")
public class SellerAccountServiceImpl implements SellerAccountService{
	@Autowired
	private SellerAccountDao sellerAccountDao;
	@Autowired
	private SellerDao sellerDao;
	
	/**
	 * 登录时检查账户
	 * @return
	 */
	@Override
	public Map<String,Object> checkifAccount(SellerAccount sellerAccount)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if(sellerAccount!=null){
			//检查是否存在该账户
			SellerAccount account = sellerAccountDao.checkifAccount(sellerAccount);
			if(account!=null){//存在该账户
				//查询该账户对应的名称
				//String type = account.getType();
				//String accountCode = account.getAccountCode();
				//String sellerCode = account.getSellerCode();
				//if("1".equals(type)){//经销商
					//Seller seller = sellerDao.searchSellerNameByAccount(sellerCode);
					//String name = seller.getSellerName();
					map.put("name", account.getSellerName());
				//}else{
					//业务员登录
				//}
				map.put("code", "0");
				map.put("msg", "查询成功！");
				return map;
			}else{//不存在该账户
				map.put("code", "2");
				map.put("msg", "不存在该账户！");
				return map;
			}
		}
		map.put("code", "1");
		map.put("msg", "查询错误！");
		return map;
	}
	
	/**
	 * 登录
	 * @return
	 */
	@Override
	public SellerAccount sellerAccountLogin(SellerAccount sellerAccount)throws Exception{
		if(sellerAccount!=null){
			return sellerAccountDao.sellerAccountLogin(sellerAccount);
		}
		return null;
	}


}
