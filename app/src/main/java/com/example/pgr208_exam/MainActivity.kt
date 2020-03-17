package com.example.pgr208_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pgr208_exam.api.GetFeatureCollection

class MainActivity : AppCompatActivity() {

    var url = "https://www.noforeignland.com/home/api/v1/places/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implement network avaible method
        val featureCollection = GetFeatureCollection().execute(url);




    }
}
