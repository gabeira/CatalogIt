package com.catalogit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.catalogit.R
import com.catalogit.viewmodel.MediaViewModel
import kotlinx.android.synthetic.main.content_main.*
import androidx.lifecycle.Observer
import com.catalogit.data.model.Item
import com.catalogit.view.adapter.MediaListAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by gabeira@gmail.com on 10/12/18.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mediaViewModel: MediaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mediaViewModel = ViewModelProviders.of(this).get(MediaViewModel::class.java)
        mediaViewModel.mediaList.observe(this, Observer { data ->
            swipeRefreshLayout.isRefreshing = false
            data?.let {
                showEmptyListMessage(it.isEmpty())
                categoryList.adapter = MediaListAdapter(
                        it,
                        onMediaListInteractionListener()
                )
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            mediaViewModel.reload()
        }

        mediaViewModel.getNetworkErrors().observe(this, Observer<String> {
            swipeRefreshLayout.isRefreshing = false
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
        if (mediaViewModel.mediaList.hasObservers()) {
            mediaViewModel.mediaList.removeObservers(this)
        }
    }

    interface OnMediaListInteractionListener {
        fun onMediaItemClick(mediaItem: Item)
    }

    private fun onMediaListInteractionListener(): OnMediaListInteractionListener {
        return object : OnMediaListInteractionListener {
            override fun onMediaItemClick(mediaItem: Item) {
                //TODO Add Media Details Activity
                Toast.makeText(applicationContext, "Media " + mediaItem.title, Toast.LENGTH_LONG).show()
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
}
