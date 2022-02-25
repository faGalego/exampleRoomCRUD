package com.fabriciogalego.examplecrud.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fabriciogalego.examplecrud.data.db.dao.SubscriberDao
import com.fabriciogalego.examplecrud.data.db.entity.SubscriberEntity

@Database(entities = [SubscriberEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val subscriberDao: SubscriberDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance: AppDatabase? = INSTANCE
                if (instance == null){
                    instance =  Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "subscriber_database"
                    ).build()
                }
                return instance
            }
        }
    }
}