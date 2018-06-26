package org.ebsellerorder.service.impl;

import java.util.List;

import org.ebsellerorder.dao.SellerDao;
import org.ebsellerorder.pojo.Seller;
import org.ebsellerorder.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SellerService")
public class SellerServiceImpl implements SellerService{
	@Autowired
	private SellerDao sellerDao;

	@Override
	public List<Seller> findAllSeller() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
