package com.afc.event;

import java.io.Serializable;
import java.util.List;

public class ADEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String description;
	private List<Detail> detail;
	private String eleMaxNumber;
	private int localID;
	private int pageID;
	private String pid;
	private String title;

	public ADEntity() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Detail> getDetail() {
		return detail;
	}

	public void setDetail(List<Detail> detail) {
		this.detail = detail;
	}

	public String getEleMaxNumber() {
		return eleMaxNumber;
	}

	public void setEleMaxNumber(String eleMaxNumber) {
		this.eleMaxNumber = eleMaxNumber;
	}

	public int getLocalID() {
		return localID;
	}

	public void setLocalID(int localID) {
		this.localID = localID;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public class Detail {

		public Detail() {
		}

		private String description;
		private String imageSrc;
		private boolean isDefault;
		private String marketPrice;
		private String page;
		private String price;
		private String priceType;
		private String serviceId;
		private String sort;
		private String targetUrl;
		private String title;
		private String touchId;
		private String type;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImageSrc() {
			return imageSrc;
		}

		public void setImageSrc(String imageSrc) {
			this.imageSrc = imageSrc;
		}

		public boolean isDefault() {
			return isDefault;
		}

		public void setDefault(boolean isDefault) {
			this.isDefault = isDefault;
		}

		public String getMarketPrice() {
			return marketPrice;
		}

		public void setMarketPrice(String marketPrice) {
			this.marketPrice = marketPrice;
		}

		public String getPage() {
			return page;
		}

		public void setPage(String page) {
			this.page = page;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getPriceType() {
			return priceType;
		}

		public void setPriceType(String priceType) {
			this.priceType = priceType;
		}

		public String getServiceId() {
			return serviceId;
		}

		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}

		public String getTargetUrl() {
			return targetUrl;
		}

		public void setTargetUrl(String targetUrl) {
			this.targetUrl = targetUrl;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getTouchId() {
			return touchId;
		}

		public void setTouchId(String touchId) {
			this.touchId = touchId;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

}
