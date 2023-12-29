package com.example.myapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pembeli(
    val id: Int,
    val jenisMotor: String,
    val totalHarga: Int
) : Parcelable
