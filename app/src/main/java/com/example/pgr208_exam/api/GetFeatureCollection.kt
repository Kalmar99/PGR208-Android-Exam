package com.example.pgr208_exam.api

import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.google.gson.Gson
class GetFeatureCollection(listener: FeatureCollectionListener<FeatureCollection>?) : AbstractFetch<FeatureCollection>(listener) {

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