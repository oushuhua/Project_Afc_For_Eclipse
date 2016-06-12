package com.afc.db.entity;

import android.content.Context;

import com.afc.Utils.AccountMgr;
import com.afc.db.dao.KuMessageDao;

import cn.trinea.android.common.util.TimeUtils;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

/**
 * Created by Administrator on 2016/5/5.
 */
public class KuMessageFactory {
    /**
     * 生成一条车主发送的消息模板
     *
     * @return
     */
    public static KuMessage createMessage(Context context) {
        KuMessage message = new KuMessage();
        String uid = AccountMgr.getMemberId(context);
        message.setReply_From_ID(uid);
        message.setFromApp(0);
        message.setReply_From_UserType(1);
        message.setReply_MessageType(3);
        message.setReply_ContentType(1);
        message.setReply_AddTime(TimeUtils.getTime(System.currentTimeMillis()));
        message.setSelfSended(true);
        return message;
    }

    /**
     * 生成一条车主接收的消息模板
     *
     * @return
     */
    public static KuMessage createReceiveMessage(Context context) {
        KuMessage message = new KuMessage();
        String uid =  AccountMgr.getMemberId(context);
        message.setReply_To_ID(uid);
        message.setFromApp(0);
        message.setReply_From_UserType(2);// 消息发送者用户类型（车主1，服务商2,客服3）
        message.setReply_MessageType(3);
        message.setReply_ContentType(1);
        message.setReply_AddTime(TimeUtils.getTime(System.currentTimeMillis()));
        message.setSelfSended(false);
        return message;
    }

    /**
     * 对指定聊天对象的记录筛选
     *
     * @param toId
     * @param builder
     * @return
     */
    public static WhereCondition defaultCondition(String toId, String myid, QueryBuilder<KuMessage> builder) {
        return builder.and(
                builder.or(KuMessageDao.Properties.Reply_From_ID.like(toId + "%"),
                        KuMessageDao.Properties.Reply_To_ID.like(toId + "%")),
                builder.or(KuMessageDao.Properties.Reply_From_ID.like(myid + "%"),
                        KuMessageDao.Properties.Reply_To_ID.like(myid + "%")));
    }
}
