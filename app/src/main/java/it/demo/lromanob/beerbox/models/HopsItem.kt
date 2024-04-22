package it.demo.lromanob.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HopsItem(

    @field:SerializedName("add")
    val add: String? = null,

    @field:SerializedName("amount")
    val amount: Amount? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("attribute")
    val attribute: String? = null
): Parcelable