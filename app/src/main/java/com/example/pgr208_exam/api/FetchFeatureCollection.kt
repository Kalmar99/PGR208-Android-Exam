package com.example.pgr208_exam.api

import android.content.Context
import android.text.PrecomputedText
import com.example.pgr208_exam.db.AbstractDao
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.db.FeatureCollectionDao
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.google.gson.Gson
import java.lang.Exception

class FetchFeatureCollection(listener: AsyncListener<Feature>?,val context: Context) : AbstractFetch<Feature>(listener) {

    override fun doInBackground(vararg params: String?): ArrayList<Feature> {
        publishProgress(0)

        val featureCollectionDao = FeatureCollectionDao(context)

        val features = getDataFromDb(featureCollectionDao,"SELECT * FROM feature_test")

        if(features != null) {
            return features
        } else {
            return getDataFromApi(params.get(0))
        }

    }

    fun getDataFromDb(dao: AbstractDao<Feature>,sql: String) : ArrayList<Feature>? {
        val featureArray = dao.fetchAll(sql)
        if(featureArray.isEmpty()) {
            return null;
        } else {
            return featureArray
        }
    }

    fun getDataFromApi(url: String?) : ArrayList<Feature> {
        try {
            var response = webRequest(url!!)
            var responseList = parseJson(response)
            return responseList;
        } catch (ex: Exception) {
            throw(ex)
        }
    }

    override fun parseJson(json: String): ArrayList<Feature> {
        var gson = Gson()

        var collection = gson.fromJson(
            json,
            FeatureCollection::class.java
        )

        var ret = ArrayList<Feature>(collection.getFeatures())

        return ret
    }


}