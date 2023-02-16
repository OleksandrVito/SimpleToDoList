package ua.vitolex.to_dolist.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.vitolex.to_dolist.data.TodoDataBase
import ua.vitolex.to_dolist.data.TodoRepository
import ua.vitolex.to_dolist.data.TodoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDataBase {
        return Room.databaseBuilder(
            app,
            TodoDataBase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDataBase): TodoRepository {
        return TodoRepositoryImpl(db.dao)
    }
}