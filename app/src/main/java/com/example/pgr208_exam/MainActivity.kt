package com.example.pgr208_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pgr208_exam.adapter.FeatureAdapter
import com.example.pgr208_exam.api.FeatureCollectionListener
import com.example.pgr208_exam.api.GetFeatureCollection
import com.example.pgr208_exam.gsontypes.Feature
import com.example.pgr208_exam.gsontypes.FeatureCollection
import com.example.pgr208_exam.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),FeatureCollectionListener {

    var url = "https://www.noforeignland.com/home/api/v1/places/"

    lateinit var adapter:  FeatureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implement network avaible method

        adapter = FeatureAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter;

        if(Utils.isNetworkAvailable(this)) {
            GetFeatureCollection(this).execute(url);
        } else {
            Toast.makeText(this, getString(R.string.connectivity_error), Toast.LENGTH_LONG).show()
        }


    }

    private fun updateFeatures(features: ArrayList<Feature>) {

        adapter.list = features // update list
        adapter.notifyDataSetChanged() // Notify the adapter that data has been updated
    }

    override fun onFeaturesSuccess(collection: FeatureCollection) {
        //Sending only the features list as an arrayList
        updateFeatures(ArrayList(collection.getFeatures()))
    }

    override fun onFeaturesError() {
        Toast.makeText(this, getString(R.string.feature_error), Toast.LENGTH_LONG).show()
    }

}
