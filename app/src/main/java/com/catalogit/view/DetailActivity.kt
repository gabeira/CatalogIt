package com.catalogit.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.catalogit.R
import com.catalogit.view.glide.GlideApp
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mediaTitleText = intent.getStringExtra(MainActivity.TITLE_KEY)
        title = mediaTitleText
        mediaTitle.text = mediaTitleText
        mediaImage.contentDescription = mediaTitleText

        year.text = intent.getStringExtra(MainActivity.YEAR_KEY)
        description.text = intent.getStringExtra(MainActivity.DESCRIPTION_KEY)

        GlideApp.with(applicationContext)
                .load(intent.getStringExtra(MainActivity.IMAGE_KEY))
                .error(R.drawable.ic_local_movies_24dp)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?,
                                              model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?,
                                                 model: Any?,
                                                 target: Target<Drawable>?,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                })
                .into(mediaImage)

        close.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
        super.onBackPressed()
    }
}