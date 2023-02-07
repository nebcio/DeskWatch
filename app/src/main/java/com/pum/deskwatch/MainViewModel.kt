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

    fun updateTask(item: Task){
        tasksList.postValue(tasksList.value?.map { if (it.id == item.id) item else it })
    }

    fun getTask(id: Int): LiveData<Task>{
        return tasksList.value?.find { it.id == id }?.let { MutableLiveData(it) } ?: MutableLiveData()
    }

    fun addTask(item: Task){
        tasksList.postValue(tasksList.value?.plus(item))
    }

    fun deleteTask(item: Task){
        tasksList.postValue(tasksList.value?.minus(item))
    }
}