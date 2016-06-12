package com.afc.db.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class BindingModelsJiLu implements java.io.Serializable {

    private Long _id;
    private String image;
    private String brandId;
    private String brandName;
    private String autoSeries;
    private String autoSeriesName;
    private String breedId;
    private String breedIdName;
    private String pid;
    private String memberId;
    private String modifiedDate;
    private String licenseNumber;
    private String vin;
    private String engineNumber;
    private String CitySpell;
    private String InsuranceExpirationDate;
    private String InsuranceComName;
    private String AutoPowerTypeName;
    private String OilIndicatorName;
    private int InsuranceComID;
    private int OilIndicator;
    private int AutoPowerType;
    private boolean IsDefaultCar;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public BindingModelsJiLu() {
    }

    public BindingModelsJiLu(Long _id) {
        this._id = _id;
    }

    public BindingModelsJiLu(Long _id, String image, String brandId, String brandName, String autoSeries, String autoSeriesName, String breedId, String breedIdName, String pid, String memberId, String modifiedDate, String licenseNumber, String vin, String engineNumber, String CitySpell, String InsuranceExpirationDate, String InsuranceComName, String AutoPowerTypeName, String OilIndicatorName, int InsuranceComID, int OilIndicator, int AutoPowerType, Boolean IsDefaultCar) {
        this._id = _id;
        this.image = image;
        this.brandId = brandId;
        this.brandName = brandName;
        this.autoSeries = autoSeries;
        this.autoSeriesName = autoSeriesName;
        this.breedId = breedId;
        this.breedIdName = breedIdName;
        this.pid = pid;
        this.memberId = memberId;
        this.modifiedDate = modifiedDate;
        this.licenseNumber = licenseNumber;
        this.vin = vin;
        this.engineNumber = engineNumber;
        this.CitySpell = CitySpell;
        this.InsuranceExpirationDate = InsuranceExpirationDate;
        this.InsuranceComName = InsuranceComName;
        this.AutoPowerTypeName = AutoPowerTypeName;
        this.OilIndicatorName = OilIndicatorName;
        this.InsuranceComID = InsuranceComID;
        this.OilIndicator = OilIndicator;
        this.AutoPowerType = AutoPowerType;
        this.IsDefaultCar = IsDefaultCar;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAutoSeries() {
        return autoSeries;
    }

    public void setAutoSeries(String autoSeries) {
        this.autoSeries = autoSeries;
    }

    public String getAutoSeriesName() {
        return autoSeriesName;
    }

    public void setAutoSeriesName(String autoSeriesName) {
        this.autoSeriesName = autoSeriesName;
    }

    public String getBreedId() {
        return breedId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public String getBreedIdName() {
        return breedIdName;
    }

    public void setBreedIdName(String breedIdName) {
        this.breedIdName = breedIdName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getCitySpell() {
        return CitySpell;
    }

    public void setCitySpell(String CitySpell) {
        this.CitySpell = CitySpell;
    }

    public String getInsuranceExpirationDate() {
        return InsuranceExpirationDate;
    }

    public void setInsuranceExpirationDate(String InsuranceExpirationDate) {
        this.InsuranceExpirationDate = InsuranceExpirationDate;
    }

    public String getInsuranceComName() {
        return InsuranceComName;
    }

    public void setInsuranceComName(String InsuranceComName) {
        this.InsuranceComName = InsuranceComName;
    }

    public String getAutoPowerTypeName() {
        return AutoPowerTypeName;
    }

    public void setAutoPowerTypeName(String AutoPowerTypeName) {
        this.AutoPowerTypeName = AutoPowerTypeName;
    }

    public String getOilIndicatorName() {
        return OilIndicatorName;
    }

    public void setOilIndicatorName(String OilIndicatorName) {
        this.OilIndicatorName = OilIndicatorName;
    }

    public int getInsuranceComID() {
        return InsuranceComID;
    }

    public void setInsuranceComID(Integer InsuranceComID) {
        this.InsuranceComID = InsuranceComID;
    }

    public int getOilIndicator() {
        return OilIndicator;
    }

    public void setOilIndicator(Integer OilIndicator) {
        this.OilIndicator = OilIndicator;
    }

    public int getAutoPowerType() {
        return AutoPowerType;
    }

    public void setAutoPowerType(Integer AutoPowerType) {
        this.AutoPowerType = AutoPowerType;
    }

    public boolean getIsDefaultCar() {
        return IsDefaultCar;
    }

    public void setIsDefaultCar(Boolean IsDefaultCar) {
        this.IsDefaultCar = IsDefaultCar;
    }

    // KEEP METHODS - put your custom methods here

    public BindingModelsJiLu getItem() {
        return item;
    }

    public void setItem(BindingModelsJiLu item) {
        this.item = item;
    }

    private BindingModelsJiLu item;
    // KEEP METHODS END

}
