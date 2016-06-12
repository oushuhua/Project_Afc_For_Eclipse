package com.afc.db.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class KuMessage implements java.io.Serializable{

    private Long _id;
    private int reply_ID;
    private int reply_ContentType;
    private int reply_MessageType;
    private int qes_ID;
    private int reply_From_UserType;
    private int fromApp;
    private int reply_State;
    private boolean selfSended;
    private boolean isFailed;
    private String reply_Message;
    private String reply_AddTime;
    private String reply_From_ID;
    private String reply_To_ID;
    private int direct;
    private String orderNumber;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public KuMessage() {
    }

    public KuMessage(Long _id) {
        this._id = _id;
    }

    public KuMessage(Long _id, Integer reply_ID, Integer reply_ContentType, Integer reply_MessageType, Integer qes_ID, Integer reply_From_UserType, Integer fromApp, Integer reply_State, Boolean selfSended, Boolean isFailed, String reply_Message, String reply_AddTime, String reply_From_ID, String reply_To_ID, Integer direct, String orderNumber) {
        this._id = _id;
        this.reply_ID = reply_ID;
        this.reply_ContentType = reply_ContentType;
        this.reply_MessageType = reply_MessageType;
        this.qes_ID = qes_ID;
        this.reply_From_UserType = reply_From_UserType;
        this.fromApp = fromApp;
        this.reply_State = reply_State;
        this.selfSended = selfSended;
        this.isFailed = isFailed;
        this.reply_Message = reply_Message;
        this.reply_AddTime = reply_AddTime;
        this.reply_From_ID = reply_From_ID;
        this.reply_To_ID = reply_To_ID;
        this.direct = direct;
        this.orderNumber = orderNumber;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Integer getReply_ID() {
        return reply_ID;
    }

    public void setReply_ID(Integer reply_ID) {
        this.reply_ID = reply_ID;
    }

    public Integer getReply_ContentType() {
        return reply_ContentType;
    }

    public void setReply_ContentType(Integer reply_ContentType) {
        this.reply_ContentType = reply_ContentType;
    }

    public Integer getReply_MessageType() {
        return reply_MessageType;
    }

    public void setReply_MessageType(Integer reply_MessageType) {
        this.reply_MessageType = reply_MessageType;
    }

    public Integer getQes_ID() {
        return qes_ID;
    }

    public void setQes_ID(Integer qes_ID) {
        this.qes_ID = qes_ID;
    }

    public Integer getReply_From_UserType() {
        return reply_From_UserType;
    }

    public void setReply_From_UserType(Integer reply_From_UserType) {
        this.reply_From_UserType = reply_From_UserType;
    }

    public Integer getFromApp() {
        return fromApp;
    }

    public void setFromApp(Integer fromApp) {
        this.fromApp = fromApp;
    }

    public Integer getReply_State() {
        return reply_State;
    }

    public void setReply_State(Integer reply_State) {
        this.reply_State = reply_State;
    }

    public Boolean getSelfSended() {
        return selfSended;
    }

    public void setSelfSended(Boolean selfSended) {
        this.selfSended = selfSended;
    }

    public Boolean getIsFailed() {
        return isFailed;
    }

    public void setIsFailed(Boolean isFailed) {
        this.isFailed = isFailed;
    }

    public String getReply_Message() {
        return reply_Message;
    }

    public void setReply_Message(String reply_Message) {
        this.reply_Message = reply_Message;
    }

    public String getReply_AddTime() {
        return reply_AddTime;
    }

    public void setReply_AddTime(String reply_AddTime) {
        this.reply_AddTime = reply_AddTime;
    }

    public String getReply_From_ID() {
        return reply_From_ID;
    }

    public void setReply_From_ID(String reply_From_ID) {
        this.reply_From_ID = reply_From_ID;
    }

    public String getReply_To_ID() {
        return reply_To_ID;
    }

    public void setReply_To_ID(String reply_To_ID) {
        this.reply_To_ID = reply_To_ID;
    }

    public Integer getDirect() {
        return direct;
    }

    public void setDirect(Integer direct) {
        this.direct = direct;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END
}
