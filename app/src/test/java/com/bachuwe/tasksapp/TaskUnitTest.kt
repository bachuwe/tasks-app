package com.bachuwe.tasksapp

import com.bachuwe.tasksapp.data.Task
import org.junit.Test
import org.junit.Assert.*
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TaskUnitTest {
    
    @Test
    fun task_creation_isCorrect() {
        val task = Task(
            id = 1,
            title = "Test Task",
            description = "Test Description",
            isCompleted = false
        )
        
        assertEquals("Test Task", task.title)
        assertEquals("Test Description", task.description)
        assertFalse(task.isCompleted)
        assertEquals(1L, task.id)
    }
    
    @Test
    fun task_completion_toggle() {
        val task = Task(
            id = 1,
            title = "Test Task",
            description = "Test Description",
            isCompleted = false
        )
        
        val completedTask = task.copy(isCompleted = true)
        assertTrue(completedTask.isCompleted)
        
        val incompleteTask = completedTask.copy(isCompleted = false)
        assertFalse(incompleteTask.isCompleted)
    }
    
    @Test
    fun task_update_changes_updatedAt() {
        val originalDate = Date(System.currentTimeMillis() - 10000) // 10 seconds ago
        val task = Task(
            id = 1,
            title = "Original Title",
            updatedAt = originalDate
        )
        
        val newDate = Date()
        val updatedTask = task.copy(
            title = "Updated Title",
            updatedAt = newDate
        )
        
        assertEquals("Updated Title", updatedTask.title)
        assertTrue(updatedTask.updatedAt.after(task.updatedAt))
    }
}