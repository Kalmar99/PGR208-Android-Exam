package com.example.pgr208_exam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


val DATABASE_VERSION: Int = 1
val DATABASE_NAME: String = "feature_test"

open class Database(context: Context,db_name: String,db_version: Int) : SQLiteOpenHelper(context,db_name,null, db_version) {

    override fun onCreate(db: SQLiteDatabase?) {

        //Table 1
        val sql = """
            CREATE TABLE feature_test(
            id INTEGER PRIMARY KEY UNIQUE NOT NULL,
            name TEXT,
            lat REAL,
            lon REAL);
            """
        db?.execSQL(sql)

        val sql2 = """
            CREATE TABLE feature_test_single(
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

