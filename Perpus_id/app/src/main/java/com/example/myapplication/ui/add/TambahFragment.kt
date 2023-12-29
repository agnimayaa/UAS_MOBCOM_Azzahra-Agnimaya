package com.example.myapplication.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.Pembeli
import com.example.myapplication.data.PembeliRepository
import com.example.myapplication.databinding.FragmentTambahBinding


class TambahFragment : Fragment() {

    private lateinit var binding: FragmentTambahBinding
    private lateinit var viewModel: TambahViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTambahBinding.inflate(inflater, container, false)

        // Initialize viewModel before accessing it
        viewModel = ViewModelProvider(this).get(TambahViewModel::class.java)
        viewModel.init(PembeliRepository(requireContext()))

        // Bind the viewModel variable to the layout
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The rest of your onViewCreated code...
        binding.btnTambah.setOnClickListener {
            tambahPembeli()
        }
    }

    private fun tambahPembeli() {
        val jenisMotor = binding.editJenisMotor.text.toString()
        val totalHarga = binding.editTotalHarga.text.toString().toIntOrNull() ?: 0

        if (jenisMotor.isNotBlank()) {
            val pembeli = Pembeli(id = 0, jenisMotor = jenisMotor, totalHarga = totalHarga)
            viewModel.tambahPembeli(pembeli)
            findNavController().navigateUp()
        }
    }
}
