package com.example.myapplication.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentEditBinding

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private lateinit var viewModel: EditViewModel
    private var pembeliId: Int = 0 // Change variable name to reflect the buyer ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)

        // Set up data binding
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // Get buyer ID from arguments (ensure you pass the ID when navigating to this fragment)
        pembeliId = arguments?.getInt("pembeliId") ?: 0 // Change to reflect the buyer ID

        // If pembeliId is not 0, it means we are editing an existing buyer
        viewModel.semuaPembeli.observe(viewLifecycleOwner) { pembeliList ->
            val selectedPembeli = pembeliList.find { it.id == pembeliId }
            selectedPembeli?.let {
                // Adjust the code to use the fields of the Pembeli data class
                binding.editTextJenisMotor.setText(it.jenisMotor)
                binding.editTextTotalHarga.setText(it.totalHarga.toString())
            }
        }

        // Set up Save button for updating existing buyer
        binding.btnSave.setOnClickListener {
            val jenisMotor = binding.editTextJenisMotor.text.toString()
            val totalHarga = binding.editTextTotalHarga.text.toString().toIntOrNull() ?: 0
            viewModel.perbaruiPembeli(pembeliId, jenisMotor, totalHarga)
            // Add navigation logic to go back to the buyer list
            findNavController().navigateUp()
        }

        // Set up Delete button for deleting existing buyer
        binding.btnDelete.setOnClickListener {
            viewModel.hapusPembeli(pembeliId)
            findNavController().navigateUp()
            // Add navigation logic to go back to the buyer list
        }
    }
}
