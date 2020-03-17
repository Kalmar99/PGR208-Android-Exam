package com.example.pgr208_exam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pgr208_exam.R
import com.example.pgr208_exam.gsontypes.Feature
import kotlinx.android.synthetic.main.feature_item_layout.view.*

class FeatureAdapter( var list: ArrayList<Feature> = ArrayList(), var onClickListener: View.OnClickListener? = null)  : RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): FeatureAdapter.FeatureViewHolder {

        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feature_item_layout, parent, false) //Inflating a ViewHolder

        return FeatureViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeatureAdapter.FeatureViewHolder, position: Int) {
        holder.bindFeatureWithViewHolder(list.get(position))
    }

    inner class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindFeatureWithViewHolder(featureItm: Feature) {
            itemView.textViewName.text = featureItm.properties.getName();
        }
    }
}