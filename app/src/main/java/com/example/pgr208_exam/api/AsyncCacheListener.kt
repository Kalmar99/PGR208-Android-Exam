package com.example.pgr208_exam.api

import com.example.pgr208_exam.db.AbstractDao

interface AsyncCacheListener<T> {
    fun onDownloadInBackground(dao: AbstractDao<T>, features: ArrayList<T>)
    fun onBackgroundDownloadComplete()
    fun updateBackground(progress: Int)
    fun onUpdateBackground(show: Boolean)
}