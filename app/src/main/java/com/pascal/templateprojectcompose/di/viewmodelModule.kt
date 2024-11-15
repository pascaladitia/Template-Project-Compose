package com.pascal.templateprojectcompose.di

import com.pascal.templateprojectcompose.ui.screen.home.HomeViewModel
import com.pascal.templateprojectcompose.ui.screen.live.LiveViewModel
import com.pascal.templateprojectcompose.ui.screen.profile.ProfileViewModel
import com.pascal.templateprojectcompose.ui.screen.teams.TeamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { TeamViewModel(get()) }
    viewModel { LiveViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}

