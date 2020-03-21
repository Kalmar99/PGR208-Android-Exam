package com.example.pgr208_exam.api

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.pgr208_exam.db.FEATURE_COLLECTION_TABLE
import com.example.pgr208_exam.db.FEATURE_SINGLE_TABLE
import com.example.pgr208_exam.db.FeatureDao
import com.example.pgr208_exam.gsontypes.single.Feature
import com.google.gson.Gson

class FetchFeature(listener: AsyncListener<Feature>?,val cacheListener: AsyncCacheListener<Feature>?,val context: Context,val db: SQLiteDatabase,val id: Long) : AbstractFetch<Feature>(listener) {

    override fun doInBackground(vararg params: String?): ArrayList<Feature> {
        publishProgress(0)

        val dao = FeatureDao(context,db)

        val features = getDataFromDb(dao,
            """
            SELECT * FROM ${FEATURE_COLLECTION_TABLE} 
            JOIN ${FEATURE_SINGLE_TABLE} 
            ON ${FEATURE_COLLECTION_TABLE}.id = ${FEATURE_SINGLE_TABLE}.id
            WHERE ${FEATURE_COLLECTION_TABLE}.id = ${id};
            """.trimIndent()
            )

        if(features != null && features[0].place.id.equals(id) ) {
            //if it can find data in database, use that
            Log.i("DATA: ", "from db")
            return features
        } else {
            //If not get data from api
            Log.i("DATA: ", "from Api")
            val features = getDataFromApi(params.get(0))
            //Start a new async task to insert the data in the db
            cacheListener?.onDownloadInBackground(dao,features)
            return features;
        }

    }

    override fun parseJson(json:String) : ArrayList<Feature> {
        var gson = Gson()

        val feature = gson.fromJson(json,
            Feature::class.java)

        var features = ArrayList<Feature>()
        features.add(feature)

        return features
    }

}