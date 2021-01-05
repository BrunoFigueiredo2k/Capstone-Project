package com.example.capstone_project.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String
) : Parcelable