package org.ebsellerorder.pojo.request.sellerAccount;
/**
 * 账号登录
 * @author smile
 *
 */
public class SellerAccountParam {
		//登录账号
		private String sellerCode;  
		//密码
		private String password;
		//账户名称
		private String name;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
}
