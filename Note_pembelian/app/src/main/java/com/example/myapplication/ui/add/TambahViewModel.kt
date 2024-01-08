package com.example.myapplication.ui.add

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Pembeli
import com.example.myapplication.data.PembeliRepository

class TambahViewModel : ViewModel() {

    private lateinit var pembeliRepository: PembeliRepository

    fun init(pembeliRepository: PembeliRepository) {
        this.pembeliRepository = pembeliRepository
    }

    fun tambahPembeli(pembeli: Pembeli) {
        pembeliRepository.tambahPembeli(pembeli)
    }
}
