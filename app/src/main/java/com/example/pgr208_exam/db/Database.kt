package com.example.pgr208_exam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


val DATABASE_VERSION: Int = 1
val DATABASE_NAME: String = "feature_test"

open class Database(context: Context,db_name: String,db_version: Int,val sql: String) : SQLiteOpenHelper(context,db_name,null, db_version) {

    override fun onCreate(db: SQLiteDatabase?) {

        //Used only for test DB Should not be here in final build
        db?.execSQL("DROP TABLE IF EXISTS ${DATABASE_NAME};")

        val query = sql

        db?.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //DO nothing right now
    }

}

