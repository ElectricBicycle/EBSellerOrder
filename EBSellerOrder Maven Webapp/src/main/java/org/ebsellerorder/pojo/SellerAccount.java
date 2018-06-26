package org.ebsellerorder.pojo;
/**
 * 登录账户表
 * @author 
 *
 */
public class SellerAccount {
	//登录账号
	private String sellerCode;  
	//密码
	private String password;
	//账户名称
	private String sellerName;
	public String getSellerCode() {
		return sellerCode;
	}
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}  
	
}
