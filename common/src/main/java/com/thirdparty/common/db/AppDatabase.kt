package com.thirdparty.common.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thirdparty.common.app.GlobalApplication
import com.thirdparty.common.db.dao.ChatDao
import com.thirdparty.common.db.entity.ChatEntity


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.common.db
 *  @文件名:   AppDatabase
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/21 13:58
 *  @描述：    TODO
 */
@Database(entities = [ChatEntity::class],exportSchema = false,version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getChatDao():ChatDao

    companion object {
        private const val DB_NAME = "app_database"
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(): AppDatabase {
            instance?.let { return it }
            return Room.databaseBuilder(GlobalApplication.getContext(),
                AppDatabase::class.java,
                DB_NAME)
                .fallbackToDestructiveMigration() //仅测试用
                .build()
                .apply { instance = this }
        }
    }
}