package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListAdapter: UserListAdapter
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupListeners() {
        binding.refreshButton.setOnClickListener {
            viewModel.getUsers()
        }
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            isNestedScrollingEnabled = false
            userListAdapter = UserListAdapter()
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> onLoading()
                Resource.Status.ERROR -> onError()
                Resource.Status.SUCCESS -> onSuccess(resource.data ?: emptyList())
            }
        }
    }

    private fun onLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun onError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(users: List<UserDomain>) {
        binding.userListProgressBar.visibility = View.GONE

        userListAdapter.users = users
    }
}
