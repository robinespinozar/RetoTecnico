package com.raerossi.retotecnico.usecases

import com.raerossi.retotecnico.data.LoginRepository
import com.raerossi.retotecnico.data.remote.auth.response.LoginResult
import com.raerossi.retotecnico.domain.usecases.LoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private val mockLoginRepository: LoginRepository = mockk()

    @Before
    fun setUp() {
        loginUseCase = LoginUseCase(mockLoginRepository)
    }

    @Test
    fun `invoke returns LoginResult on successful login`(): Unit = runBlocking {
        val expected = LoginResult.Success
        coEvery { mockLoginRepository.login("Admin", "Password*123") } returns expected

        val actual = loginUseCase("Admin", "Password*123")

        assertEquals(expected, actual)
    }

    @Test
    fun `invoke returns LoginResult on failed login`() = runBlocking {
        val expected = LoginResult.Error
        coEvery { mockLoginRepository.login("user", "wrongPassword") } returns expected

        val actual = loginUseCase("user", "wrongPassword")

        assertEquals(expected, actual)
    }
}