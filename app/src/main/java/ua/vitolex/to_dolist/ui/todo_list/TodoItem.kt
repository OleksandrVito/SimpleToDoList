package ua.vitolex.to_dolist.ui.todo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.vitolex.to_dolist.R
import ua.vitolex.to_dolist.data.Todo
import ua.vitolex.to_dolist.ui.theme.*


@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    val alphaBackground = if (todo.isDone) 0.7f else 1f

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .alpha(alphaBackground)
    ) {
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondary,
                    shape = RoundedCornerShape(4.dp)
                )
                .background(Nepal)
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        text = todo.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = montserratFF,
                    )
                }
                if (todo.description != "") {
                    Text(
                        text = todo.description ?: "",
                        fontSize = 16.sp,
                        fontFamily = montserratFF
                    )
                }
            }
            IconButton(
                onClick = {
                    onEvent(TodoListEvent.OnDeleteTodoClick(todo))
                }) {
                Icon(
                    imageVector = Icons.TwoTone.Delete,
                    contentDescription = stringResource(R.string.delete),
                    tint = DarkGray.copy(alpha = 0.8f),
                    )
            }
            Checkbox(
                checked = todo.isDone,
                onCheckedChange = { isChecked ->
                    onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
                },
                modifier = Modifier
                    .scale(1.5f),
                colors = CheckboxDefaults.colors(
                    checkedColor = HotCinnamon,
                    uncheckedColor = Silver,
                    checkmarkColor = MidnightBlue
                )
            )
        }
    }

}

