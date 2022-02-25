package com.fabriciogalego.examplecrud.ui.subscriberlist

import androidx.lifecycle.ViewModel
import com.fabriciogalego.examplecrud.repository.SubscriberRepository

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    val allSubscribersEvent = repository.getAllSubscribers()

}