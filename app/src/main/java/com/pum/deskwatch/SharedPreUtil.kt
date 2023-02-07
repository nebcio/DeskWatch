package com.pum.deskwatch

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val TASKS_LIST = "list"
private const val TASKS_FILE = "tasks_file"


fun saveTasksList(context: Context, list: List<Task>) {
    val json = Gson().toJson(list)

    val sharedPreferences = context.getSharedPreferences(TASKS_FILE, Context.MODE_PRIVATE)
    sharedPreferences.edit().putString(TASKS_LIST, json).apply()
}

fun getTasksList(context: Context): List<Task> {
    val sharedPreferences = context.getSharedPreferences(TASKS_FILE, Context.MODE_PRIVATE)
    val json = sharedPreferences.getString(TASKS_LIST, "")

    if (json.isNullOrEmpty()) {
        return emptyList()
    }

    val type = object : TypeToken<ArrayList<Task>>() {}.type
    return Gson().fromJson(json, type)
}