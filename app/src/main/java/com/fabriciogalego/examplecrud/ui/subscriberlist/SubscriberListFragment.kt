package com.fabriciogalego.examplecrud.ui.subscriberlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fabriciogalego.examplecrud.R
import com.fabriciogalego.examplecrud.data.db.entity.SubscriberEntity
import com.fabriciogalego.examplecrud.databinding.SubscriberListFragmentBinding

class SubscriberListFragment : Fragment() {

    private var _binding: SubscriberListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SubscriberListViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subscriberListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "Fabricio Galego", "fabricio@galego.com"),
                SubscriberEntity(2, "Andreia Franco", "andreia@franco.com")
            )
        )

        binding.subscriberListFragmentRecyclerView.run {
            setHasFixedSize(true)
            adapter = subscriberListAdapter
        }

    }
}