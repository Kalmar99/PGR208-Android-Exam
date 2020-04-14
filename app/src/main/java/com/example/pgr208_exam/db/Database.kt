package com.example.pgr208_exam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

val DB_NAME = "feature_cache"
val DB_VERSION = 1
val FEATURE_COLLECTION_TABLE = "feature_collection"
val FEATURE_SINGLE_TABLE = "feature_single"


open class Database(context: Context) : SQLiteOpenHelper(context,DB_NAME,null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        //Table 1
        val sql = """
            CREATE TABLE ${FEATURE_COLLECTION_TABLE}(
            id INTEGER PRIMARY KEY UNIQUE NOT NULL,
            name TEXT,
            lat REAL,
            lon REAL,
            icon TEXT);
            """
        db?.execSQL(sql)

        val sql2 = """
            CREATE TABLE ${FEATURE_SINGLE_TABLE}(
            id INTEGER PRIMARY KEY UNIQUE NOT NULL,
            banner TEXT,
            comments TEXT);
            """

        //Table 2
        db?.execSQL(sql2)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //DO nothing right now
    }

}

