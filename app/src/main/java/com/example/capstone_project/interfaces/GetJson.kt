package com.example.capstone_project.interfaces

import android.content.Context
import com.example.capstone_project.model.Languages
import com.google.gson.Gson
import org.json.JSONObject

interface GetJson {
    // Get json from file in assets package and return json as string
    fun getJson(fileName : String, context : Context) : String {
        val jsonfile: String = context.assets.open(fileName).bufferedReader().use {it.readText()}
        val json = JSONObject(jsonfile).toString()
        return json
    }

    fun getLanguageNameJson(languageCode : String, context : Context) : String {
        val gson = Gson()
        val json = getJson("languages.json", context)

        // Convert json string of file to Model (Languages)
        val languages = gson.fromJson(json, Languages::class.java)

        // Loop through all languages and whenever iteration language code matches the code passed
        // from Download class then return the language name
        var languageName = ""
        for (i in languages.languages.indices){
            if (languageCode == languages.languages[i].code){
                languageName = languages.languages[i].name
            }
        }
        return languageName
    }
}