package com.afc.db.entity.project;

import java.io.Serializable;

import com.afc.event.PictureEntity;

/**
 * @author houen.bao
 * @date Jun 17, 2016 3:41:13 PM
 */
public class ProjectListEntity implements Serializable {

	private String Title; // 项目名称
	private int Category;// 类别
	private int Field;// 投资领域
	private String Intro;// 简介
	private int MemberId;// 发布项目人
	private int ProjectStatus;// 项目状态
	private int Status;// 审核状态
	private String Detail;// 详情
	private PictureEntity Picture;// 图片
	private String FieldName;// 投资领域名称
	private String pid;
	private String CreateTime;// 创建时间
	private String ModifyTime;// 更新时间

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public int getCategory() {
		return Category;
	}

	public void setCategory(int category) {
		Category = category;
	}

	public int getField() {
		return Field;
	}

	public void setField(int field) {
		Field = field;
	}

	public String getIntro() {
		return Intro;
	}

	public void setIntro(String intro) {
		Intro = intro;
	}

	public int getMemberId() {
		return MemberId;
	}

	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	public int getProjectStatus() {
		return ProjectStatus;
	}

	public void setProjectStatus(int projectStatus) {
		ProjectStatus = projectStatus;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
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

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
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

	public String getModifyTime() {
		return ModifyTime;
	}

	public void setModifyTime(String modifyTime) {
		ModifyTime = modifyTime;
	}

}
