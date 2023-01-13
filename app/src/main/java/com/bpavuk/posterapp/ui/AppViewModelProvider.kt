package com.bpavuk.posterapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bpavuk.posterapp.PosterApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            PosterAppViewModel(application().container.defaultPosterRepository)
        }
    }
}

fun CreationExtras.application() =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PosterApplication)