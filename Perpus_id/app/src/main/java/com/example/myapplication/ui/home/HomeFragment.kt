package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        val adapter = PembeliAdapter()

        // Set up item click listener for RecyclerView
        adapter.setOnItemClickListener { pembeli ->
            // Navigate to EditFragment with the buyer ID as an argument
            val bundle = Bundle().apply {
                putInt("pembeliId", pembeli.id)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_editFragment,
                bundle
            )
        }
        binding.fabCreate.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tambahFragment)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Initialize TextView to display a message if there is no data
        val noDataTextView = binding.noDataTextView

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Observe LiveData from ViewModel to get buyer data
        viewModel.semuaPembeli.observe(viewLifecycleOwner, Observer { pembeliList ->
            if (pembeliList.isEmpty()) {
                // Display the message "No buyer data"
                noDataTextView.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                // Hide the message and show RecyclerView if there is data
                noDataTextView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE

                // Update RecyclerView when the data changes
                adapter.submitList(pembeliList)
            }
        })
        viewModel.refreshDataPembeli()
    }
}
