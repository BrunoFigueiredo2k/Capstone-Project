package com.example.capstone_project.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.capstone_project.dao.DownloadDao
import com.example.capstone_project.model.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class Converters {
    /** CONVERTER FOR DATE CLASS **/
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    /** CONVERTER FOR ATTRIBUTES CLASS **/
    @TypeConverter
    fun fromAttributes(value : Attributes):String{
        return value.toString()
    }

    @TypeConverter
    fun toAttributes(value:String) : Attributes{
        //TODO: DONT KNOW IF THIS WORKS!!
        return Attributes(value, FeatureDetails(value), arrayListOf(File(value)), RelatedLinks(value))
    }
}

@Database(entities = [Download::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DownloadsRoomDatabase : RoomDatabase() {

    abstract fun downloadDao(): DownloadDao

    companion object {
        private const val DATABASE_NAME = "DOWNLOADS_DATABASE"

        @Volatile
        private var INSTANCE: DownloadsRoomDatabase? = null

        fun getDatabase(context: Context): DownloadsRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(DownloadsRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DownloadsRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
