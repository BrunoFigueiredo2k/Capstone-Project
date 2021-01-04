package com.example.capstone_project.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Languages (
    @SerializedName("languages") val languages : @RawValue ArrayList<Language>
) : Parcelable

@Parcelize
data class Language (
    @SerializedName("code") val code : String,
    @SerializedName("name") val name : String
): Parcelable