package com.picpay.desafio.android.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.Resource
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.ui.adapters.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onStart() {
        super.onStart()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.users.observe(this) { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> progressBar.visibility = View.VISIBLE
                Resource.Status.ERROR -> onError()
                Resource.Status.SUCCESS -> onSuccess(resource.data ?: emptyList())
            }
        }
    }

    private fun onSuccess(users: List<User>) {
        progressBar.visibility = View.GONE

        adapter.users = users
    }

    private fun onError() {
        val message = getString(R.string.error)

        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getUsers()
    }
}
