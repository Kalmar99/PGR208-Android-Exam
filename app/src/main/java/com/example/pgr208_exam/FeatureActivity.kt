package com.example.pgr208_exam

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class FeatureActivity : AppCompatActivity() {

    companion object{
        const val FEATURE_ID = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        var featureId: Long;

        if(this?.intent.hasExtra(FEATURE_ID)){
            featureId = this?.intent.getLongExtra(FEATURE_ID,0)
        } else {
            featureId = 0;
        }

        println("Feature Id: " + featureId);
    }


}