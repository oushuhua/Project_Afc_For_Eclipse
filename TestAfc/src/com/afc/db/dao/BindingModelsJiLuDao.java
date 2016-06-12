package com.afc.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.afc.db.entity.BindingModelsJiLu;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Administrator on 2016/5/5.
 */
public class BindingModelsJiLuDao extends AbstractDao<BindingModelsJiLu, Long> {

    public static final String TABLENAME = "BINDING_MODELS_JI_LU";

    /**
     * Properties of entity BindingModelsJiLu.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_ID");
        public final static Property Image = new Property(1, String.class, "image", false, "IMAGE");
        public final static Property BrandId = new Property(2, String.class, "brandId", false, "BRAND_ID");
        public final static Property BrandName = new Property(3, String.class, "brandName", false, "BRAND_NAME");
        public final static Property AutoSeries = new Property(4, String.class, "autoSeries", false, "AUTO_SERIES");
        public final static Property AutoSeriesName = new Property(5, String.class, "autoSeriesName", false, "AUTO_SERIES_NAME");
        public final static Property BreedId = new Property(6, String.class, "breedId", false, "BREED_ID");
        public final static Property BreedIdName = new Property(7, String.class, "breedIdName", false, "BREED_ID_NAME");
        public final static Property Pid = new Property(8, String.class, "pid", false, "PID");
        public final static Property MemberId = new Property(9, String.class, "memberId", false, "MEMBER_ID");
        public final static Property ModifiedDate = new Property(10, String.class, "modifiedDate", false, "MODIFIED_DATE");
        public final static Property LicenseNumber = new Property(11, String.class, "licenseNumber", false, "LICENSE_NUMBER");
        public final static Property Vin = new Property(12, String.class, "vin", false, "VIN");
        public final static Property EngineNumber = new Property(13, String.class, "engineNumber", false, "ENGINE_NUMBER");
        public final static Property CitySpell = new Property(14, String.class, "CitySpell", false, "CITY_SPELL");
        public final static Property InsuranceExpirationDate = new Property(15, String.class, "InsuranceExpirationDate", false, "INSURANCE_EXPIRATION_DATE");
        public final static Property InsuranceComName = new Property(16, String.class, "InsuranceComName", false, "INSURANCE_COM_NAME");
        public final static Property AutoPowerTypeName = new Property(17, String.class, "AutoPowerTypeName", false, "AUTO_POWER_TYPE_NAME");
        public final static Property OilIndicatorName = new Property(18, String.class, "OilIndicatorName", false, "OIL_INDICATOR_NAME");
        public final static Property InsuranceComID = new Property(19, Integer.class, "InsuranceComID", false, "INSURANCE_COM_ID");
        public final static Property OilIndicator = new Property(20, Integer.class, "OilIndicator", false, "OIL_INDICATOR");
        public final static Property AutoPowerType = new Property(21, Integer.class, "AutoPowerType", false, "AUTO_POWER_TYPE");
        public final static Property IsDefaultCar = new Property(22, Boolean.class, "IsDefaultCar", false, "IS_DEFAULT_CAR");
    };


    public BindingModelsJiLuDao(DaoConfig config) {
        super(config);
    }

    public BindingModelsJiLuDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BINDING_MODELS_JI_LU\" (" + //
                "\"_ID\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"IMAGE\" TEXT," + // 1: image
                "\"BRAND_ID\" TEXT," + // 2: brandId
                "\"BRAND_NAME\" TEXT," + // 3: brandName
                "\"AUTO_SERIES\" TEXT," + // 4: autoSeries
                "\"AUTO_SERIES_NAME\" TEXT," + // 5: autoSeriesName
                "\"BREED_ID\" TEXT," + // 6: breedId
                "\"BREED_ID_NAME\" TEXT," + // 7: breedIdName
                "\"PID\" TEXT," + // 8: pid
                "\"MEMBER_ID\" TEXT," + // 9: memberId
                "\"MODIFIED_DATE\" TEXT," + // 10: modifiedDate
                "\"LICENSE_NUMBER\" TEXT," + // 11: licenseNumber
                "\"VIN\" TEXT," + // 12: vin
                "\"ENGINE_NUMBER\" TEXT," + // 13: engineNumber
                "\"CITY_SPELL\" TEXT," + // 14: CitySpell
                "\"INSURANCE_EXPIRATION_DATE\" TEXT," + // 15: InsuranceExpirationDate
                "\"INSURANCE_COM_NAME\" TEXT," + // 16: InsuranceComName
                "\"AUTO_POWER_TYPE_NAME\" TEXT," + // 17: AutoPowerTypeName
                "\"OIL_INDICATOR_NAME\" TEXT," + // 18: OilIndicatorName
                "\"INSURANCE_COM_ID\" INTEGER," + // 19: InsuranceComID
                "\"OIL_INDICATOR\" INTEGER," + // 20: OilIndicator
                "\"AUTO_POWER_TYPE\" INTEGER," + // 21: AutoPowerType
                "\"IS_DEFAULT_CAR\" INTEGER);"); // 22: IsDefaultCar
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BINDING_MODELS_JI_LU\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BindingModelsJiLu entity) {
        stmt.clearBindings();

        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }

        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(2, image);
        }

        String brandId = entity.getBrandId();
        if (brandId != null) {
            stmt.bindString(3, brandId);
        }

        String brandName = entity.getBrandName();
        if (brandName != null) {
            stmt.bindString(4, brandName);
        }

        String autoSeries = entity.getAutoSeries();
        if (autoSeries != null) {
            stmt.bindString(5, autoSeries);
        }

        String autoSeriesName = entity.getAutoSeriesName();
        if (autoSeriesName != null) {
            stmt.bindString(6, autoSeriesName);
        }

        String breedId = entity.getBreedId();
        if (breedId != null) {
            stmt.bindString(7, breedId);
        }

        String breedIdName = entity.getBreedIdName();
        if (breedIdName != null) {
            stmt.bindString(8, breedIdName);
        }

        String pid = entity.getPid();
        if (pid != null) {
            stmt.bindString(9, pid);
        }

        String memberId = entity.getMemberId();
        if (memberId != null) {
            stmt.bindString(10, memberId);
        }

        String modifiedDate = entity.getModifiedDate();
        if (modifiedDate != null) {
            stmt.bindString(11, modifiedDate);
        }

        String licenseNumber = entity.getLicenseNumber();
        if (licenseNumber != null) {
            stmt.bindString(12, licenseNumber);
        }

        String vin = entity.getVin();
        if (vin != null) {
            stmt.bindString(13, vin);
        }

        String engineNumber = entity.getEngineNumber();
        if (engineNumber != null) {
            stmt.bindString(14, engineNumber);
        }

        String CitySpell = entity.getCitySpell();
        if (CitySpell != null) {
            stmt.bindString(15, CitySpell);
        }

        String InsuranceExpirationDate = entity.getInsuranceExpirationDate();
        if (InsuranceExpirationDate != null) {
            stmt.bindString(16, InsuranceExpirationDate);
        }

        String InsuranceComName = entity.getInsuranceComName();
        if (InsuranceComName != null) {
            stmt.bindString(17, InsuranceComName);
        }

        String AutoPowerTypeName = entity.getAutoPowerTypeName();
        if (AutoPowerTypeName != null) {
            stmt.bindString(18, AutoPowerTypeName);
        }

        String OilIndicatorName = entity.getOilIndicatorName();
        if (OilIndicatorName != null) {
            stmt.bindString(19, OilIndicatorName);
        }

        Integer InsuranceComID = entity.getInsuranceComID();
        if (InsuranceComID != null) {
            stmt.bindLong(20, InsuranceComID);
        }

        Integer OilIndicator = entity.getOilIndicator();
        if (OilIndicator != null) {
            stmt.bindLong(21, OilIndicator);
        }

        Integer AutoPowerType = entity.getAutoPowerType();
        if (AutoPowerType != null) {
            stmt.bindLong(22, AutoPowerType);
        }

        Boolean IsDefaultCar = entity.getIsDefaultCar();
        if (IsDefaultCar != null) {
            stmt.bindLong(23, IsDefaultCar ? 1L: 0L);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public BindingModelsJiLu readEntity(Cursor cursor, int offset) {
        BindingModelsJiLu entity = new BindingModelsJiLu( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // image
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // brandId
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // brandName
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // autoSeries
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // autoSeriesName
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // breedId
                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // breedIdName
                cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // pid
                cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // memberId
                cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // modifiedDate
                cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // licenseNumber
                cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // vin
                cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // engineNumber
                cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // CitySpell
                cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // InsuranceExpirationDate
                cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // InsuranceComName
                cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // AutoPowerTypeName
                cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // OilIndicatorName
                cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // InsuranceComID
                cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20), // OilIndicator
                cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // AutoPowerType
                cursor.isNull(offset + 22) ? null : cursor.getShort(offset + 22) != 0 // IsDefaultCar
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BindingModelsJiLu entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setImage(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBrandId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBrandName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAutoSeries(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAutoSeriesName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBreedId(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBreedIdName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPid(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMemberId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setModifiedDate(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setLicenseNumber(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setVin(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setEngineNumber(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setCitySpell(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setInsuranceExpirationDate(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setInsuranceComName(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setAutoPowerTypeName(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setOilIndicatorName(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setInsuranceComID(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setOilIndicator(cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20));
        entity.setAutoPowerType(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
        entity.setIsDefaultCar(cursor.isNull(offset + 22) ? null : cursor.getShort(offset + 22) != 0);
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BindingModelsJiLu entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(BindingModelsJiLu entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

}
