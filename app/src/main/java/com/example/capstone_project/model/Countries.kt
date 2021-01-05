package com.example.capstone_project.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Countries (
    @SerializedName("countries") val countries : @RawValue ArrayList<Country>
) : Parcelable

@Parcelize
data class Country (
    @SerializedName("country_code_name") val countryCode : String,
    @SerializedName("lang_code") val languageCode : String
): Parcelable