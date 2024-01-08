package com.example.myapplication.ui.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.Pembeli
import com.example.myapplication.data.PembeliRepository

class EditViewModel(application: Application) : AndroidViewModel(application) {

    private val pembeliRepository: PembeliRepository = PembeliRepository(application)
    val semuaPembeli: LiveData<List<Pembeli>> = pembeliRepository.ambilSemuaPembeli()

    fun tambahPembeli(jenisMotor: String, totalHarga: Int) {
        val pembeli = Pembeli(0, jenisMotor, totalHarga)
        pembeliRepository.tambahPembeli(pembeli)
    }

    fun perbaruiPembeli(id: Int, jenisMotor: String, totalHarga: Int) {
        val pembeli = Pembeli(id, jenisMotor, totalHarga)
        pembeliRepository.perbaruiPembeli(pembeli)
    }

    fun hapusPembeli(id: Int) {
        pembeliRepository.hapusPembeli(id)
    }
}
