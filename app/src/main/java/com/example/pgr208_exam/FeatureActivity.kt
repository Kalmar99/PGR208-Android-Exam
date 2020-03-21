package com.example.pgr208_exam

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.example.pgr208_exam.api.AsyncCacheListener
import com.example.pgr208_exam.api.AsyncListener
import com.example.pgr208_exam.api.FetchFeature
import com.example.pgr208_exam.db.AbstractDao
import com.example.pgr208_exam.db.CacheData
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.gsontypes.single.Feature
import com.example.pgr208_exam.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_feature.*

class FeatureActivity : AppCompatActivity(), AsyncListener<Feature>, AsyncCacheListener<Feature> {

    companion object{
        const val FEATURE_ID = ""
    }

    var endpoint = "https://www.noforeignland.com/home/api/v1/place?id="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        var featureId: Long;

        if(this.intent.hasExtra(FEATURE_ID)){
            featureId = this.intent.getLongExtra(FEATURE_ID,0)
        } else {
            featureId = 0;
        }

        var url = endpoint + featureId + "\n"

        val db = Database(this).writableDatabase

        if(Utils.isNetworkAvailable(this)) {
            FetchFeature(this,this,this,db,featureId).execute(url);
        } else {
            Toast.makeText(this, getString(R.string.connectivity_error), Toast.LENGTH_LONG).show()
        }
    }

    override fun onFeaturesSuccess(collection: ArrayList<Feature>) {
        titleText.text = collection[0].place.name

            var path = collection[0].place?.getBanner()
            if(path != null) {
                if(path.isEmpty()) {path = "http//"}
            }

            Picasso.get()
                .load(path)
                .placeholder(R.drawable.notfound)
                .error(R.drawable.notfound)
                .into(banner)


        comments.text = HtmlCompat.fromHtml(collection[0].place.comments,0);
    }

    override fun onFeaturesError() {
        Log.w("Error: ","An Error occured when trying to get features")
    }

    override fun showProgress(show: Boolean) {

        if (isFinishing ) {
            return;
        }

        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onUpdateBackground(show: Boolean) {}
    override fun updateBackground(progress: Int) {}
    override fun onBackgroundDownloadComplete() {}

    override fun onDownloadInBackground(dao: AbstractDao<Feature>, features: ArrayList<Feature>) {
        CacheData<Feature>(dao,features,this).execute(null)
    }

}