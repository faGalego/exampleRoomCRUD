package com.fabriciogalego.examplecrud.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fabriciogalego.examplecrud.data.db.entity.SubscriberEntity

@Dao
interface SubscriberDao {

    @Insert
    suspend fun insert(subscriberEntity: SubscriberEntity): Long

    @Update
    suspend fun udpate(subscriberEntity: SubscriberEntity)

    @Query("DELETE FROM subscriber WHERE ID = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM subscriber")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber")
    fun getAll(): LiveData<List<SubscriberEntity>>

}