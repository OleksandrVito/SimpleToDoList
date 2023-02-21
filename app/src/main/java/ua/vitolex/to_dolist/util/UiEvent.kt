package ua.vitolex.to_dolist.util

import androidx.compose.ui.res.stringResource

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null,
    ) : UiEvent()
}
