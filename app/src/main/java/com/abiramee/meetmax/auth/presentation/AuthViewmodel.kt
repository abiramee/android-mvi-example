package com.abiramee.meetmax.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abiramee.meetmax.auth.domain.AuthDataSource
import com.abiramee.meetmax.auth.domain.ForgotPasswordModel
import com.abiramee.meetmax.auth.domain.SignInModel
import com.abiramee.meetmax.auth.domain.SignUpModel
import com.abiramee.meetmax.auth.presentation.models.AuthUI
import com.abiramee.meetmax.core.domain.util.onError
import com.abiramee.meetmax.core.domain.util.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.sign
import kotlin.text.matches


class AuthViewmodel(private val authDataSource: AuthDataSource) : ViewModel() {

    private val _state = MutableStateFlow(AuthState(screen = AuthUI.SignUp()))
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        AuthState(screen = AuthUI.SignUp())
    )

    private val _share = MutableSharedFlow<AuthShare>()
    val share = _share.asSharedFlow()

    fun onAction(action: AuthAction) {
        when (action) {
            is AuthAction.OnNavigateSignInClick -> {
                _state.update { it.copy(screen = AuthUI.SignIn()) }
            }

            is AuthAction.OnNavigateSignUpClick -> {
                _state.update { it.copy(screen = AuthUI.SignUp()) }
            }

            is AuthAction.OnNavigateForgotPasswordClick -> {
                _state.update { it.copy(screen = AuthUI.ForgotPassword()) }
            }

            is AuthAction.OnSignUpClick -> {
                signUp(action.signUpModel)

            }

            is AuthAction.OnSignInClick -> {
                signIn(action.signInModel)
            }

            is AuthAction.OnForgotPasswordSentClick -> {
                sendEmailForgotPassword(action.forgotPasswordModel)
            }
        }
    }

    private fun validateUserData(signupModel: SignUpModel): Boolean {
        Log.d("validateUserData: ", signupModel.toString())
        if (!signupModel.email.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))) {
            viewModelScope.launch {
                _share.emit(AuthShare.ToastError("Invalid email"))
            }
            return false;
        }

        if (signupModel.name.trim().isEmpty()) {
            viewModelScope.launch {
                _share.emit(AuthShare.ToastError("Invalid name"))
            }
            return false;
        }

        if (signupModel.password.length < 8) {
            viewModelScope.launch {
                _share.emit(AuthShare.ToastError("Invalid password"))
            }
            return false;
        }

        signupModel.dateOfBirth = convertDate(signupModel.dateOfBirth)

        if (!signupModel.dateOfBirth.matches(Regex("\\d{2}-\\d{2}-\\d{4}"))) {
            viewModelScope.launch {
                _share.emit(AuthShare.ToastError("Invalid date of birth"))
            }
            return false;
        }

        if (signupModel.gender !in listOf("Male", "Female")) {
            viewModelScope.launch {
                _share.emit(AuthShare.ToastError("Invalid gender"))
            }
            return false
        }

        return true
    }

    private fun signUp(signUpModel: SignUpModel) {
        if (!validateUserData(signUpModel)) {
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            authDataSource.signUp(signUpModel)
                .onSuccess {
                    _share.emit(AuthShare.Navigation)
                    delay(500L)
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
                .onError {
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }

    private fun convertDate(inputDate: String): String {
        if (inputDate.length != 8) {
            return inputDate // Return the input if it's not in the expected format
        }

        return inputDate.substring(0, 2) + "-" +
                inputDate.substring(2, 4) + "-" +
                inputDate.substring(4, 8)
    }

    private fun validateSignInModel(signInModel: SignInModel): Boolean {
        if (!signInModel.email.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))) {
            viewModelScope.launch {
                _share.emit(AuthShare.ToastError("Invalid email"))
            }
            return false;
        }

        if (signInModel.password.length < 8) {
            viewModelScope.launch {
                _share.emit(AuthShare.ToastError("Invalid password"))
            }
            return false;
        }

        return true
    }

    private fun signIn(signInModel: SignInModel) {
        if (!validateSignInModel(signInModel))
            return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            authDataSource.signIn(signInModel).onSuccess {
                _share.emit(AuthShare.Navigation)
                delay(500L)
                _state.update { it.copy(isLoading = false) }
            }
                .onError {
                    _state.update { it.copy(isLoading = true) }
                    _share.emit(AuthShare.ToastError("Something went wrong!"))
                }
        }
    }

    private fun validateEmail(forgotPasswordModel: ForgotPasswordModel): Boolean {
        if (!forgotPasswordModel.email.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))) {
            viewModelScope.launch {
                _share.emit(AuthShare.ToastError("Invalid email"))
            }
            return false;
        }
        return true
    }

    private fun sendEmailForgotPassword(forgotPasswordModel: ForgotPasswordModel) {
        if (!validateEmail(forgotPasswordModel))
            return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            authDataSource.forgotPassword(forgotPasswordModel).onSuccess {
                _state.update { it.copy(isLoading = false) }
                _share.emit(AuthShare.ToastError("Email Sent"))
            }
                .onError {
                    _state.update { it.copy(isLoading = true) }
                    _share.emit(AuthShare.ToastError("Something went wrong!"))
                }
        }
    }
}

