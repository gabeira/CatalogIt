package com.catalogit.view.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
        private val isFeatured: Boolean,
        private val listener: MainActivity.OnMediaListInteractionListener?
) : RecyclerView.Adapter<MediaItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_media, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.mediaImageView.contentDescription = item.title

        var imageUrl = item.images.portrait
        if (isFeatured) {
            imageUrl = item.images.landscape
            holder.titleView.layoutParams.width = holder.mView.context.resources.getDimensionPixelSize(R.dimen.landscape_image_width)
            holder.mediaImageView.layoutParams.width = holder.mView.context.resources.getDimensionPixelSize(R.dimen.landscape_image_width)
            holder.mediaImageView.layoutParams.height = holder.mView.context.resources.getDimensionPixelSize(R.dimen.landscape_image_height)
        } else {
            holder.titleView.layoutParams.width = holder.mView.context.resources.getDimensionPixelSize(R.dimen.portrait_image_width)
            holder.mediaImageView.layoutParams.width = holder.mView.context.resources.getDimensionPixelSize(R.dimen.portrait_image_width)
            holder.mediaImageView.layoutParams.height = holder.mView.context.resources.getDimensionPixelSize(R.dimen.portrait_image_height)
        }
        GlideApp.with(holder.mView.context)
                .load(imageUrl)
                .error(R.drawable.ic_local_movies_24dp)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?,
                                              model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean): Boolean {
                        holder.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?,
                                                 model: Any?, target: Target<Drawable>?,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean): Boolean {
                        holder.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(holder.mediaImageView)

        with(holder.mView) {
            setOnClickListener {
                listener?.onMediaItemClick(item, holder.mediaImageView)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val titleView: TextView = mView.itemTitle
        val mediaImageView: ImageView = mView.mediaImage
        val progressBar: ProgressBar = mView.progressBar

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }
}