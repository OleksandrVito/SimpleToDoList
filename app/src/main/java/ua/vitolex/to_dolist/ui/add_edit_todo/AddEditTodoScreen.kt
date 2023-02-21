package ua.vitolex.to_dolist.ui.add_edit_todo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.vitolex.to_dolist.R
import ua.vitolex.to_dolist.ui.theme.*
import ua.vitolex.to_dolist.util.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel(),
) {
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
                .padding(16.dp),
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
                )
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
                )
            )
        }
    }

}