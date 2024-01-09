package it.demo.satispay.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Amount(

    @field:SerializedName("unit")
    val unit: String? = null,

    @field:SerializedName("value")
    val value: Double? = null
): Parcelable