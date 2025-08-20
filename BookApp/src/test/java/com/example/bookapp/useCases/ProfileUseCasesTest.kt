package com.example.bookapp.useCases

import com.example.bookapp.BaseTest
import com.example.bookapp.domain.models.Profile
import com.example.bookapp.domain.repository.ProfileRepository
import com.example.bookapp.domain.useCases.Profile.GetProfileUseCase
import com.example.bookapp.domain.useCases.Profile.UpdateProfileUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProfileUseCasesTest : BaseTest() {
    private lateinit var getProfileUseCase: GetProfileUseCase
    private lateinit var updateProfileUseCase: UpdateProfileUseCase
    private lateinit var repository: ProfileRepository

    @Before
    fun setupUseCase() {
        repository = mockk()
        getProfileUseCase = GetProfileUseCase(repository)
        updateProfileUseCase = UpdateProfileUseCase(repository)
    }

    @Test
    fun `GetProfileUseCase should return profile from repository`() = runTest {
        val profile = Profile(
            id = 1,
            name = "Test",
            email = "test@test.com"
        )
        coEvery { repository.getProfile() } returns flowOf(profile)

        val result = getProfileUseCase().first()

        assertThat(result).isEqualTo(profile)
    }

    @Test
    fun `UpdateProfileUseCase should call repository updateProfile`() = runTest {
        val profile = Profile(
            id = 1,
            name = "Test",
            email = "test@test.com"
        )
        coEvery { repository.updateProfile(profile) } just Runs

        updateProfileUseCase(profile)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.updateProfile(profile) }
    }
}