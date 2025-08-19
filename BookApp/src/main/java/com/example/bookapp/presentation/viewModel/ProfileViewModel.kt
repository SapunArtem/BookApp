package com.example.bookapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.models.Profile
import com.example.bookapp.domain.useCases.Profile.GetProfileUseCase
import com.example.bookapp.domain.useCases.Profile.UpdateProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase

) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    init {
        getProfileUseCase().onEach { profile ->
            if (profile == null && _state.value.profile == null) {
                val default = Profile(
                    id = 1, // или UUID
                    name = "Your name",
                    email = "example@mail.com",
                    avatarUrl = null
                )
                _state.value = ProfileState(profile = default, isLoading = false)
                viewModelScope.launch {
                    updateProfileUseCase(default)
                }
            } else if (profile != null) {
                _state.value = ProfileState(profile = profile, isLoading = false)
            }
        }.launchIn(viewModelScope)
    }

    fun updateProfile(name: String, email: String) {
        val current = state.value.profile
        if (current != null) {
            val updated = current.copy(name = name, email = email)
            viewModelScope.launch {
                updateProfileUseCase(updated)
            }
        }
    }
}

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)