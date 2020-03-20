package com.example.pgr208_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pgr208_exam.adapter.FeatureAdapter
import com.example.pgr208_exam.api.AsyncListener
import com.example.pgr208_exam.api.FetchFeatureCollection
import com.example.pgr208_exam.db.AbstractDao
import com.example.pgr208_exam.db.CacheData
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),AsyncListener<Feature>, View.OnClickListener, SearchView.OnQueryTextListener {

    var url = "https://www.noforeignland.com/home/api/v1/places/"

    lateinit var adapter:  FeatureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FeatureAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter;
        adapter.onClickListener = this


        searchBar.setOnQueryTextListener(this)

        val db = Database(this).writableDatabase

        if(Utils.isNetworkAvailable(this)) {
            FetchFeatureCollection(this,this,db).execute(url);
        } else {
            Toast.makeText(this, getString(R.string.connectivity_error), Toast.LENGTH_LONG).show()
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.getFilter().filter(newText);
        return false;

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
        adapter.fullList.addAll(features)
        adapter.list = features // update list
        adapter.notifyDataSetChanged() // Notify the adapter that data has been updated
    }

    override fun onFeaturesSuccess(collection: ArrayList<Feature>) {

        if(isFinishing) {
            return;
        }
        //Sending only the features list as an arrayList
        updateFeatures(collection)
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



    override fun onBackgroundDownloadComplete() {
        Toast.makeText(this, "Finished Downloading", Toast.LENGTH_LONG).show()
    }

    override fun updateBackground(progress: Int) {
        cacheProgressBar.setProgress(progress);
    }

    override fun onUpdateBackground(show: Boolean) {
        cacheProgressBar.visibility = if (show) View.VISIBLE else View.GONE
        cacheWarning.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDownloadInBackground(dao: AbstractDao<Feature>,features: ArrayList<Feature>) {
        CacheData<Feature>(dao,features,this).execute(null)
    }


}
