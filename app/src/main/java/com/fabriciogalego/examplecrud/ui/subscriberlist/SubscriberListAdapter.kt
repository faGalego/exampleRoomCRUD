package com.fabriciogalego.examplecrud.ui.subscriberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fabriciogalego.examplecrud.R
import com.fabriciogalego.examplecrud.data.db.entity.SubscriberEntity
import com.fabriciogalego.examplecrud.databinding.SubscriberItemBinding

class SubscriberListAdapter(
    private val subscribers: List<SubscriberEntity>
) :
    RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    var onItemClick: ((entity: SubscriberEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        val binding =
            SubscriberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubscriberListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscribers[position])
    }

    override fun getItemCount() = subscribers.size

    inner class SubscriberListViewHolder(binding: SubscriberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val textViewSubscriberName: TextView = binding.subscriberItemName
        private val textViewSubscriberEmail: TextView = binding.subscriberItemEmail

        fun bindView(subscriber: SubscriberEntity) {
            textViewSubscriberName.text = subscriber.name
            textViewSubscriberEmail.text = subscriber.email
            itemView.setOnClickListener {
                onItemClick?.invoke(subscriber)
            }
        }

    }
}