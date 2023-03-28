package ua.vitolex.to_dolist.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null,
)

