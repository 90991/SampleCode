package com.example.sampe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampe.R
import com.example.sampe.adapter.PostsAdapter
import com.example.sampe.databinding.ActivityMainBinding
import com.example.sampe.models.PostsResponseItem
import com.example.sampe.viewModels.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PostsAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter
        viewModel.getPost()

        subscribePosts()
        subscribeProgress()
    }

    private fun subscribePosts() {
        viewModel.observePosts().observe(this, Observer { response ->
            if (response.isSuccess) {
                updateList(response.model as List<PostsResponseItem>)
            } else {
                response.message?.let { showErrorDialog(it) }
            }
        })
    }

    private fun subscribeProgress() {
        viewModel.observeProgress().observe(this, Observer {
            binding.progress = it
        })
    }

    private fun updateList(list: List<PostsResponseItem>) {
        adapter.apply {
            setData(list)
        }
    }
}