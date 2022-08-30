package com.hfad.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azimut4946777.my_tasks.Task
import com.azimut4946777.my_tasks.TaskDiffItemCallback
import com.azimut4946777.my_tasks.databinding.TaskItemBinding
import com.azimut4946777.my_tasks.generated.callback.OnClickListener


class TaskItemAdapter(val clickListener: (taskId: Long)-> Unit) : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }
    class TaskItemViewHolder(val binding: TaskItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }
        fun bind(item: Task, clickListener: (taskId: Long) -> Unit) {
            binding.task = item
            binding.root.setOnClickListener {clickListener(item.taskId)}
        }
    }
}