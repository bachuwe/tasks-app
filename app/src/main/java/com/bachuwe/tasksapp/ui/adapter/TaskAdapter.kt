package com.bachuwe.tasksapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bachuwe.tasksapp.R
import com.bachuwe.tasksapp.data.Task
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter(
    private val onTaskClick: (Task) -> Unit,
    private val onTaskChecked: (Task, Boolean) -> Unit,
    private val onTaskMenuClick: (Task, View) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkBoxComplete: CheckBox = itemView.findViewById(R.id.checkBoxComplete)
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        private val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        private val imageViewMore: ImageView = itemView.findViewById(R.id.imageViewMore)

        private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

        fun bind(task: Task) {
            // Remove listener to prevent triggering during binding
            checkBoxComplete.setOnCheckedChangeListener(null)
            checkBoxComplete.isChecked = task.isCompleted
            textViewTitle.text = task.title
            
            if (task.description.isNotEmpty()) {
                textViewDescription.text = task.description
                textViewDescription.visibility = View.VISIBLE
            } else {
                textViewDescription.visibility = View.GONE
            }
            
            textViewDate.text = "Created: ${dateFormat.format(task.createdAt)}"
            
            // Apply strikethrough effect for completed tasks
            if (task.isCompleted) {
                textViewTitle.alpha = 0.6f
                textViewDescription.alpha = 0.6f
            } else {
                textViewTitle.alpha = 1.0f
                textViewDescription.alpha = 1.0f
            }
            
            // Set click listeners
            itemView.setOnClickListener { onTaskClick(task) }
            checkBoxComplete.setOnCheckedChangeListener { _, isChecked ->
                onTaskChecked(task, isChecked)
            }
            imageViewMore.setOnClickListener { onTaskMenuClick(task, it) }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}