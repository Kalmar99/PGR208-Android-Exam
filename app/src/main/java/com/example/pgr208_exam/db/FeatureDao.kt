package com.example.pgr208_exam.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.pgr208_exam.api.AsyncListener
import com.example.pgr208_exam.gsontypes.single.Feature
import com.example.pgr208_exam.gsontypes.single.Place
import java.lang.Exception


class FeatureDao(context: Context, database: SQLiteDatabase) : AbstractDao<Feature>(context,database) {

    override fun insert(features: List<Feature>) {

            for(feature in features) {
                var statement = database!!.compileStatement("INSERT INTO '${FEATURE_SINGLE_TABLE}' (id,banner,comments,dieselprice,gasolineprice,protected) VALUES(?,?,?,?,?,?)")
                val id = feature.place.id;
                val banner = feature.place.banner;
                val comments = feature.place.comments;
                val dieselprice = feature.place.dieselPrice
                val gasolineprice = feature.place.gasolinePrice
                val protected = feature.place.protectionFrom

                statement.bindLong(1,id);
                statement.bindString(2,banner)
                statement.bindString(3,comments)
                statement.bindDouble(4,dieselprice)
                statement.bindLong(5,gasolineprice)
                statement.bindString(6,protected)
                statement.executeInsert()
            }

    }

    override fun createObject(cursor: Cursor): Feature {

        val id = cursor.getLong(cursor.getColumnIndex("id"))
        val name = cursor.getString(cursor.getColumnIndex("name"))
        val lat = cursor.getDouble(cursor.getColumnIndex("lat"))
        val lon = cursor.getDouble(cursor.getColumnIndex("lon"))
        val banner = cursor.getString(cursor.getColumnIndex("banner"))
        val comments = cursor.getString(cursor.getColumnIndex("comments"))
        val dieselprice = cursor.getDouble(cursor.getColumnIndex("dieselprice"))
        val gasolineprice = cursor.getLong(cursor.getColumnIndex("gasolineprice"))
        val protected = cursor.getString(cursor.getColumnIndex("protected"))

        var feature = Feature()
        var place = Place()
        place.id = id;
        place.name = name
        place.lat = lat
        place.lon = lon
        place.banner = banner
        place.comments = comments
        place.dieselPrice = dieselprice
        place.gasolinePrice = gasolineprice
        place.protectionFrom = protected


        feature.setPlace(place)
        return feature;
    }


}