package com.example.technicalassessment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.technicalassessment.R
import com.example.technicalassessment.model.FactsRows
import com.example.technicalassessment.viewmodel.ActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mParentView: View
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        initViews()

        loadData(actionBar)

        //** Set the colors of the Pull To Refresh View
        mSwipeRefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this, R.color.colorPrimary))
        mSwipeRefresh.setColorSchemeColors(Color.WHITE)
        //set Swipe Refresh Listener
        mSwipeRefresh.setOnRefreshListener {
            activityViewModel.getFacts()
        }
    }

    private fun loadData(actionBar: ActionBar?) {
        mProgressBar.visibility = VISIBLE
        activityViewModel.getFacts()
        activityViewModel.resultStatus.observe(this, Observer {
            it?.let { boolean ->
                if (!boolean) Snackbar.make(
                    mParentView.rootView,
                    "Failed,Please Try Again",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        //oberver pattern to get the live data list
        activityViewModel.facts.observe(this, Observer {
            it?.let { value ->
                mProgressBar.visibility = GONE
                actionBar!!.title = ""
                val recyclerViewAdapter = RecyclerViewAdapter(filterList(value.rows))
                mRecyclerView.adapter = recyclerViewAdapter
                Snackbar.make(mParentView.rootView, "Success", Snackbar.LENGTH_SHORT).show()
                mSwipeRefresh.isRefreshing = false
                actionBar.title = value.title
            }
        })
    }

    /**
     * used to filter the empty list item
     * @param rows list to filter
     */
    private fun filterList(rows: List<FactsRows>): List<FactsRows> {
        val tempList: ArrayList<FactsRows> = ArrayList()
        for (row in rows) {
            if ((row.title != null && !row.title.isEmpty()) || (row.description != null && !row.description.isEmpty()))
                tempList.add(row)
        }
        return tempList
    }

    private fun initViews() {
        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        mProgressBar = findViewById(R.id.progressBar)
        mRecyclerView = findViewById(R.id.recyclerView)
        mSwipeRefresh = findViewById(R.id.swipe_container)
        mParentView = findViewById(R.id.parent_view)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }
}
