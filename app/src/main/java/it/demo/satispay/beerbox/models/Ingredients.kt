package it.demo.satispay.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredients(

    @field:SerializedName("hops")
    val hops: List<HopsItem?>? = null,

    @field:SerializedName("yeast")
    val yeast: String? = null,

    @field:SerializedName("malt")
    val malt: List<MaltItem?>? = null
): Parcelable