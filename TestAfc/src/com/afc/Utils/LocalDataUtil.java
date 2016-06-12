package com.afc.Utils;

import android.content.Context;
import android.text.TextUtils;

import com.afc.db.entity.SercodeData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.util.Logger;

import java.util.List;

import cn.trinea.android.common.util.ListUtils;

/**
 * Created by Administrator on 2016/5/5.
 */
public class LocalDataUtil {
    private static List<SercodeData> cacheList;

    public static void initAll(Context context) {
      /*  initSercodeData(context);
        initWeatherData(context);
        initChepaiData(context);
        initCitydataData(context);
        initCityWithBaiduData(context);*/

    }

    /**
     * 加载sercode.txt数据并插入数据库
     * @param context
     */
    /*public static void initSercodeData(Context context) {
        if (hasLoaded(SercodeData.class) > 0) return;

        String data = parseData(context, "sercode.txt");
        if (TextUtils.isEmpty(data))
            return;
        List<SercodeData> list = JSON.parseArray(data, SercodeData.class);
        DbMgr.daoSession.getSercodeDataDao().deleteAll();
        DbMgr.daoSession.getSercodeDataDao().insertInTx(list);
        Logger.i("插入sercode数据：" + list.size() + "条");
    }*/


    /**
     * 加载weather.txt数据并插入数据库
     * @param context
     */
   /* public static void initWeatherData(Context context) {
        if (hasLoaded(WeatherData.class) > 0) return;

        String data = parseData(context, "weather.txt");
        if (TextUtils.isEmpty(data))
            return;
        List<WeatherData> list = JSON.parseArray(data, WeatherData.class);
        DbMgr.daoSession.getWeatherDataDao().deleteAll();
        DbMgr.daoSession.getWeatherDataDao().insertInTx(list);
        Logger.i("插入weather数据：" + list.size() + "条");
    }*/

    /**
     * 加载chepai.txt数据并插入数据库
     * @param context
     */
    /*public static void initChepaiData(Context context) {
        if (hasLoaded(ChepaiData.class) > 0) return;

        String data = KuUtils.getFromAssets("chepai.txt", context);
        if (TextUtils.isEmpty(data))
            return;
        final List<ChepaiData> list = JSON.parseArray(data, ChepaiData.class);

        DbMgr.daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                DbMgr.daoSession.getChepaiDataDao().deleteAll();
                if (!ListUtils.isEmpty(list)) {
                    DbMgr.daoSession.getCityItemDao().deleteAll();
                    for (ChepaiData chepai : list) {
                        long id = DbMgr.daoSession.getChepaiDataDao().insert(chepai);
                        List<CityItem> temp = chepai.getItems();
                        for (CityItem item : temp) {
                            item.set_id(id);
                        }
                        DbMgr.daoSession.getCityItemDao().insertInTx(chepai.getItems());
                    }
                }
            }
        });

        Logger.i("插入chepai数据：" + list.size() + "条");
    }*/

    /**
     * 加载citydata.txt数据并插入数据库
     * @param context
     */
    /*public static void initCitydataData(Context context) {
        if (hasLoaded(CitydataData.class) > 0) return;

        String data = parseData(context, "citydata.txt");
        if (TextUtils.isEmpty(data))
            return;
        List<CitydataData> list = JSON.parseArray(data, CitydataData.class);
        DbMgr.daoSession.getCitydataDataDao().deleteAll();
        DbMgr.daoSession.getCitydataDataDao().insertInTx(list);
        Logger.i("插入citydata数据：" + list.size() + "条");
    }*/

    /**
     * 加载citywithbaiduid.txt数据并插入数据库
     * @param context
     */
   /* public static void initCityWithBaiduData(Context context) {
        if (hasLoaded(CityEntity.class) > 0) return;

        String data = parseData(context, "citywithbaiduid.txt");
        if (TextUtils.isEmpty(data))
            return;
        List<CityEntity> list = JSON.parseArray(data, CityEntity.class);
        DbMgr.daoSession.getCityEntityDao().deleteAll();
        DbMgr.daoSession.getCityEntityDao().insertInTx(list);
        Logger.i("插入citywithbaiduid数据：" + list.size() + "条");
    }*/


    /**
     * 加载指定文件中的json数据
     * @param context
     * @param fileName
     * @return
     */
   /* private static String parseData(Context context, String fileName) {
        String json = KuUtils.getFromAssets(fileName, context);
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("responsedata");
    }*/

    /**
     * 判断指定类型的数据是否已被加载
     * @param
     * @return
     */
    /*private static long hasLoaded(Class clazz) {
        long count = DbMgr.daoSession.getDao(clazz).count();
        Logger.i(clazz.getName()+ "已存在" + count + "条数据");
        return count;
    }

    public static List<SercodeData> getSercodeData() {
        if (cacheList != null) {
            return cacheList;
        }
        return DbMgr.daoSession.getSercodeDataDao().loadAll();
    }*/


    /*public static CityEntity getBaiduCityByBid(Context context, String bid) {
        return DbMgr.daoSession.getCityEntityDao().queryBuilder().where(CityEntityDao.Properties.Bid.eq(bid)).unique();
    }

    public interface OnQueryEndListener{
        void onQueryEnd(List<Class> result);
    }*/

}
