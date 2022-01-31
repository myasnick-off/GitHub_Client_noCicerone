package com.example.githubclient.presenter

import com.example.githubclient.repository.GitHubUser
import com.example.githubclient.ui.users.UserItemView

class UsersListPresenter : IUserListPresenter {

    val users = mutableListOf<GitHubUser>()
    override var itemClickListener: ((UserItemView) -> Unit)? = null

    override fun bindView(view: UserItemView) {
        val user = users[view.pos]
        view.setLogin(user.login)
    }

    override fun getCount() = users.size
}