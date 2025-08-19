package com.example.bookapp.viewModel

import com.example.bookapp.BaseTest
import com.example.bookapp.domain.models.Profile
import com.example.bookapp.domain.useCases.Profile.GetProfileUseCase
import com.example.bookapp.domain.useCases.Profile.UpdateProfileUseCase
import com.example.bookapp.presentation.viewModel.ProfileViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test


class ProfileViewModelTest : BaseTest() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var getProfileUseCase: GetProfileUseCase
    private lateinit var updateProfileUseCase: UpdateProfileUseCase

    @Before
    fun setupViewModel() {
        getProfileUseCase = mockk(relaxed = true)
        updateProfileUseCase = mockk(relaxed = true)
        viewModel = ProfileViewModel(getProfileUseCase, updateProfileUseCase)
    }

    @Test
    fun `init should set default profile when no profile exists`() = runTest {
        coEvery { getProfileUseCase() } returns flowOf(null)
        coEvery { updateProfileUseCase(any()) } just Runs

        viewModel = ProfileViewModel(getProfileUseCase, updateProfileUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.state.value.profile).isNotNull()
        assertThat(viewModel.state.value.profile?.name).isEqualTo("Your name")
        coVerify { updateProfileUseCase(any()) }
    }

    @Test
    fun `init should set existing profile`() = runTest {
        val profile = Profile(
            id = 1,
            name = "Existing",
            email = "existing@test.com",
            avatarUrl = "url"
        )
        coEvery { getProfileUseCase() } returns flowOf(profile)

        viewModel = ProfileViewModel(getProfileUseCase, updateProfileUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.state.value.profile).isEqualTo(profile)
    }

    @Test
    fun `updateProfile should update profile`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        val currentProfile = Profile(
            1,
            "Old",
            "old@test.com",
            "url")
        val profileFlow = MutableStateFlow(currentProfile)
        coEvery { getProfileUseCase() } returns profileFlow
        coEvery { updateProfileUseCase(any()) } answers {
            profileFlow.value = firstArg()
        }

        val viewModel = ProfileViewModel(getProfileUseCase, updateProfileUseCase)
        val newName = "New"
        val newEmail = "new@test.com"

        testScheduler.advanceUntilIdle()

        viewModel.updateProfile(newName, newEmail)

        testScheduler.advanceUntilIdle()

        val profile = viewModel.state.value.profile
        assertThat(profile?.name).isEqualTo(newName)
        assertThat(profile?.email).isEqualTo(newEmail)
        coVerify { updateProfileUseCase(match { it.name == newName && it.email == newEmail }) }
    }
}