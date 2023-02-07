package com.pum.deskwatch

import androidx.recyclerview.widget.DiffUtil

class TaskComparator : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.title == newItem.title && oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}