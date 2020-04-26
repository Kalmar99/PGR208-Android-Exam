package com.example.pgr208_exam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.marginLeft
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

    lateinit var db: SQLiteDatabase;
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

        db = Database(this).writableDatabase

        if(Utils.isNetworkAvailable(this)) {
            FetchFeature(this,this,this,db,featureId).execute(url);
        } else {
            Toast.makeText(this, getString(R.string.connectivity_error), Toast.LENGTH_LONG).show()
        }
    }

    fun getFacilityDrawable(facility: String) : Int {
        //The reason why im doing this manually is becouse i found no documentation on this api.
        // It could be more icons for facilites than i cover here so im doing a where so i can set a default if there is a icon i havent covered here
        val result = when(facility) {
            "elec" -> R.drawable.ic_plug_solid
            "toilets" -> R.drawable.ic_restroom_solid
            "bar" -> R.drawable.ic_beer_solid
            "wifi" -> R.drawable.ic_wifi_solid
            "pump" -> R.drawable.ic_poo_solid
            "water" -> R.drawable.ic_tint_solid
            "showers" -> R.drawable.ic_shower_solid
            "food" -> R.drawable.ic_utensils_solid
            else -> R.drawable.ic_question_solid
        }
        if(result == R.drawable.ic_question_solid) {
            //So i can spot new icons when im testing
            println("Found unknown icon: " + facility)
        }

        return result
    }

    fun getFacilityViews(facilities: List<String>) : ArrayList<View> {

        val list = ArrayList<View>()

        for(facility in facilities) {
            val view = ImageView(this)
            view.setImageDrawable(getDrawable(getFacilityDrawable(facility)))

            //The code that gets width,Height and Margin in dp is copied from here: https://stackoverflow.com/questions/35354032/how-to-set-layout-params-to-units-dp-android
            val width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                resources.getDimension(R.dimen.facility_icon_width),
                resources.displayMetrics
            ).toInt()

            val height = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                resources.getDimension(R.dimen.facility_icon_height),
                resources.displayMetrics
            ).toInt()

            val margin = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                resources.getDimension(R.dimen.facility_icon_margin),
                resources.displayMetrics
            ).toInt()

            val params = LinearLayout.LayoutParams(width,height);
            params.setMargins(margin,margin,margin,margin)
            view.layoutParams = params;


            list.add(view)
        }

        return list
    }

    override fun onFeaturesSuccess(collection: ArrayList<Feature>) {
        titleText.text = collection[0].place.name
        val dieselpriceValue = collection[0].place.dieselPrice;
        val gasolinepriceValue = collection[0].place.gasolinePrice
        val protectedFromValue = collection[0].place.protectionFrom

        if(collection[0].place.meta.facilities != null) {
            val facilitiesInfo = collection[0].place.meta.facilities.toString()

            if(!facilitiesInfo.equals("Unknown")) {
                val facilityIcons = getFacilityViews(facilitiesInfo.split(","))
                for(facility in facilityIcons) {
                    facilitiesIcons.addView(facility)
                }
            }
        } else {
            facilitiesInfo.visibility = View.GONE;
        }

        if(dieselpriceValue.equals(0.0)) {
            dieselprice.text = getString(R.string.diesel,getString(R.string.unknown))
        } else {
            dieselprice.text = getString(R.string.diesel,dieselpriceValue.toString())
        }

        if(gasolinepriceValue.compareTo(0).equals(0)) {
            gasolineprice.text = getString(R.string.gasoline,getString(R.string.unknown))
        } else {
            gasolineprice.text = getString(R.string.gasoline,gasolinepriceValue.toString())
        }

        if(protectedFromValue.isEmpty()) {
            protectedFrom.text = getString(R.string.protectedFrom,getString(R.string.unknown))
        } else {
            protectedFrom.text = getString(R.string.protectedFrom,protectedFromValue)
        }

        var path = collection[0].place?.getBanner()
        if(path != null) {
            if(path.isEmpty()) {path = "http//"}
        }

        println(collection[0].toString() )
            Picasso.get()
                .load(path)
                .placeholder(R.drawable.notfound)
                .error(R.drawable.notfound)
                .into(banner)

        locationIcon.setOnClickListener(View.OnClickListener {
            var intent = Intent(this,MapsActivity::class.java)

            println("Location: LAT: " + collection[0].place.lat + "LON: " + collection[0].place.lon)

            intent.putExtra("lat",collection[0].place.lat)
            intent.putExtra("lon",collection[0].place.lon)
            intent.putExtra("name",collection[0].place.name)
            startActivity(intent);
        })
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

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }


}