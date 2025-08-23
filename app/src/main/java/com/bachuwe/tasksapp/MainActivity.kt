package com.bachuwe.tasksapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bachuwe.tasksapp.data.Task
import com.bachuwe.tasksapp.databinding.ActivityMainBinding
import com.bachuwe.tasksapp.ui.AddEditTaskActivity
import com.bachuwe.tasksapp.ui.adapter.TaskAdapter
import com.bachuwe.tasksapp.ui.viewmodel.TaskViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private val taskViewModel: TaskViewModel by viewModels()
    private var showCompletedTasks = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        setupFab()
        observeTasks()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }
    
    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(
            onTaskClick = { task -> editTask(task) },
            onTaskChecked = { task, isChecked -> toggleTaskStatus(task, isChecked) },
            onTaskMenuClick = { task, view -> showTaskMenu(task, view) }
        )
        
        binding.recyclerViewTasks.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
    
    private fun setupFab() {
        binding.fabAddTask.setOnClickListener {
            addNewTask()
        }
    }
    
    private var currentTasksObserver: LiveData<List<Task>>? = null
    
    private fun observeTasks() {
        // Remove previous observer if exists
        currentTasksObserver?.removeObservers(this)
        
        // Get the appropriate LiveData based on current view mode
        currentTasksObserver = if (showCompletedTasks) {
            taskViewModel.completedTasks
        } else {
            taskViewModel.incompleteTasks
        }
        
        // Observe the new LiveData
        currentTasksObserver?.observe(this) { tasks ->
            taskAdapter.submitList(tasks)
            updateEmptyState(tasks.isEmpty())
        }
    }
    
    private fun updateEmptyState(isEmpty: Boolean) {
        if (isEmpty) {
            binding.recyclerViewTasks.visibility = View.GONE
            binding.textViewEmptyState.visibility = View.VISIBLE
        } else {
            binding.recyclerViewTasks.visibility = View.VISIBLE
            binding.textViewEmptyState.visibility = View.GONE
        }
    }
    
    private fun addNewTask() {
        val intent = Intent(this, AddEditTaskActivity::class.java)
        startActivity(intent)
    }
    
    private fun editTask(task: Task) {
        val intent = Intent(this, AddEditTaskActivity::class.java).apply {
            putExtra(AddEditTaskActivity.EXTRA_TASK_ID, task.id)
        }
        startActivity(intent)
    }
    
    private fun toggleTaskStatus(task: Task, isCompleted: Boolean) {
        if (isCompleted) {
            taskViewModel.markTaskComplete(task)
            Snackbar.make(binding.root, R.string.task_completed, Snackbar.LENGTH_SHORT).show()
        } else {
            taskViewModel.markTaskIncomplete(task)
        }
    }
    
    private fun showTaskMenu(task: Task, view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.task_menu, popup.menu)
        
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_edit -> {
                    editTask(task)
                    true
                }
                R.id.action_delete -> {
                    confirmDeleteTask(task)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
    
    private fun confirmDeleteTask(task: Task) {
        AlertDialog.Builder(this)
            .setTitle(R.string.delete)
            .setMessage(R.string.confirm_delete)
            .setPositiveButton(R.string.delete) { _, _ ->
                deleteTask(task)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
    
    private fun deleteTask(task: Task) {
        taskViewModel.deleteTask(task)
        Snackbar.make(binding.root, R.string.task_deleted, Snackbar.LENGTH_SHORT).show()
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_show_completed -> {
                showCompletedTasks = !showCompletedTasks
                item.title = if (showCompletedTasks) {
                    getString(R.string.show_active)
                } else {
                    getString(R.string.show_completed)
                }
                observeTasks()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}