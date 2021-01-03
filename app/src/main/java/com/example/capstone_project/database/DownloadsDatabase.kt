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

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? = value?.let { value ->
        GregorianCalendar().also { calendar ->
            calendar.timeInMillis = value
        }
    }

    @TypeConverter
    fun dateToTimestamp(timestamp: Calendar?): Long? = timestamp?.timeInMillis
}

// Type converter for saving Files arraylist in db
class ArrayListConverter {
    @TypeConverter
    fun listToJson(value: List<Files>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Files>::class.java).toList()
}

// Type converter for class FeatureDetails (Download)
class FeatureDetailsConverter{
    @TypeConverter
    fun fromFeatureDetails(value : FeatureDetails):String{
        return value.toString()
    }

    @TypeConverter
    fun toFeatureDetails(value:String) : FeatureDetails{
        return FeatureDetails(value)
    }
}

// Type converter for class Attributes (Download)
class AttributesConverter{
    @TypeConverter
    fun fromAttributes(value : Attributes):String{
        return value.toString()
    }

    @TypeConverter
    fun toAttributes(value:String) : Attributes{
        return Attributes(value)
    }
}

// Type converter for class RelatedLinks (Download)
class RelatedLinksConverter{
    @TypeConverter
    fun fromRelatedLinks(value : RelatedLinks):String{
        return value.toString()
    }

    @TypeConverter
    fun toRelatedLinks(value:String) : RelatedLinks{
        return RelatedLinks(value)
    }
}

@Database(entities = [Download::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, ArrayListConverter::class, FeatureDetailsConverter::class, AttributesConverter::class, RelatedLinksConverter::class)
abstract class DownloadsRoomDatabase : RoomDatabase() {

    abstract fun downloadDao(): DownloadDao

    companion object {
        private const val DATABASE_NAME = "DOWNLOADS_DATABASE"

        val file = Files("hello.srt")
        val files = arrayListOf(file)

        val featureDetails = FeatureDetails("Spiderman")
        val attributes = Attributes("en")
        val relatedLinks = RelatedLinks("https://s9.osdb.link/features/5/4/4/650445.jpg")

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
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            // TODO: check if this works
                                            database.downloadDao().insertDownload(Download( featureDetails, attributes, files, relatedLinks, Calendar.getInstance()))
                                        }
                                    }
                                }
                            })

                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
