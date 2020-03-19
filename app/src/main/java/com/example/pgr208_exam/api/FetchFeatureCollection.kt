package com.example.pgr208_exam.api

import android.content.Context
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.db.FeatureCollectionDao
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.google.gson.Gson
import java.lang.Exception

class FetchFeatureCollection(listener: AsyncListener<Feature>?,val context: Context) : AbstractFetch<Feature>(listener) {



    override fun doInBackground(vararg params: String?): ArrayList<Feature> {
        publishProgress(0)

        val db = Database(context)
        val featureCollectionDao = FeatureCollectionDao(context)

        try {

            val collection = featureCollectionDao.fetchAll("SELECT * FROM feature_test")
            if(collection.isEmpty())
            {
                println("Cant find in db")
                throw (Exception())
            } else {
                println("FOUND IN DB")
                return collection;
            }
        } catch (ex: Exception) {
            //Cant find data in database
            try {
                var response = webRequest(params.get(0)!!)
                var responseList = parseJson(response)
                println("FOUND IN API")
                return responseList;
            } catch (ex: Exception) {
                //Real error
                throw(ex)
            }
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