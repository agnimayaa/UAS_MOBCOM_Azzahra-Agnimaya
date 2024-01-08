package com.example.myapplication.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.Pembeli
import com.example.myapplication.data.PembeliRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    // Initialize the repository with the Application context as needed
    private val pembeliRepository: PembeliRepository = PembeliRepository(application)

    // LiveData to store the list of all buyers
    val semuaPembeli: LiveData<List<Pembeli>> = pembeliRepository.ambilSemuaPembeli()

    fun refreshDataPembeli() {
        pembeliRepository.refreshDataPembeli()
    }
}
