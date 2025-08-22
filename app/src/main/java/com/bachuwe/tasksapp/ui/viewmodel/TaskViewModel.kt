package com.bachuwe.tasksapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bachuwe.tasksapp.data.Task
import com.bachuwe.tasksapp.data.TaskDatabase
import com.bachuwe.tasksapp.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>
    val incompleteTasks: LiveData<List<Task>>
    val completedTasks: LiveData<List<Task>>
    
    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.getAllTasks()
        incompleteTasks = repository.getIncompleteTasks()
        completedTasks = repository.getCompletedTasks()
    }
    
    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }
    
    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }
    
    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }
    
    fun markTaskComplete(task: Task) = viewModelScope.launch {
        repository.markTaskComplete(task)
    }
    
    fun markTaskIncomplete(task: Task) = viewModelScope.launch {
        repository.markTaskIncomplete(task)
    }
    
    suspend fun getTaskById(id: Long): Task? {
        return repository.getTaskById(id)
    }
}