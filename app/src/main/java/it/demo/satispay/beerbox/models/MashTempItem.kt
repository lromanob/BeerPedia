package it.demo.satispay.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MashTempItem(

    @field:SerializedName("duration")
    val duration: Int? = null,

    @field:SerializedName("temp")
    val temp: Temp? = null
): Parcelable