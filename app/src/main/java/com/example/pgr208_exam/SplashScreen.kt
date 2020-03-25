package com.example.pgr208_exam


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pgr208_exam.api.AsyncCacheListener
import com.example.pgr208_exam.api.AsyncListener
import com.example.pgr208_exam.api.FetchFeatureCollection
import com.example.pgr208_exam.db.AbstractDao
import com.example.pgr208_exam.db.CacheData
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.utils.Utils
import kotlinx.android.synthetic.main.splash_screen.*

class SplashScreen : AppCompatActivity(), AsyncListener<Feature>, AsyncCacheListener<Feature> {

    var doneDownload = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        var url = "https://www.noforeignland.com/home/api/v1/places/"

        val db = Database(this).writableDatabase

        if(Utils.isNetworkAvailable(this)) {
            FetchFeatureCollection(this,this,this,db).execute(url);
        } else {
            Toast.makeText(this, getString(R.string.connectivity_error), Toast.LENGTH_LONG).show()
        }

    }

    fun nextScreen() {
        val intent = Intent(this,MainActivity::class.java);
        startActivity(intent);
        finish()
    }

    override fun onFeaturesSuccess(collection: ArrayList<Feature>) {
        if(doneDownload) {
            nextScreen();
        }
    }

    override fun onFeaturesError() {
        Log.w("Error: ","An Error occured when trying to get features")
    }

    override fun onDownloadInBackground(dao: AbstractDao<Feature>, features: ArrayList<Feature>) {
        //Cache the data
        doneDownload = false;
        CacheData<Feature>(dao,features,this).execute(null)
    }

    override fun onBackgroundDownloadComplete() {
        doneDownload = true;
        nextScreen();
    }

    override fun updateBackground(progress: Int) {
        cacheProgressBar.setProgress(progress)
    }
    override fun onUpdateBackground(show: Boolean) {
        cacheLayout.visibility = if (show) View.VISIBLE else View.GONE
    }
    override fun showProgress(show: Boolean) {}

}