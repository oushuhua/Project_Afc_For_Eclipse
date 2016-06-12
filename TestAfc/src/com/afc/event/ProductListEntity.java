package com.afc.event;

import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ProductListEntity implements Serializable {

    private String Title; //产品名称
    private int Type; //1会员发布2审核通过3审核不通过
    private int Process; //项目进程
    private String StartScale;//启动规模
    private String CurrentScale;//本期规模
    private String ContinueScale;//存续规模
    private String StartTerm;//启动状态期限
    private String ActualTerm;//购买状态期限
    private String BaseInvest;//起投
    private int Category;//类别
    private int Profit;//收益
    private String ExpectProfit;//预期收益
    private String FinalProfit;//最终受益
    private String Networth;//最新净值
    private String PmComment;//产品经理点评
    private String Detail;//详情
    private PictureEntity Picture;//图片
    private String CategoryName;//产品类别名称
    private String ProfitName; //产品收益名称
    private String ProcessName;//产品进程名称
    private String pid;//
    private String CreateTime;//创建时间
    private String ModifyTime;//更新时间


    public String getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(String modifyTime) {
        ModifyTime = modifyTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getProcess() {
        return Process;
    }

    public void setProcess(int process) {
        Process = process;
    }

    public String getStartScale() {
        return StartScale;
    }

    public void setStartScale(String startScale) {
        StartScale = startScale;
    }

    public String getCurrentScale() {
        return CurrentScale;
    }

    public void setCurrentScale(String currentScale) {
        CurrentScale = currentScale;
    }

    public String getContinueScale() {
        return ContinueScale;
    }

    public void setContinueScale(String continueScale) {
        ContinueScale = continueScale;
    }

    public String getStartTerm() {
        return StartTerm;
    }

    public void setStartTerm(String startTerm) {
        StartTerm = startTerm;
    }

    public String getActualTerm() {
        return ActualTerm;
    }

    public void setActualTerm(String actualTerm) {
        ActualTerm = actualTerm;
    }

    public String getBaseInvest() {
        return BaseInvest;
    }

    public void setBaseInvest(String baseInvest) {
        BaseInvest = baseInvest;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public int getProfit() {
        return Profit;
    }

    public void setProfit(int profit) {
        Profit = profit;
    }

    public String getExpectProfit() {
        return ExpectProfit;
    }

    public void setExpectProfit(String expectProfit) {
        ExpectProfit = expectProfit;
    }

    public String getFinalProfit() {
        return FinalProfit;
    }

    public void setFinalProfit(String finalProfit) {
        FinalProfit = finalProfit;
    }

    public String getNetworth() {
        return Networth;
    }

    public void setNetworth(String networth) {
        Networth = networth;
    }

    public String getPmComment() {
        return PmComment;
    }

    public void setPmComment(String pmComment) {
        PmComment = pmComment;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public PictureEntity getPicture() {
        return Picture;
    }

    public void setPicture(PictureEntity picture) {
        Picture = picture;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getProfitName() {
        return ProfitName;
    }

    public void setProfitName(String profitName) {
        ProfitName = profitName;
    }

    public String getProcessName() {
        return ProcessName;
    }

    public void setProcessName(String processName) {
        ProcessName = processName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
