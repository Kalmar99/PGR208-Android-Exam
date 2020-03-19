package com.example.pgr208_exam.api

import android.content.Context
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.db.FeatureCollectionDao
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.google.gson.Gson
import java.lang.Exception

class FetchFeatureCollection(listener: AsyncListener<FeatureCollection>?,val context: Context) : AbstractFetch<FeatureCollection>(listener) {



    override fun doInBackground(vararg params: String?): FeatureCollection {
        publishProgress(0)

        val db = Database(context)
        val featureCollectionDao = FeatureCollectionDao(context)

        try {

            val collection = featureCollectionDao.fetchAll()
            if(collection.getFeatures().isEmpty())
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
                featureCollectionDao.insert(responseList.getFeatures())
                return responseList;
            } catch (ex: Exception) {
                //Real error
                throw(ex)
            }
        }

        /*
        try {

            var response = webRequest(params.get(0)!!)
            var responseList = parseJson(response)
            return responseList;

        } catch(ex: Exception) {
            ex.printStackTrace()
            throw (ex)
        } */

    }

    fun insertToDb(featureCollection: FeatureCollection) {



    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)

        listener?.showProgress(true)

    }

    override fun onPostExecute(result: FeatureCollection) {
        super.onPostExecute(result)

        listener?.showProgress(false)

        if (result != null) {
            if (result.getFeatures().isEmpty()) {
                listener?.onFeaturesError()
            } else {
                listener?.onFeaturesSuccess(result)
            }
        } else {
            listener?.onFeaturesError()

        }


    }

    override fun parseJson(json: String): FeatureCollection {
        var gson = Gson()

        val collection = gson.fromJson(
            json,
            FeatureCollection::class.java
        )

        return collection
    }


}