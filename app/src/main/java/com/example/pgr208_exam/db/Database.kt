package com.example.pgr208_exam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


val DATABASE_VERSION: Int = 1
val DATABASE_NAME: String = "feature_test"

open class Database(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        //Used only for test DB Should not be here in final build
        db?.execSQL("DROP TABLE IF EXISTS ${DATABASE_NAME};")

        val query = """
            CREATE TABLE ${DATABASE_NAME}(
            id INTEGER PRIMARY KEY UNIQUE NOT NULL,
            name TEXT,
            lat REAL,
            lon REAL);
            """

        db?.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //DO nothing right now
    }

}

