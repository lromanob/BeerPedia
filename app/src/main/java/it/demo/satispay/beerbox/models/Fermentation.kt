package it.demo.satispay.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fermentation(

    @field:SerializedName("temp")
    val temp: Temp? = null
): Parcelable