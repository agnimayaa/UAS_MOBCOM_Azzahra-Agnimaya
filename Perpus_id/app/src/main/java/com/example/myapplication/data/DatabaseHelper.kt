package com.example.myapplication.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "penjualan_bensin.db"
        private const val DATABASE_VERSION = 4

        // Tabel buku
        const val TABLE_PEMBELI = "pembeli"
        const val COLUMN_ID = "_id"
        const val COLUMN_JENIS_MOTOR = "jenis_motor"
        const val COLUMN_TOTAL_HARGA = "total_harga"
    }

    // Membuat tabel buku
    private val CREATE_TABLE_PEMBELI = (
            "CREATE TABLE $TABLE_PEMBELI ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_JENIS_MOTOR TEXT, $COLUMN_TOTAL_HARGA INTEGER);")


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_PEMBELI)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PEMBELI")
        onCreate(db)
    }
}
