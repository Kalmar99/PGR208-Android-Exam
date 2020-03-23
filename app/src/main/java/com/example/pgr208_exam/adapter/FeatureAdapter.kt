package com.example.pgr208_exam.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pgr208_exam.MapsActivity
import com.example.pgr208_exam.R
import com.example.pgr208_exam.gsontypes.collection.Feature
import kotlinx.android.synthetic.main.feature_item_layout.view.*

class FeatureAdapter(var list: ArrayList<Feature> = ArrayList(), var onClickListener: View.OnClickListener? = null)  : RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>(), Filterable {

    var fullList: ArrayList<Feature> = ArrayList()
    lateinit var context: Context;

    inner class searchFilter(val adapter: FeatureAdapter) : Filter() {

        var filteredList = ArrayList<Feature>()

        override fun performFiltering(constraint: CharSequence?): FilterResults {

            if(constraint?.count() === 0 || constraint == null) {
                filteredList.addAll(fullList)
            } else {

                for(item in list) {
                    val searchCondition = constraint.toString().toLowerCase()
                    if(item.properties.name.toLowerCase().contains(searchCondition)) {
                        filteredList.add(item)
                    }

                }

            }

            val result = FilterResults();
            result.values = filteredList;
            result.count = filteredList.size;
            return result;
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

            if(results != null) {
                var ret = ArrayList<Feature>()
                for(res in results.values as List<Any>) {
                    when(res.javaClass) {
                        Feature::class.java -> ret.add(res as Feature)
                    }
                }

                adapter.list = ret;
                adapter.notifyDataSetChanged()
            }

        }
    }

    override fun getFilter(): Filter {
        return searchFilter(this)
    }

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

    inner class FeatureViewHolder( var view: View) : RecyclerView.ViewHolder(view) {

        fun bindFeatureWithViewHolder(featureItm: Feature) {
            itemView.textViewName.text = featureItm.properties.getName();
            itemView.tag = featureItm.properties.getId();
            itemView.setOnClickListener(onClickListener)

            itemView.locationIcon.setOnClickListener(View.OnClickListener {
                var intent = Intent(context,MapsActivity::class.java)
                intent.putExtra("lat",featureItm.geometry.coordinates[1])
                intent.putExtra("lon",featureItm.geometry.coordinates[0])
                intent.putExtra("name",featureItm.properties.name)
                context.startActivity(intent);
            })
        }
    }
}