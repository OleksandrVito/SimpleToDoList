package ua.vitolex.to_dolist.ui.add_edit_todo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import ua.vitolex.to_dolist.R
import ua.vitolex.to_dolist.ui.theme.*
import ua.vitolex.to_dolist.util.UiEvent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect() { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }

        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),

        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save",
                    tint = MaterialTheme.colors.secondaryVariant
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .padding(16.dp)
                .padding(top = 16.dp),
        ) {
            TextField(
                value = viewModel.title,
                onValueChange = {
                    viewModel.onEvent(AddEditTodoEvent.OnTitleChange(it))
                },
                label = {
                    Text(text = stringResource(R.string.title))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = DarkGray.copy(0.7f),
                    focusedLabelColor = DarkGray.copy(0.7f),
                    backgroundColor = Nepal.copy(alpha = 0.8f),
                    focusedIndicatorColor = HotCinnamon,
                    unfocusedIndicatorColor = Nepal
                        .copy(TextFieldDefaults.UnfocusedIndicatorLineOpacity),
                    cursorColor = HotCinnamon,
                    textColor = DarkGray.copy(0.9f)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    autoCorrect = true,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.description,
                onValueChange = {
                    viewModel.onEvent(AddEditTodoEvent.OnDecriptionChange(it))
                },
                label = {
                    Text(text = stringResource(R.string.description))
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = DarkGray.copy(0.7f),
                    focusedLabelColor = DarkGray.copy(0.7f),
                    backgroundColor = Nepal.copy(alpha = 0.8f),
                    focusedIndicatorColor = HotCinnamon,
                    unfocusedIndicatorColor = Nepal
                        .copy(TextFieldDefaults.UnfocusedIndicatorLineOpacity),
                    cursorColor = HotCinnamon,
                    textColor = DarkGray.copy(0.9f)
                ),
            )
            Spacer(modifier = Modifier.height(28.dp))
            BannerAdView()
        }
    }

}

@Composable
fun BannerAdView() {
    val isInEditMode = LocalInspectionMode.current

    if (isInEditMode) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(horizontal = 2.dp, vertical = 6.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            text = "Advert Here",
        )
    } else {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.MEDIUM_RECTANGLE)
                    // Додайте свій adUnitID, це для тестування.
                    adUnitId = context.getString(R.string.banner_ad_unit_id)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}