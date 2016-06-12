package com.afc.App;

import android.content.Context;

import com.afc.BuildConfig;
import com.afc.db.dao.CityEntityDao;
import com.afc.db.entity.CityEntity;
import com.afc.db.entity.CitydataData;
import com.afc.event.LocationEvent;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.lidroid.util.Logger;

import org.simple.eventbus.EventBus;

import java.util.List;

import cn.trinea.android.common.util.ListUtils;
import cn.trinea.android.common.util.PreferencesUtils;

/**
 * 与定位相关的工作
 * Created by Administrator on 2016/5/5.
 */
public class LbsMgr {
    /**
     * 获取现有地理位置信息
     */
    public static BDLocation sLocation = new BDLocation();

    private static LocationClient mLocationClient = null;

    public void init(Context context) {

        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(context);
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(new MyLocationListener(context.getApplicationContext()));
        setupLocation(context);

    }
    /**
     * 请求定位
     */
    public static void location() {
        if (mLocationClient != null)
            mLocationClient.start();
    }

    /**
     * 百度地图 初始化定位信息
     */
    private void setupLocation(Context context) {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值bd09ll
        option.setScanSpan(1000);// 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true); // 反地理编码
        mLocationClient.setLocOption(option);
    }

    /**
     * 实现实位回调监听
     */
    private class MyLocationListener implements BDLocationListener {
        private Context mContext;

        public MyLocationListener(Context context) {
            mContext = context;
        }

        @Override
        public void onReceiveLocation(BDLocation location) {
            mLocationClient.stop();
            Logger.i("LocType: " + location.getLocType());
            switch (location.getLocType()) {
                case BDLocation.TypeNetWorkException:
                    sendLocResult(false, location);
                    break;
                case BDLocation.TypeGpsLocation:
                case BDLocation.TypeNetWorkLocation:
                    if (location.getAddrStr() == null) {
                        sendLocResult(false, location);
                    } else {
                        saveData(mContext, location);
                    }
                    break;
                default:
                    sendLocResult(false, location);
                    break;
            }

        }

    }

    private void saveData(Context context, BDLocation location) {
        sLocation = location;

        String locationCity = location.getCity().replace("市", "");
        String bid = location.getCityCode();
        String longItude = "" + location.getLongitude();
        String latItude = "" + location.getLatitude();
        String detailAddress = "当前位置:" + location.getCity() + "-" + location.getStreet();
        List<CitydataData> listCitys = DbMgr.daoSession.getCitydataDataDao().loadAll();
        if (ListUtils.isEmpty(listCitys)) {
            Logger.e("Assets下城市数据出错.");
        } else {

            CitydataData temp = null;
            for (CitydataData item : listCitys) {
                if (locationCity.equals(item.getName())) {
                    temp = item;
                    break;
                }
            }
            Logger.i(detailAddress);
            if (temp != null) {
                Logger.i("对应城市编码：" + temp.getName() + "+" + temp.getId());
                City.putCity(context, location.getProvince(), temp.getId() + "", temp.getName());
            }
        }
        Location.putData(context, locationCity, longItude, latItude, detailAddress, bid);

        sendLocResult(true, location);

    }

    /**
     * 广播定位信息Get
     *
     * @param success
     */
    private void sendLocResult(boolean success, BDLocation location) {
        EventBus.getDefault().post(new LocationEvent(success, location));
    }

    public static class City {
        /**
         * SharedName
         */
        public static final String CITY = "CITY";
        /**
         * name 城市码
         */
        public static final String CITY_CODE = "city_code";
        /**
         * name 城市名称
         */
        public static final String CITY_NAME = "city_name";
        /**
         * 省份信息
         */
        public static final String PROVINCE = "PROVINCE";

        public static String getProvince(Context context) {
            return getString(context, CITY, PROVINCE);
        }

        public static void putProvince(Context context, String province) {
            putString(context, CITY, PROVINCE, province);
        }

        public static String getCode(Context context) {
            return getString(context, CITY, CITY_CODE);
        }

        public static String getName(Context context) {
            return getString(context, CITY, CITY_NAME);
        }

        public static void putCode(Context context, String code) {
            putString(context, CITY, CITY_CODE, code);
        }

        public static void putName(Context context, String name) {
            putString(context, CITY, CITY_NAME, name);
        }

        public static void putCity(Context context, String province, String code, String name) {
            putCode(context, code);
            putName(context, name);
            putProvince(context, province);
        }

    }

    public static class Location {
        /**
         * 定位相关
         */
        public final static String LOCATION = "LOCATION_PREF";
        /**
         * 定位城市
         */
        public final static String CITY = "CITY";
        /**
         * 纬度
         */
        public static final String LONGITUDE = "LONGITUDE";
        /**
         * 经度
         */
        public static final String LATITUDE = "LATITUDE";
        /**
         * 详细地址
         */
        public static final String DETAIL_ADDREDS = "DETAILADDREDS";
        /**
         * 百度城市id
         */
        public static final String BAIDU_ID = "bid";

        public static String getLongItude(Context context) {
            return getString(context, LOCATION, LONGITUDE);
        }

        public static String getLatItude(Context context) {
            return getString(context, LOCATION, LATITUDE);
        }

        public static String getCity(Context context) {
            return getString(context, LOCATION, CITY);
        }

        public static void putDetailAddreds(Context context, String detailAddress) {
            putString(context, LOCATION, DETAIL_ADDREDS, detailAddress);
        }

        public static String getDetailAddreds(Context context) {
            return getString(context, LOCATION, DETAIL_ADDREDS);
        }

        public static String getBaiduId(Context context) {
            return getString(context, LOCATION, BAIDU_ID);
        }

        public static void putData(Context context, String city, String longItude, String latItude,
                                   String detailAddress, String bid) {
            putString(context, LOCATION, CITY, city);
            putString(context, LOCATION, LONGITUDE, longItude);
            putString(context, LOCATION, LATITUDE, latItude);
            putString(context, LOCATION, BAIDU_ID, bid);
            putDetailAddreds(context, detailAddress);
        }

    }

    private static void putString(Context context, String sharedName, String name, String value) {
        PreferencesUtils.putString(context, name, value);
    }

    private static String getString(Context context, String sharedName, String name) {
        return PreferencesUtils.getString(context, name);

    }

    /**
     * 返回当前的areacode
     *
     * @return
     */
    public static int getCurrentAreaCode() {
        if (BuildConfig.DEBUG)
            return 441900;
        String bid = LbsMgr.sLocation.getCityCode();
        try {
            List<CityEntity> carEntityList = DbMgr.daoSession.getCityEntityDao().queryBuilder()
                    .where(CityEntityDao.Properties.Bid.eq(bid)).limit(1).list();
            if (carEntityList != null && carEntityList.size() > 0)
                return carEntityList.get(0).getId();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
