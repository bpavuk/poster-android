package com.bpavuk.posterapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bpavuk.posterapp.PosterApplication
import com.bpavuk.posterapp.ui.common.viewmodels.LoginScreenViewModel
import com.bpavuk.posterapp.ui.common.viewmodels.PostCardsListViewModel
import com.bpavuk.posterapp.ui.common.viewmodels.PosterAppViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            PostCardsListViewModel(application().container.defaultPosterRepository)
        }
        initializer {
            LoginScreenViewModel(
                application().container.defaultPosterRepository,
                application().container.defaultUserLoginRepository
            )
        }
        initializer { PosterAppViewModel() }
    }
}

fun CreationExtras.application() =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PosterApplication)