package com.example.capstone_project.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Files(
    @SerializedName("file_name")
    var fileName: String
) : Parcelable