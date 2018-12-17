package com.catalogit.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.catalogit.MyApp
import com.catalogit.R
import com.catalogit.data.model.Item
import com.catalogit.view.adapter.MediaListAdapter
import com.catalogit.viewmodel.MediaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


/**
 * Created by gabeira@gmail.com on 10/12/18.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mediaViewModel: MediaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        (application as MyApp).component?.inject(this)

        mediaViewModel = ViewModelProviders.of(this).get(MediaViewModel::class.java)
        mediaViewModel.getMediaList().observe(this, Observer { data ->
            swipeRefreshLayout.isRefreshing = false
            data?.let {
                showEmptyListMessage(it.isEmpty())
                categoryList.adapter = MediaListAdapter(
                        it.reversed(),
                        onMediaListInteractionListener()
                )
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            mediaViewModel.reload()
        }

        mediaViewModel.getNetworkErrors().observe(this, Observer<String> {
            swipeRefreshLayout.isRefreshing = false
            showEmptyListMessage(true)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun showEmptyListMessage(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            categoryList.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            categoryList.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaViewModel.getMediaList().hasObservers()) {
            mediaViewModel.getMediaList().removeObservers(this)
        }
    }

    interface OnMediaListInteractionListener {
        fun onMediaItemClick(mediaItem: Item, imageView: View)
    }

    private fun onMediaListInteractionListener(): OnMediaListInteractionListener {
        return object : OnMediaListInteractionListener {
            override fun onMediaItemClick(mediaItem: Item, imageView: View) {
                val intent = Intent(baseContext, DetailActivity::class.java)
                intent.putExtra(TITLE_KEY, mediaItem.title)
                intent.putExtra(YEAR_KEY, mediaItem.year.toString())
                intent.putExtra(IMAGE_KEY, mediaItem.images.landscape)
                intent.putExtra(DESCRIPTION_KEY, mediaItem.description)
                val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(
                                this@MainActivity,
                                imageView,
                                getString(R.string.image_transition))
                startActivity(intent, options.toBundle())
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val TITLE_KEY = "title"
        const val YEAR_KEY = "year"
        const val IMAGE_KEY = "image"
        const val DESCRIPTION_KEY = "description"
    }
}