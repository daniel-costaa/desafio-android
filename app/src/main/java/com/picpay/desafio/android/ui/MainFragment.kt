package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.UserDomain
import com.picpay.desafio.android.databinding.FragmentMainBinding
import com.picpay.desafio.android.ui.adapters.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var userListAdapter: UserListAdapter
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        viewModel.getUsers()
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            userListAdapter = UserListAdapter()
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> binding.userListProgressBar.visibility = View.VISIBLE
                Resource.Status.ERROR -> onError()
                Resource.Status.SUCCESS -> onSuccess(resource.data ?: emptyList())
            }
        }
    }

    private fun onSuccess(users: List<UserDomain>) {
        binding.userListProgressBar.visibility = View.GONE

        userListAdapter.users = users
    }

    private fun onError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
