package com.bachuwe.tasksapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bachuwe.tasksapp.R
import com.bachuwe.tasksapp.data.Task
import com.bachuwe.tasksapp.databinding.ActivityAddEditTaskBinding
import com.bachuwe.tasksapp.ui.viewmodel.TaskViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.util.Date

class AddEditTaskActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddEditTaskBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private var taskId: Long = -1
    private var existingTask: Task? = null
    
    companion object {
        const val EXTRA_TASK_ID = "extra_task_id"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        taskId = intent.getLongExtra(EXTRA_TASK_ID, -1)
        
        setupToolbar()
        setupClickListeners()
        
        if (taskId != -1L) {
            loadTask()
        }
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = if (taskId == -1L) {
                getString(R.string.add_task)
            } else {
                getString(R.string.edit_task)
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.buttonSave.setOnClickListener {
            saveTask()
        }
    }
    
    private fun loadTask() {
        lifecycleScope.launch {
            existingTask = taskViewModel.getTaskById(taskId)
            existingTask?.let { task ->
                binding.editTextTitle.setText(task.title)
                binding.editTextDescription.setText(task.description)
            }
        }
    }
    
    private fun saveTask() {
        val title = binding.editTextTitle.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim()
        
        if (title.isEmpty()) {
            binding.textInputLayoutTitle.error = getString(R.string.error_empty_title)
            return
        }
        
        binding.textInputLayoutTitle.error = null
        
        if (taskId == -1L) {
            // Create new task
            val newTask = Task(
                title = title,
                description = description,
                createdAt = Date(),
                updatedAt = Date()
            )
            taskViewModel.insertTask(newTask)
        } else {
            // Update existing task
            existingTask?.let { task ->
                val updatedTask = task.copy(
                    title = title,
                    description = description,
                    updatedAt = Date()
                )
                taskViewModel.updateTask(updatedTask)
            }
        }
        
        finish()
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}