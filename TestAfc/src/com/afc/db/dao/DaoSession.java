package com.afc.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.afc.db.entity.BindingModelsJiLu;
import com.afc.db.entity.ChepaiData;
import com.afc.db.entity.CityEntity;
import com.afc.db.entity.CityItem;
import com.afc.db.entity.CitydataData;
import com.afc.db.entity.FootPrint;
import com.afc.db.entity.KuMessage;
import com.afc.db.entity.SearchColl;
import com.afc.db.entity.SercodeData;
import com.afc.db.entity.SortModelEntity;
import com.afc.db.entity.StringEntity;
import com.afc.db.entity.WeatherData;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Administrator on 2016/5/5.
 */
public class DaoSession extends AbstractDaoSession {
    private final DaoConfig kuMessageDaoConfig;
    private final DaoConfig sortModelEntityDaoConfig;
    private final DaoConfig bindingModelsJiLuDaoConfig;
    private final DaoConfig cityEntityDaoConfig;
    private final DaoConfig stringEntityDaoConfig;
    private final DaoConfig searchCollDaoConfig;
    private final DaoConfig footPrintDaoConfig;
    private final DaoConfig sercodeDataDaoConfig;
    private final DaoConfig weatherDataDaoConfig;
    private final DaoConfig chepaiDataDaoConfig;
    private final DaoConfig cityItemDaoConfig;
    private final DaoConfig citydataDataDaoConfig;

    private final KuMessageDao kuMessageDao;
    private final SortModelEntityDao sortModelEntityDao;
    private final BindingModelsJiLuDao bindingModelsJiLuDao;
    private final CityEntityDao cityEntityDao;
    private final StringEntityDao stringEntityDao;
    private final SearchCollDao searchCollDao;
    private final FootPrintDao footPrintDao;
    private final SercodeDataDao sercodeDataDao;
    private final WeatherDataDao weatherDataDao;
    private final ChepaiDataDao chepaiDataDao;
    private final CityItemDao cityItemDao;
    private final CitydataDataDao citydataDataDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        kuMessageDaoConfig = daoConfigMap.get(KuMessageDao.class).clone();
        kuMessageDaoConfig.initIdentityScope(type);

        sortModelEntityDaoConfig = daoConfigMap.get(SortModelEntityDao.class).clone();
        sortModelEntityDaoConfig.initIdentityScope(type);

        bindingModelsJiLuDaoConfig = daoConfigMap.get(BindingModelsJiLuDao.class).clone();
        bindingModelsJiLuDaoConfig.initIdentityScope(type);

        cityEntityDaoConfig = daoConfigMap.get(CityEntityDao.class).clone();
        cityEntityDaoConfig.initIdentityScope(type);

        stringEntityDaoConfig = daoConfigMap.get(StringEntityDao.class).clone();
        stringEntityDaoConfig.initIdentityScope(type);

        searchCollDaoConfig = daoConfigMap.get(SearchCollDao.class).clone();
        searchCollDaoConfig.initIdentityScope(type);

        footPrintDaoConfig = daoConfigMap.get(FootPrintDao.class).clone();
        footPrintDaoConfig.initIdentityScope(type);

        sercodeDataDaoConfig = daoConfigMap.get(SercodeDataDao.class).clone();
        sercodeDataDaoConfig.initIdentityScope(type);

        weatherDataDaoConfig = daoConfigMap.get(WeatherDataDao.class).clone();
        weatherDataDaoConfig.initIdentityScope(type);

        chepaiDataDaoConfig = daoConfigMap.get(ChepaiDataDao.class).clone();
        chepaiDataDaoConfig.initIdentityScope(type);

        cityItemDaoConfig = daoConfigMap.get(CityItemDao.class).clone();
        cityItemDaoConfig.initIdentityScope(type);

        citydataDataDaoConfig = daoConfigMap.get(CitydataDataDao.class).clone();
        citydataDataDaoConfig.initIdentityScope(type);

        kuMessageDao = new KuMessageDao(kuMessageDaoConfig, this);
        sortModelEntityDao = new SortModelEntityDao(sortModelEntityDaoConfig, this);
        bindingModelsJiLuDao = new BindingModelsJiLuDao(bindingModelsJiLuDaoConfig, this);
        cityEntityDao = new CityEntityDao(cityEntityDaoConfig, this);
        stringEntityDao = new StringEntityDao(stringEntityDaoConfig, this);
        searchCollDao = new SearchCollDao(searchCollDaoConfig, this);
        footPrintDao = new FootPrintDao(footPrintDaoConfig, this);
        sercodeDataDao = new SercodeDataDao(sercodeDataDaoConfig, this);
        weatherDataDao = new WeatherDataDao(weatherDataDaoConfig, this);
        chepaiDataDao = new ChepaiDataDao(chepaiDataDaoConfig, this);
        cityItemDao = new CityItemDao(cityItemDaoConfig, this);
        citydataDataDao = new CitydataDataDao(citydataDataDaoConfig, this);

        registerDao(KuMessage.class, kuMessageDao);
        registerDao(SortModelEntity.class, sortModelEntityDao);
        registerDao(BindingModelsJiLu.class, bindingModelsJiLuDao);
        registerDao(CityEntity.class, cityEntityDao);
        registerDao(StringEntity.class, stringEntityDao);
        registerDao(SearchColl.class, searchCollDao);
        registerDao(FootPrint.class, footPrintDao);
        registerDao(SercodeData.class, sercodeDataDao);
        registerDao(WeatherData.class, weatherDataDao);
        registerDao(ChepaiData.class, chepaiDataDao);
        registerDao(CityItem.class, cityItemDao);
        registerDao(CitydataData.class, citydataDataDao);
    }

    public void clear() {
        kuMessageDaoConfig.getIdentityScope().clear();
        sortModelEntityDaoConfig.getIdentityScope().clear();
        bindingModelsJiLuDaoConfig.getIdentityScope().clear();
        cityEntityDaoConfig.getIdentityScope().clear();
        stringEntityDaoConfig.getIdentityScope().clear();
        searchCollDaoConfig.getIdentityScope().clear();
        footPrintDaoConfig.getIdentityScope().clear();
        sercodeDataDaoConfig.getIdentityScope().clear();
        weatherDataDaoConfig.getIdentityScope().clear();
        chepaiDataDaoConfig.getIdentityScope().clear();
        cityItemDaoConfig.getIdentityScope().clear();
        citydataDataDaoConfig.getIdentityScope().clear();
    }

    public KuMessageDao getKuMessageDao() {
        return kuMessageDao;
    }

    public SortModelEntityDao getSortModelEntityDao() {
        return sortModelEntityDao;
    }

    public BindingModelsJiLuDao getBindingModelsJiLuDao() {
        return bindingModelsJiLuDao;
    }

    public CityEntityDao getCityEntityDao() {
        return cityEntityDao;
    }

    public StringEntityDao getStringEntityDao() {
        return stringEntityDao;
    }

    public SearchCollDao getSearchCollDao() {
        return searchCollDao;
    }

    public FootPrintDao getFootPrintDao() {
        return footPrintDao;
    }

    public SercodeDataDao getSercodeDataDao() {
        return sercodeDataDao;
    }

    public WeatherDataDao getWeatherDataDao() {
        return weatherDataDao;
    }

    public ChepaiDataDao getChepaiDataDao() {
        return chepaiDataDao;
    }

    public CityItemDao getCityItemDao() {
        return cityItemDao;
    }

    public CitydataDataDao getCitydataDataDao() {
        return citydataDataDao;
    }

}
