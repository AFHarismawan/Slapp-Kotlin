package com.harismawan.slapp.ui

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.arlib.floatingsearchview.FloatingSearchView

import com.harismawan.slapp.R
import com.harismawan.slapp.config.Constant
import com.harismawan.slapp.data.Word
import com.harismawan.slapp.util.Utils
import eu.davidea.flexibleadapter.FlexibleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity(), FloatingSearchView.OnQueryChangeListener {

    var words = ArrayList<Word>()
    private var adapter = FlexibleAdapter<Word>(words)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerWord.layoutManager = linearLayoutManager
        floatingSearchView.setOnQueryChangeListener(this)

        //TODO add SwipeRefreshLayout
        //TODO save list current position and current search on state change
        loadData()
    }

    private fun initAdapter() {
        adapter = FlexibleAdapter(words)
        adapter.addListener(FlexibleAdapter.OnItemClickListener { position ->
            val change = Intent(this, VideoActivity::class.java)
            change.putExtra(Constant.extraLink, words[position].link)
            startActivity(change)
            false
        })
        recyclerWord.adapter = adapter
    }

    override fun onSearchTextChanged(oldQuery: String?, newQuery: String?) {
        executeSearch(newQuery)
    }

    private fun executeSearch(query: String?) {
        if (adapter.hasNewSearchText(query)) {
            adapter.searchText = query
            adapter.filterItems()
        }
    }

    private fun loadData() {
        val call = Utils.getAPIHelper()?.getAll()
        call?.enqueue(object : Callback<List<Word>> {
            override fun onResponse(call: Call<List<Word>>, response: Response<List<Word>>) {
                if (response.isSuccessful) {
                    words.clear()
                    words.addAll(response.body()!!)
                    initAdapter()
                    progressBar.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                    val snackbar = Snackbar
                            .make(linearLayout, getString(R.string.failed_get_data), Snackbar.LENGTH_INDEFINITE)
                            .setAction(getString(R.string.retry)) {
                                progressBar.visibility = View.VISIBLE
                                loadData()
                            }

                    snackbar.setActionTextColor(Color.RED)
                    val sbView = snackbar.view
                    val textView = sbView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
                    textView.setTextColor(Color.WHITE)
                    snackbar.show()
                }
            }

            override fun onFailure(call: Call<List<Word>>, t: Throwable) {
                progressBar.visibility = View.GONE
                val snackbar = Snackbar
                        .make(linearLayout, getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.retry)) {
                            progressBar.visibility = View.VISIBLE
                            loadData()
                        }

                snackbar.setActionTextColor(Color.RED)
                val sbView = snackbar.view
                val textView = sbView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.WHITE)
                snackbar.show()
            }
        })
    }
}
