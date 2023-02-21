package ua.vitolex.to_dolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import ua.vitolex.to_dolist.ui.SplashScreen
import ua.vitolex.to_dolist.ui.add_edit_todo.AddEditTodoScreen
import ua.vitolex.to_dolist.ui.theme.ToDoListTheme
import ua.vitolex.to_dolist.ui.todo_list.TodoListScreen
import ua.vitolex.to_dolist.util.Routes
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.SPLASH_SCREEN) {
                    composable(Routes.SPLASH_SCREEN) {
                        SplashScreen(navController = navController)
                    }
                    composable(Routes.TODO_LIST) {
                        TodoListScreen(onNavigate = {
                            navController.navigate(it.route)
                        }, navController = navController)
                    }
                    composable(Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name = "todoId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditTodoScreen(onPopBackStack = {
                            navController.popBackStack()
                        })

                    }
                }
            }
        }
        MobileAds.initialize(this) {}
    }
}

