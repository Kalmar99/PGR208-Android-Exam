package com.example.pgr208_exam.api

import com.example.pgr208_exam.gsontypes.collection.FeatureCollection

interface FeatureCollectionListener<T> {

    fun onFeaturesSuccess(collection: T)
    fun onFeaturesError()
    fun showProgress(show: Boolean)
}