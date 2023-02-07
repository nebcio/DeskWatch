package com.pum.deskwatch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var tasksList: MutableLiveData<List<Task>> = MutableLiveData(getTasksList(getApplication()))

    val tasks: LiveData<List<Task>>
        get() = tasksList

    val id
        get() = tasksList.value?.maxByOrNull { it.id }?.id ?: 0

    fun getTask(id: Int): LiveData<Task>{
        return tasksList.value?.find { it.id == id }?.let { MutableLiveData(it) } ?: MutableLiveData()
    }

    fun updateTask(item: Task) {
        tasksList.postValue(tasksList.value?.map { if (it.id == item.id) item else it })
        saveTasksList(getApplication(), tasksList.value ?: emptyList())
    }

    fun addTask(item: Task){
        tasksList.postValue(tasksList.value?.plus(item))
        saveTasksList(getApplication(), tasksList.value?: emptyList())
    }

    fun deleteTask(item: Task){
        tasksList.postValue(tasksList.value?.minus(item)?: emptyList())
        saveTasksList(getApplication(), tasksList.value ?: emptyList())
    }

    fun saveTaskList(){
        saveTasksList(getApplication(), tasksList.value ?: emptyList())
    }
}