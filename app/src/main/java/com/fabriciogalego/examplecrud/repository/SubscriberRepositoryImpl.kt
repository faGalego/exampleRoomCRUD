package com.fabriciogalego.examplecrud.repository

import androidx.lifecycle.LiveData
import com.fabriciogalego.examplecrud.data.db.dao.SubscriberDao
import com.fabriciogalego.examplecrud.data.db.entity.SubscriberEntity

class SubscriberRepositoryImpl(
    private val subscriberDao: SubscriberDao
) : SubscriberRepository {

    override suspend fun insertSubscriber(name: String, email: String): Long {
        val subscriber = SubscriberEntity(
            name = name,
            email = email
        )
        return subscriberDao.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntity(
            id = id,
            name = name,
            email = email
        )
        subscriberDao.udpate(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscriberDao.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDao.deleteAll()
    }

    override suspend fun getAllSubscribers(): List<SubscriberEntity> {
        return subscriberDao.getAll()
    }
}