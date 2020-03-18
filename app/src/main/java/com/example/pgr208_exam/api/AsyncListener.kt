package com.example.pgr208_exam.api

interface AsyncListener<T> {

    fun onFeaturesSuccess(collection: T)
    fun onFeaturesError()
    fun showProgress(show: Boolean)
}