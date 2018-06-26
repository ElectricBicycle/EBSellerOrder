package org.ebsellerorder.pojo.request.seller;
/**
 * 经销商基本信息表
 * @author 
 *
 */
public class SellerParam {
	
	//标识经销商的代码
		private String sellerCode;
		//经销商名称
		private String sellerName;
		//经销商简称
		private String sellerShortName;
		//经销商送货地址
		private String sellerDeliveryAddress;
		//经销商联系人姓名
		private String contactor;
		//联系人电话
		private String contactorTele;
		//地区代码
		private String areaCode;
		
		public String getSellerCode() {
			return sellerCode;
		}
		public void setSellerCode(String sellerCode) {
			this.sellerCode = sellerCode;
		}
		public String getSellerName() {
			return sellerName;
		}
		public void setSellerName(String sellerName) {
			this.sellerName = sellerName;
		}
		public String getSellerShortName() {
			return sellerShortName;
		}
		public void setSellerShortName(String sellerShortName) {
			this.sellerShortName = sellerShortName;
		}
		public String getSellerDeliveryAddress() {
			return sellerDeliveryAddress;
		}
		public void setSellerDeliveryAddress(String sellerDeliveryAddress) {
			this.sellerDeliveryAddress = sellerDeliveryAddress;
		}
		public String getContactor() {
			return contactor;
		}
		public void setContactor(String contactor) {
			this.contactor = contactor;
		}
		public String getContactorTele() {
			return contactorTele;
		}
		public void setContactorTele(String contactorTele) {
			this.contactorTele = contactorTele;
		}
		public String getAreaCode() {
			return areaCode;
		}
		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}
}
