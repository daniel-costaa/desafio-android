package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.Resource
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.adapters.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userListAdapter: UserListAdapter
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        setupObservers()
        viewModel.getUsers()
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            userListAdapter = UserListAdapter()
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupObservers() {
        viewModel.users.observe(this) { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> binding.userListProgressBar.visibility = View.VISIBLE
                Resource.Status.ERROR -> onError()
                Resource.Status.SUCCESS -> onSuccess(resource.data ?: emptyList())
            }
        }
    }

    private fun onSuccess(users: List<User>) {
        binding.userListProgressBar.visibility = View.GONE

        userListAdapter.users = users
    }

    private fun onError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
