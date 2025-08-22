package com.bachuwe.tasksapp.repository

import androidx.lifecycle.LiveData
import com.bachuwe.tasksapp.data.Task
import com.bachuwe.tasksapp.data.TaskDao
import java.util.Date

class TaskRepository(private val taskDao: TaskDao) {
    
    fun getAllTasks(): LiveData<List<Task>> = taskDao.getAllTasks()
    
    fun getIncompleteTasks(): LiveData<List<Task>> = taskDao.getIncompleteTasks()
    
    fun getCompletedTasks(): LiveData<List<Task>> = taskDao.getCompletedTasks()
    
    suspend fun getTaskById(id: Long): Task? = taskDao.getTaskById(id)
    
    suspend fun insertTask(task: Task): Long = taskDao.insertTask(task)
    
    suspend fun updateTask(task: Task) = taskDao.updateTask(task.copy(updatedAt = Date()))
    
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    
    suspend fun markTaskComplete(task: Task) {
        taskDao.updateTaskStatus(task.id, true)
    }
    
    suspend fun markTaskIncomplete(task: Task) {
        taskDao.updateTaskStatus(task.id, false)
    }
}