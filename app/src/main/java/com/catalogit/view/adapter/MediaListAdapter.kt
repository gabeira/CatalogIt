package com.catalogit.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.catalogit.R
import com.catalogit.data.model.MediaList
import com.catalogit.view.MainActivity
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
class MediaListAdapter(
    private val values: List<MediaList>,
    private val listener: MainActivity.OnMediaListInteractionListener?
) : RecyclerView.Adapter<MediaListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.categoryView.text = item.category
        holder.mediaRecyclerView.adapter = MediaItemAdapter(
                item.items,
                listener
        )
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

        val mediaRecyclerView: RecyclerView = mView.mediaList
        val categoryView: TextView = mView.categoryView

        override fun toString(): String {
            return super.toString() + " '" + categoryView.text + "'"
        }
    }
}