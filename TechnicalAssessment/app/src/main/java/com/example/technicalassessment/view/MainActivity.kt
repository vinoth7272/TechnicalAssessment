package com.example.technicalassessment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import com.example.technicalassessment.R
import com.example.technicalassessment.model.Facts
import com.example.technicalassessment.model.Output
import com.example.technicalassessment.repository.DataRepository
import com.example.technicalassessment.utils.AppUtils.Companion.getInstance
import com.example.technicalassessment.viewmodel.ActivityViewModel
import com.example.technicalassessment.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var activityViewModel: ActivityViewModel
    private val actionBar by lazy {
        supportActionBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityViewModel = ViewModelProviders.of(this, MainViewModelFactory(dataRepository = DataRepository()))
            .get(ActivityViewModel::class.java)

        setAdapter()

        loadData()

        setSwipeToRefresh()
    }

    private fun setAdapter() {
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView?.adapter = recyclerViewAdapter
    }

    private fun setSwipeToRefresh() {
        //Set the colors of the Pull To Refresh View
        swipe_container.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this, R.color.colorPrimary))
        swipe_container.setColorSchemeColors(Color.WHITE)
        //set Swipe Refresh Listener
        swipe_container.setOnRefreshListener {
            checkInternetAndCallApi()
        }
    }

    private fun loadData() {
        progressBar.visibility = VISIBLE
        checkInternetAndCallApi()

        activityViewModel.output.observe(this, Observer {
            when (it) {
                is Output.Success ->
                    successFunction(it.facts)
                is Output.Error -> {
                    showSnackMessage(it.error.toString())
                }
            }
        })
    }

    /**
     * used to show snack bar with message
     * @param message to show in snack bar
     */
    private fun showSnackMessage(message: String) {
        Snackbar.make(
            parent_view.rootView, message,
            Snackbar.LENGTH_SHORT
        ).show()
        progressBar.visibility = GONE
        swipe_container?.isRefreshing = false
    }

    /**
     * Method used to check the internet connection 1st then it call the api method
     */
    private fun checkInternetAndCallApi() {
        if (getInstance().isConnectedToNetwork(this))
            activityViewModel.getFacts() // call view model api call method
        else {
            showSnackMessage(getString(R.string.network_error))
        }
    }

    /**
     * call when API response success
     * @param facts list data for the adapter
     */
    private fun successFunction(facts: Facts) {
        recyclerViewAdapter.setData(getInstance().filterList(facts.rows))
        recyclerViewAdapter.notifyDataSetChanged()
        actionBar.let {
            it?.title = facts.title
        }
        showSnackMessage(getString(R.string.success_string))
    }

}
