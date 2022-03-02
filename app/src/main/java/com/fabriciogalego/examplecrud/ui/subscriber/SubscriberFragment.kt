package com.fabriciogalego.examplecrud.ui.subscriber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fabriciogalego.examplecrud.data.db.AppDatabase
import com.fabriciogalego.examplecrud.data.db.dao.SubscriberDao
import com.fabriciogalego.examplecrud.databinding.SubscriberFragmentBinding
import com.fabriciogalego.examplecrud.extension.hideKeyboard
import com.fabriciogalego.examplecrud.repository.SubscriberRepository
import com.fabriciogalego.examplecrud.repository.SubscriberRepositoryImpl
import com.google.android.material.snackbar.Snackbar

class SubscriberFragment : Fragment() {

    private var _binding: SubscriberFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val subscriberDao: SubscriberDao =
                    AppDatabase.getInstance(requireContext()).subscriberDao

                val repository: SubscriberRepository = SubscriberRepositoryImpl(subscriberDao)

                return SubscriberViewModel(repository) as T
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SubscriberFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setListeners()
    }

    private fun setObservers() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()

                    findNavController().popBackStack()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) { messageId ->
            Toast.makeText(context, messageId, Toast.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        binding.subscriberFragmentNameEditText.text?.clear()
        binding.subscriberFragmentEmailEditText.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        binding.subscriberFragmentAddButton.setOnClickListener {
            val name = binding.subscriberFragmentNameEditText.text.toString()
            val email = binding.subscriberFragmentEmailEditText.text.toString()
            viewModel.addSubscriber(name, email)
        }
    }
}