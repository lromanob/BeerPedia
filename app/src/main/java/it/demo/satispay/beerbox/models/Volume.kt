package it.demo.satispay.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Volume(

    @field:SerializedName("unit")
    val unit: String? = null,

    @field:SerializedName("value")
    val value: Int? = null
): Parcelable