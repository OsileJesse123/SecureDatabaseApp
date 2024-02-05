package com.jesse.securedatabaseapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.securedatabaseapp.data.model.User
import com.jesse.securedatabaseapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _userFlow: MutableStateFlow<User> = MutableStateFlow(User())
    val userFlow get() = _userFlow.asStateFlow()

    init {
        viewModelScope.launch {
            // We already know that the user id will be 1 because it has been hardcoded.
            _userFlow.value = userRepository.getUser(1)
        }
    }
}