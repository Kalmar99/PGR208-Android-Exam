package com.example.pgr208_exam.api

interface AsyncListener<T> {

    fun onFeaturesSuccess(collection: ArrayList<T>)
    fun onFeaturesError()
    fun showProgress(show: Boolean)
}