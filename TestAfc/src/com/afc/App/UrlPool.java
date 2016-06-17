package com.afc.App;

/**
 * Created by Administrator on 2016/5/5.
 */
public class UrlPool {

	// 判断号码是否已注册
	public static final String IsMemberRegisted = Host.AIF_WIT
			+ "api/Member/IsMemberRegisted";

	// 获取验证码
	public static final String GetRegistCode = Host.AIF_WIT
			+ "api/Member/GetRegistCode";

	/**
	 * POST 图片上传
	 */
	public static final String UploadFiles = Host.AIF_WIT
			+ "api/File/UpLoadCommonFile";

	// 注册会员,密码MD5加密后传过来
	public static final String RegisterMember = Host.AIF_WIT
			+ "api/Member/RegisterMember";

	// 获取轮播广告
	public static final String QueryCarouselAds = Host.AIF_WIT
			+ "api/Ads/QueryCarouselAds";

	// 修改密码
	public static final String ModifyPassword = Host.AIF_WIT
			+ "api/Member/ModifyPassword";

	// 登录接口
	public static final String LOGIN = Host.AIF_WIT + "api/Member/Login";

	// 产品首页--列表数据
	public static final String QueryProductList = Host.AIF_WIT
			+ "api/Product/QueryProductList";

	// 产品详情
	public static final String ProductDetail = Host.AIF_WIT
			+ "api/Product/QueryProductDetail";

	// 产品资料
	public static final String ProductFile = Host.AIF_WIT
			+ "api/Product/QueryProductFile";
	
	//项目列表
	public static final String QueryProjectList=Host.AIF_WIT+"api/Project/QueryProjectList";

}
