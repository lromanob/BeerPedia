package it.demo.lromanob.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Method(

    @field:SerializedName("mash_temp")
    val mashTemp: List<MashTempItem?>? = null,

    @field:SerializedName("fermentation")
    val fermentation: Fermentation? = null,

    @field:SerializedName("twist")
    val twist: String? = null
): Parcelable