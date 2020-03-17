package com.example.pgr208_exam.api

import com.example.pgr208_exam.gsontypes.FeatureCollection

interface FeatureCollectionListener {

    fun onFeaturesSuccess(collection: FeatureCollection )
    fun onFeaturesError()
    fun showProgress(show: Boolean)
}