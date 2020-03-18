package com.example.pgr208_exam.api

import com.example.pgr208_exam.gsontypes.single.Feature
import com.google.gson.Gson

class FetchFeature(listener: AsyncListener<Feature>?) : AbstractFetch<Feature>(listener) {


    override fun onPostExecute(result: Feature) {
        super.onPostExecute(result)

        listener?.showProgress(false)

        if (result != null) {
            if (result.place === null) {
                listener?.onFeaturesError()
            } else {
                listener?.onFeaturesSuccess(result)
            }
        } else {
            listener?.onFeaturesError()

        }


    }

    override fun parseJson(json:String) : Feature {
        var gson = Gson()

        val feature = gson.fromJson(json,
            Feature::class.java)

        return feature
    }

}