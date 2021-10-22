package com.thirdparty.common.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.common.db
 *  @文件名:   ChatEntity
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/20 18:40
 *  @描述：    TODO
 */
@Entity(tableName = "chat")
data class ChatEntity(
    //消息id
    @ColumnInfo(name = "msg_id") var msgId: String,
    //消息类型（文本，图片，音频，红包...）
    @ColumnInfo(name = "msg_type") var msgType: Int,
    //聊天类型（单聊，群聊）
    @ColumnInfo(name = "chat_type") var chatType: Int,
    //消息时间戳
    @ColumnInfo(name = "msg_timestamp") var msgTimestamp: Long,
    //是否是未读消息（目前主要用在音频接收消息显示未读红点）
    @ColumnInfo(name = "unread") var isUnread: Boolean,
    //消息来自哪个终端
    @ColumnInfo(name = "from_device") var fromDevice: String,
    //消息接收的终端
    @ColumnInfo(name = "to_device") var toDevice: String,
    //消息的状态（发送中，发送成功，发送失败）
    @ColumnInfo(name = "msg_state") var msgState: Int,
    //消息内容
    @ColumnInfo(name = "msg_body") var msgBody: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
