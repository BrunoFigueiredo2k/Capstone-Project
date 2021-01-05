package com.example.capstone_project.interfaces

import android.content.Context
import org.json.JSONObject

interface GetJson {
    // Get json from file in assets package and return json as string
    fun getJson(fileName : String, context : Context) : String {
        val jsonfile: String = context.assets.open(fileName).bufferedReader().use {it.readText()}
        val json = JSONObject(jsonfile).toString()
        return json
    }
}