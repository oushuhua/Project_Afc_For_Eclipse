package com.afc.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/17.
 */
public class PictureEntity implements Serializable {

    private int ForeignId;//关联ID
    private String Name;//文档名称
    private String LinkName;//文档链接名称
    private int Project;//文档项目  0:会员动态 1:活动2：图标3：资产图片4：吐槽5：项目6：产品7：会员头像
    private int Category;//文档类型 0：图片1：文档2：视频
    private String MD5;//文件MD5值
    private String Uri;//文件路径
    private int pid;
    private String CreateTime;//创建时间
    private String ModifyTime;//更新时间

    public int getForeignId() {
        return ForeignId;
    }

    public void setForeignId(int foreignId) {
        ForeignId = foreignId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLinkName() {
        return LinkName;
    }

    public void setLinkName(String linkName) {
        LinkName = linkName;
    }

    public int getProject() {
        return Project;
    }

    public void setProject(int project) {
        Project = project;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(String modifyTime) {
        ModifyTime = modifyTime;
    }
}
