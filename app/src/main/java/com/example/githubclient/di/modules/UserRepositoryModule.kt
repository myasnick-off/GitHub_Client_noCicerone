package com.example.githubclient.di.modules

import com.example.githubclient.di.scope.UserScope
import com.example.githubclient.repository.users.UsersRepository
import com.example.githubclient.repository.users.UsersRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface UserRepositoryModule {

    @Binds
    @UserScope
    fun provideUserRepository(impl: UsersRepositoryImpl): UsersRepository
}