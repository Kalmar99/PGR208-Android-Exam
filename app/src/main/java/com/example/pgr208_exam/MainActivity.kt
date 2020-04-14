package com.example.pgr208_exam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pgr208_exam.adapter.FeatureAdapter
import com.example.pgr208_exam.api.AsyncCacheListener
import com.example.pgr208_exam.api.AsyncListener
import com.example.pgr208_exam.api.FetchFeatureCollection
import com.example.pgr208_exam.api.FetchFeatureList
import com.example.pgr208_exam.db.AbstractDao
import com.example.pgr208_exam.db.CacheData
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.db.FEATURE_COLLECTION_TABLE
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),AsyncListener<Feature>, View.OnClickListener, SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener,AsyncCacheListener<Feature> {

    var url = "https://www.noforeignland.com/home/api/v1/places/"

    lateinit var adapter:  FeatureAdapter
    lateinit var db: SQLiteDatabase;

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val search = menu?.findItem(R.id.searchBar)?.actionView as SearchView
        search.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FeatureAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter;
        refreshLayout.setOnRefreshListener(this)
        adapter.onClickListener = this

        db = Database(this).writableDatabase;

        //searchBar.setOnQueryTextListener(this)

        if(Utils.isNetworkAvailable(this)) {
            FetchFeatureList(this,null,this,db).execute(url);
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

        if(v?.tag === -1) {
            //Clicked icon
            val lat = v.getTag(R.id.lat) as Double
            val lon = v.getTag(R.id.lon) as Double
            val name = v.getTag(R.id.name) as String
            //println("Name: $name Lat: $lat Lon: $lon")

            val intent = Intent(this,MapsActivity::class.java)
            intent.putExtra("lat",lat)
            intent.putExtra("lon",lon)
            intent.putExtra("name",name)
            startActivity(intent)


        } else {
            //Clicked title
            var id = v?.tag as Long
            var intent = Intent(this,FeatureActivity::class.java)
            intent.putExtra(FeatureActivity.FEATURE_ID,id)
            startActivity(intent)
        }

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
        println("Updated features")
        refreshLayout.isRefreshing = false;
        updateFeatures(collection)
    }

    override fun onFeaturesError() {
        Toast.makeText(this, getString(R.string.feature_error), Toast.LENGTH_LONG).show()
    }

    override fun showProgress(show: Boolean) {

        if (isFinishing ) {
            return;
        }

    }

    override fun onRefresh() {
        if(Utils.isNetworkAvailable(this)) {
            //val db = Database(this).writableDatabase;
            //Wipe the database
            db.execSQL("""DELETE FROM ${FEATURE_COLLECTION_TABLE}""")
            FetchFeatureCollection(this,this,this,db).execute(url)
        } else {
            Toast.makeText(this, getString(R.string.connectivity_error), Toast.LENGTH_LONG).show()

        }
    }

    override fun onDownloadInBackground(dao: AbstractDao<Feature>, features: ArrayList<Feature>) {
        //Progresstuff
        println("Downloading data")
        CacheData<Feature>(dao,features,this).execute(null)
    }

    override fun onBackgroundDownloadComplete() {
        println("Done downloading")
        cacheProgressBar.hide();
    }

    override fun updateBackground(progress: Int) {
        cacheProgressBar.setProgress(progress);
    }
    override fun onUpdateBackground(show: Boolean) {
        if(show) {
            cacheProgressBar.show()
        } else {
            cacheProgressBar.hide();
        }
        //cacheProgressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        //Closing the db connection
        db.close()
    }


}
