package com.afc.Utils.myphotos;

import java.io.Serializable;
import java.util.List;

/**
 * 文件上传的回调
 * 
 * @author yiyi
 * @date 2016年3月7日
 */
public class UploadResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8576534780042599479L;
	private List<Item> resultList;

	public static class Item {
		private String fullPath;
		private boolean isSuccess;
		private String message;

		public String getFullPath() {
			return fullPath;
		}

		public void setFullPath(String fullPath) {
			this.fullPath = fullPath;
		}

		public boolean isSuccess() {
			return isSuccess;
		}

		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	public List<Item> getResultList() {
		return resultList;
	}

	public void setResultList(List<Item> resultList) {
		this.resultList = resultList;
	}
}
