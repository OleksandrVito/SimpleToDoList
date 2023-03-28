package ua.vitolex.to_dolist.ui.add_edit_todo

import ua.vitolex.to_dolist.data.Todo
import ua.vitolex.to_dolist.ui.todo_list.TodoListEvent

sealed class AddEditTodoEvent {
    data class OnTitleChange(val title: String) : AddEditTodoEvent()
    data class OnDecriptionChange(val description: String) : AddEditTodoEvent()
    object OnSaveTodoClick : AddEditTodoEvent()
}
