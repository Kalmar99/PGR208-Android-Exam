package com.example.pgr208_exam.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.pgr208_exam.api.AsyncListener
import com.example.pgr208_exam.gsontypes.single.Feature
import com.example.pgr208_exam.gsontypes.single.Place


class FeatureDao(context: Context, database: SQLiteDatabase,listener: AsyncListener<Feature>?) : AbstractDao<Feature>(context,database,listener) {

    override fun insert(features: List<Feature>) {

        for(feature in features) {

            val id = feature.place.getId();
            val banner = feature.place.getBanner();
            val comments = feature.place.getComments();

            var statement = database!!.compileStatement("INSERT INTO '${FEATURE_SINGLE_TABLE}' (id,banner,comments) VALUES(?,?,?)")
            statement.bindLong(1,id);
            statement.bindString(2,banner)
            statement.bindString(3,comments)
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

        var feature = Feature()
        var place = Place()
        place.setId(id);
        place.setName(name)
        place.setLat(lat)
        place.setLon(lon)
        place.setBanner(banner)
        place.setComments(comments)

        feature.setPlace(place)
        return feature;
    }


}