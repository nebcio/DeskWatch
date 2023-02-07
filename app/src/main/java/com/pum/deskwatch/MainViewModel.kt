package com.pum.deskwatch

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var tasksList: MutableLiveData<List<Task>> = MutableLiveData()

    val tasks: LiveData<List<Task>>
        get() = tasksList

    fun getTaskList(context: Context) {
        tasksList.postValue(getTasksList(context))
    }

    fun getTask(id: Int): LiveData<Task>{
        return tasksList.value?.find { it.id == id }?.let { MutableLiveData(it) } ?: MutableLiveData()
    }

    fun saveTaskList(context: Context) {
        saveTasksList(context, tasksList.value ?: emptyList())
    }

    fun updateTask(item: Task, context: Context){
        tasksList.postValue(tasksList.value?.map { if (it.id == item.id) item else it })
        saveTasksList(context, tasksList.value ?: emptyList())
    }

    fun addTask(item: Task, context: Context){
        tasksList.postValue(tasksList.value?.plus(item))
        saveTasksList(context, tasksList.value ?: emptyList())
    }

    fun deleteTask(item: Task, context: Context){
        tasksList.postValue(tasksList.value?.minus(item))
        saveTasksList(context, tasksList.value ?: emptyList())
    }
}