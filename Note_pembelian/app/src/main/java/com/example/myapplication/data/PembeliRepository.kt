package com.example.myapplication.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PembeliRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val _semuaPembeli = MutableLiveData<List<Pembeli>>() // Update to use Pembeli data class

    fun tambahPembeli(pembeli: Pembeli) { // Update function name and parameter
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_JENIS_MOTOR, pembeli.jenisMotor) // Update to use jenisMotor
            put(DatabaseHelper.COLUMN_TOTAL_HARGA, pembeli.totalHarga) // Update to use totalHarga
        }
        db.insert(DatabaseHelper.TABLE_PEMBELI, null, values) // Update table name
        db.close()
        refreshDataPembeli()
    }

    @SuppressLint("Range")
    fun ambilSemuaPembeli(): LiveData<List<Pembeli>> { // Update return type
        refreshDataPembeli()
        return _semuaPembeli
    }

    @SuppressLint("Range")
    fun refreshDataPembeli() { // Update function name
        val pembelis = mutableListOf<Pembeli>() // Update to use Pembeli data class
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_PEMBELI}", null) // Update table name

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
                val jenisMotor = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_JENIS_MOTOR)) // Update to use jenisMotor
                val totalHarga = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_TOTAL_HARGA)) // Update to use totalHarga

                val pembeli = Pembeli(id, jenisMotor, totalHarga) // Update to use Pembeli data class
                pembelis.add(pembeli)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        _semuaPembeli.value = pembelis
    }

    fun perbaruiPembeli(pembeli: Pembeli) { // Update function name and parameter
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_JENIS_MOTOR, pembeli.jenisMotor) // Update to use jenisMotor
            put(DatabaseHelper.COLUMN_TOTAL_HARGA, pembeli.totalHarga) // Update to use totalHarga
        }
        db.update(
            DatabaseHelper.TABLE_PEMBELI, // Update table name
            values,
            "${DatabaseHelper.COLUMN_ID} = ?",
            arrayOf(pembeli.id.toString())
        )
        db.close()
        refreshDataPembeli()
    }

    fun hapusPembeli(id: Int) { // Update function name and parameter
        val db = dbHelper.writableDatabase
        db.delete(
            DatabaseHelper.TABLE_PEMBELI, // Update table name
            "${DatabaseHelper.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
        refreshDataPembeli()
    }
}
