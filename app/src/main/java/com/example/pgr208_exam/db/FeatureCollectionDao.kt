package com.example.pgr208_exam.db

import android.content.Context
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.example.pgr208_exam.gsontypes.collection.Geometry
import com.example.pgr208_exam.gsontypes.collection.Properties
import java.lang.Exception



class FeatureCollectionDao(context: Context) : Database(context) {

    fun insert(features: List<Feature>) {

        for(feature in features) {

            val id = feature.getProperties().getId()
            var name = feature.getProperties().getName()
            val cords = feature.getGeometry().getCoordinates()
            val lat = cords[0]
            val lon = cords[1]

            //writableDatabase.execSQL("INSERT INTO ${DATABASE_NAME} (id,name,lat,lon) VALUES(${id},${name},${lat},${lon})")
            val db = getWritableDatabase()
            var statement = db.compileStatement("INSERT INTO ${DATABASE_NAME} (id,name,lat,lon) VALUES(?,?,?,?)")
            statement.bindLong(1,id);
            statement.bindString(2,name);
            statement.bindDouble(3,lat)
            statement.bindDouble(4,lon);
            statement.executeInsert()

        }

    }



    fun createObject(id: Long, name: String, lat: Double, lon: Double) : Feature {

        //Create feature
        val feature = Feature()

        //Create properties object
        val properties = Properties()
        properties.setId(id)
        properties.setName(name)

        //Create Geometry object
        val geometry = Geometry()
        val cords = listOf(lat,lon)
        geometry.setCoordinates(cords)

        //Insert Geometry and Properties to the Feature
        feature.setProperties(properties);
        feature.setGeometry(geometry);

        return feature;

    }


    fun fetchAll() : FeatureCollection {

        val cursor = readableDatabase.rawQuery("SELECT * FROM feature_test",null)

        var features = ArrayList<Feature>()

        with(cursor) {
            try {
                while(moveToNext()) {

                    val id = getLong(getColumnIndex("id"))
                    val name = getString(getColumnIndex("name"))
                    val lat = getDouble(getColumnIndex("lat"))
                    val lon = getDouble(getColumnIndex("lon"))
                    features.add(createObject(id,name,lat,lon));
                }
            } catch(ex: Exception) {
                // If data is not found in database this should return null so i can try api call
                throw ex;
            }

        }

        val collection = FeatureCollection()
        collection.setType("Feature Collection")
        collection.setFeatures(features)
        return collection;

    }

}