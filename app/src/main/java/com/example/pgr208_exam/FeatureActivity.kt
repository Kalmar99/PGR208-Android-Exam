package com.example.pgr208_exam

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.example.pgr208_exam.api.FeatureCollectionListener
import com.example.pgr208_exam.api.GetFeatureById
import com.example.pgr208_exam.gsontypes.single.Feature
import com.example.pgr208_exam.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_feature.*

class FeatureActivity : AppCompatActivity(), FeatureCollectionListener<Feature> {

    companion object{
        const val FEATURE_ID = ""
    }

    var endpoint = "https://www.noforeignland.com/home/api/v1/place?id="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        var featureId: Long;

        if(this?.intent.hasExtra(FEATURE_ID)){
            featureId = this?.intent.getLongExtra(FEATURE_ID,0)
        } else {
            featureId = 0;
        }

        var url = endpoint + featureId + "\n"

        if(Utils.isNetworkAvailable(this)) {
            GetFeatureById(this).execute(url);
        } else {
            Toast.makeText(this, getString(R.string.connectivity_error), Toast.LENGTH_LONG).show()
        }
    }

    override fun onFeaturesSuccess(collection: Feature) {
        titleText.text = collection.place.name

            var path = collection?.place?.getBanner()
            if(path != null) {
                if(path.isEmpty()) {path = "http//"}
            }

            Picasso.get()
                .load(path)
                .placeholder(R.drawable.notfound)
                .error(R.drawable.notfound)
                .into(banner)


        comments.text = HtmlCompat.fromHtml(collection.place.comments,0);
    }

    override fun onFeaturesError() {
        //Nothing
    }

    override fun showProgress(show: Boolean) {

        if (isFinishing ) {
            return;
        }

        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }


}