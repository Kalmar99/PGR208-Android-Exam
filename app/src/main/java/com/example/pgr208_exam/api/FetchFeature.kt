package com.example.pgr208_exam.api

import com.example.pgr208_exam.gsontypes.single.Feature
import com.google.gson.Gson

class FetchFeature(listener: AsyncListener<Feature>?) : AbstractFetch<Feature>(listener) {




    override fun parseJson(json:String) : ArrayList<Feature> {
        var gson = Gson()

        val feature = gson.fromJson(json,
            Feature::class.java)

        var ret = ArrayList<Feature>()
        ret.add(feature)

        return ret
    }

}