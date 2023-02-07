package com.pum.deskwatch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pum.deskwatch.databinding.TaskItemBinding

class TasksListAdapter (private val taskComparator: TaskComparator, private val flags: Boolean) :
    ListAdapter<Task, TasksListAdapter.TasksListHolder>(taskComparator) {

    class TasksListHolder(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.titleEditText.text = item.title
            binding.checkBox.isChecked = item.isDone

            binding.root.setOnClickListener {
                val action: NavDirections = MainFragmentDirections.actionMainFragmentToTaskFragment2(item.id)
                findNavController(binding.root).navigate(action)
            }
        }
    }
    fun getTaskAt(position: Int): Task{
        return getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksListHolder {
        return TasksListHolder(
            TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TasksListHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToTaskFragment2(getItem(position).id)
            holder.itemView.findNavController().navigate(action)
        }
    }
}