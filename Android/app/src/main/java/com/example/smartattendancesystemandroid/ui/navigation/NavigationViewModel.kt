package com.example.smartattendancesystemandroid.ui.navigation

import androidx.lifecycle.ViewModel
import com.example.smartattendancesystemandroid.data.token.TokenProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val tokenProvider: TokenProvider
): ViewModel() {
    fun logout() {
        tokenProvider.removeToken()
    }
}