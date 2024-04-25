package com.dicoding.asclepius.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.adapter.ResultAdapter
import com.dicoding.asclepius.viewmodel.CancerViewModel

class HistoryActivity : AppCompatActivity() {

    private lateinit var cancerViewModel: CancerViewModel
    private lateinit var resultAdapter: ResultAdapter
    private lateinit var rvResult: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        cancerViewModel = ViewModelProvider(this).get(CancerViewModel::class.java)

        resultAdapter = ResultAdapter(emptyList())
        rvResult = findViewById(R.id.resultRv)
        rvResult.layoutManager = LinearLayoutManager(this)
        rvResult.adapter = resultAdapter

        cancerViewModel.cancer.observe(this, Observer { it ->
            it?.let {
                resultAdapter.setUserList(it)
            }
        })
    }
}