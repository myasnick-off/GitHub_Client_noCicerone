package com.example.githubclient.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.FragmentUsersBinding
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.network.ApiHolder.githubApiService
import com.example.githubclient.repository.UsersRepositoryImpl
import com.example.githubclient.ui.AndroidScreens
import com.example.githubclient.ui.BackButtonListener
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val presenter by moxyPresenter {
        UsersPresenter(UsersRepositoryImpl(githubApiService), App.appInstance.router, AndroidScreens())
    }
    private lateinit var adapter: UsersRecyclerAdapter
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        adapter = UsersRecyclerAdapter { presenter.onUserClicked() }
        binding.usersRecyclerView.adapter = adapter
    }

    override fun updateList(users: List<GitHubUser>) {
        adapter.submitList(users)
    }

    override fun showError(message: String?) {
        Snackbar
            .make(binding.root, "${getString(R.string.error)} ${message.orEmpty()}", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        fun newInstance() = UsersFragment()
    }
}