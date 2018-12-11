package com.catalogit.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.catalogit.R
import com.catalogit.data.model.Item
import com.catalogit.view.MainActivity
import com.catalogit.view.glide.GlideApp
import kotlinx.android.synthetic.main.item_media.view.*

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
class MediaItemAdapter(
        private val values: List<Item>,
        private val listener: MainActivity.OnMediaListInteractionListener?
) : RecyclerView.Adapter<MediaItemAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Item
            listener?.onMediaItemClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_media, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        GlideApp.with(holder.mView.context)
                .load(item.images.portrait)
                .placeholder(R.drawable.ic_local_movies_24dp)
                .into(holder.mediaImageView)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val titleView: TextView = mView.itemName
        val mediaImageView: ImageView = mView.mediaImage

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }
}