package com.fabriciogalego.examplecrud.ui.subscriberlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.fabriciogalego.examplecrud.R
import com.fabriciogalego.examplecrud.data.db.AppDatabase
import com.fabriciogalego.examplecrud.data.db.dao.SubscriberDao
import com.fabriciogalego.examplecrud.data.db.entity.SubscriberEntity
import com.fabriciogalego.examplecrud.databinding.SubscriberListFragmentBinding
import com.fabriciogalego.examplecrud.extension.navigateWithAnimations
import com.fabriciogalego.examplecrud.repository.SubscriberRepository
import com.fabriciogalego.examplecrud.repository.SubscriberRepositoryImpl
import com.fabriciogalego.examplecrud.ui.subscriber.SubscriberViewModel

class SubscriberListFragment : Fragment() {

    private var _binding: SubscriberListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val subscriberDao: SubscriberDao =
                    AppDatabase.getInstance(requireContext()).subscriberDao

                val repository: SubscriberRepository = SubscriberRepositoryImpl(subscriberDao)

                return SubscriberListViewModel(repository) as T
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SubscriberListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSubscribers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListeners()
    }

    private fun setObservers() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) { allSubscribers ->
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers)
            binding.subscriberListFragmentRecyclerView.run {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }
    }

    private fun setListeners() {
        binding.subscriberListFragmentAddFab.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.subscriberFragment)
        }
    }


}