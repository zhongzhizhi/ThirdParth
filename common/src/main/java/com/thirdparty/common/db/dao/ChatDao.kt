package com.thirdparty.common.db.dao

import androidx.room.*
import com.thirdparty.common.db.entity.ChatEntity


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.common.db.dao
 *  @文件名:   ChatDao
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/21 14:26
 *  @描述：    TODO
 */
@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChat(entity: ChatEntity): Long

    @Update
    fun updateChat(entity: ChatEntity): Int

    @Delete
    fun deleteChat(entity: ChatEntity): Int

    @Query("SELECT * FROM chat")
    fun queryChat(): Array<ChatEntity>

    @Query("SELECT * FROM chat WHERE from_device=:sessionId")
    fun queryChatFromDevice(sessionId: String): Array<ChatEntity>

    @Query("SELECT * FROM chat WHERE to_device=:sessionId")
    fun queryChatToDevice(sessionId: String): Array<ChatEntity>
}