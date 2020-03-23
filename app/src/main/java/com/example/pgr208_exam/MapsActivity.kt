package com.example.pgr208_exam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)


        val mapFragment = SupportMapFragment()
        mapFragment.getMapAsync(this)
        supportFragmentManager.beginTransaction().add(R.id.map, mapFragment, "map").commit()

    }

    override fun onMapReady(googleMap: GoogleMap?) {

        var lat = 0.00;
        var lon = 0.00;
        var name = "Cant find location"

        if(this.intent.hasExtra("lat")){
           lat = this.intent.getDoubleExtra("lat",0.00);
        }
        if(this.intent.hasExtra("lon")) {
            lon = this.intent.getDoubleExtra("lon",0.00)
        }
        if(this.intent.hasExtra("name")) {
            name = this.intent.getStringExtra("name")
        }

        var location = LatLng(lat, lon)

        googleMap?.addMarker(MarkerOptions().position(location).title(name))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(location))

    }

}