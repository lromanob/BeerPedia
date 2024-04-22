package it.demo.lromanob.beerbox.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerItem(

	@field:SerializedName("first_brewed")
	val firstBrewed: String? = null,

	@field:SerializedName("attenuation_level")
	val attenuationLevel: Double? = null,

	@field:SerializedName("method")
	val method: Method? = null,

	@field:SerializedName("target_og")
	val targetOg: Double? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("boil_volume")
	val boilVolume: BoilVolume? = null,

	@field:SerializedName("ebc")
	val ebc: Double? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("target_fg")
	val targetFg: Int? = null,

	@field:SerializedName("srm")
	val srm: Double? = null,

	@field:SerializedName("volume")
	val volume: Volume? = null,

	@field:SerializedName("contributed_by")
	val contributedBy: String? = null,

	@field:SerializedName("abv")
	val abv: Double? = null,

	@field:SerializedName("food_pairing")
	val foodPairing: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("ph")
	val ph: Double? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("ingredients")
	val ingredients: Ingredients? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("ibu")
	val ibu: Double? = null,

	@field:SerializedName("brewers_tips")
	val brewersTips: String? = null
): Parcelable




