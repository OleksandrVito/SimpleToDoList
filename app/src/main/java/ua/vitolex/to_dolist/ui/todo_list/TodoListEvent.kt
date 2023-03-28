package ua.vitolex.to_dolist.ui.todo_list

import ua.vitolex.to_dolist.data.Todo

sealed class TodoListEvent {
    /*створюємо екземпляр події у вигляді data class, якщо передаємо параметри
    i у вигляді object, якщо параметри не передаємо */

    data class OnDeleteTodoClick(val todo: Todo) : TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean) : TodoListEvent()
    object OnUndoDeleteClick : TodoListEvent()
    data class OnTodoClick(val todo: Todo) : TodoListEvent()
    object OnAddTodoClick : TodoListEvent()
}
