package com.example.pgr208_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pgr208_exam.adapter.FeatureAdapter
import com.example.pgr208_exam.api.FeatureCollectionListener
import com.example.pgr208_exam.api.GetFeatureCollection
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.example.pgr208_exam.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),FeatureCollectionListener<FeatureCollection>, View.OnClickListener {

    var url = "https://www.noforeignland.com/home/api/v1/places/"

    lateinit var adapter:  FeatureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FeatureAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter;
        adapter.onClickListener = this

        if(Utils.isNetworkAvailable(this)) {
            GetFeatureCollection(this).execute(url);
        } else {
            Toast.makeText(this, getString(R.string.connectivity_error), Toast.LENGTH_LONG).show()
        }

    }

    override fun onClick(v: View?) {

        var id = v?.tag as Long

        var intent = Intent(this,FeatureActivity::class.java)
        intent.putExtra(FeatureActivity.FEATURE_ID,id)
        startActivity(intent)

    }

    private fun updateFeatures(features: ArrayList<Feature>) {

        if(isFinishing) {
            return;
        }

        adapter.list = features // update list
        adapter.notifyDataSetChanged() // Notify the adapter that data has been updated
    }

    override fun onFeaturesSuccess(collection: FeatureCollection) {

        if(isFinishing) {
            return;
        }

        //Sending only the features list as an arrayList
        updateFeatures(ArrayList(collection.getFeatures()))
    }

    override fun onFeaturesError() {
        Toast.makeText(this, getString(R.string.feature_error), Toast.LENGTH_LONG).show()
    }

    override fun showProgress(show: Boolean) {

        if (isFinishing ) {
            return;
        }

        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

}
