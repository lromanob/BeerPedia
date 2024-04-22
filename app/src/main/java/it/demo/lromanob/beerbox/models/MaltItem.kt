package it.demo.lromanob.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaltItem(

    @field:SerializedName("amount")
    val amount: Amount? = null,

    @field:SerializedName("name")
    val name: String? = null
): Parcelable