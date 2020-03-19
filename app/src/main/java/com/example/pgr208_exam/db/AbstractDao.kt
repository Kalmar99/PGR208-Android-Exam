package com.example.pgr208_exam.db

import android.content.Context
import android.database.Cursor
import com.example.pgr208_exam.gsontypes.collection.Feature
import java.lang.Exception

abstract class AbstractDao<T>(context: Context) : Database(context){


    abstract fun insert(features: List<T>)
    abstract fun createObject(cursor: Cursor) : T

    fun fetchAll(sql: String) : ArrayList<T> {

        val cursor = readableDatabase.rawQuery(sql,null)

        var features = ArrayList<T>()

        with(cursor) {
            try {
                while(moveToNext()) {

                    features.add(createObject(cursor));
                }
            } catch(ex: Exception) {
                // If data is not found in database this should return null so i can try api call
                throw ex;
            }

        }


        return features;

    }
}