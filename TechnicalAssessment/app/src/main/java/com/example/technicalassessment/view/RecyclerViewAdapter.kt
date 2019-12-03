package com.example.technicalassessment.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.example.technicalassessment.R
import com.example.technicalassessment.model.FactsRows
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.Exception

class RecyclerViewAdapter(private var factsContentList: List<FactsRows>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return factsContentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(factsContentList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(factsRows: FactsRows) {
            itemView.tvTitle.text = factsRows.title
            itemView.tvDesc.text = factsRows.description

            if (factsRows.imageHref != null && !factsRows.imageHref.isEmpty()) {
                itemView.imageView.visibility = View.VISIBLE
                //used to load images lazily
                Picasso.get() // give it the context
                    .load(factsRows.imageHref) // load the image
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .fit()
                    .into(itemView.imageView, object : Callback { // CallBack to find the error of the unloaded Image
                        override fun onSuccess() {
                            itemView.imageView.visibility = View.VISIBLE
                        }
                        override fun onError(e: Exception?) {
                            println("Exception $e")
                        }
                    })

            } else {
                itemView.imageView.visibility = GONE
            }

        }
    }
}