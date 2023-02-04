package com.pum.deskwatch

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val LIST_LIST = "lists"
private const val LIST_FILE = "list_file"


//fun saveListList(context: Context, list: List<ListElement>) {
//    val json = Gson().toJson(list)
//
//    val sharedPreferences = context.getSharedPreferences(LIST_FILE, Context.MODE_PRIVATE)
//    sharedPreferences.edit().putString(LIST_LIST, json).apply()
//}
//
//fun getLists(context: Context): List<ListElement> {
//    val sharedPreferences = context.getSharedPreferences(LIST_FILE, Context.MODE_PRIVATE)
//    val json = sharedPreferences.getString(LIST_LIST, "")
//
//    if (json.isNullOrEmpty()) {
//        return emptyList()
//    }
//
//    val type = object : TypeToken<ArrayList<ListElement>>() {}.type
//    return Gson().fromJson(json, type)
//}