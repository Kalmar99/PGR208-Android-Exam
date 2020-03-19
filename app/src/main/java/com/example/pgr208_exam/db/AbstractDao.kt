package com.example.pgr208_exam.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.pgr208_exam.gsontypes.collection.Feature
import java.lang.Exception

abstract class AbstractDao<T>(val context: Context,val database: SQLiteDatabase){


    //abstract val database: SQLiteDatabase

    abstract fun insert(features: List<T>)
    abstract fun createObject(cursor: Cursor) : T

    fun fetchAll(sql: String) : ArrayList<T> {

        val db = database;

        val cursor = db.rawQuery(sql,null)

        var features = ArrayList<T>()


        with(cursor) {
            try {
                while(moveToNext()) {

                    features.add(createObject(cursor));
                }
            } catch(ex: Exception) {
                // If data is not found in database this should return null so i can try api call
                throw ex;
            } finally {
                cursor.close();
            }

        }


        return features;

    }
}