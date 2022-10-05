package com.example.restaurantsearchapp.models

import com.google.gson.annotations.SerializedName

data class RestaurantsModel(

	@SerializedName("restaurants")
	val restaurants: List<RestaurantsItem> = emptyList()
)

data class RestaurantsItem(

	@SerializedName("photograph")
	val photograph: String? = null,

	@SerializedName("address")
	val address: String,

	@SerializedName("reviews")
	val reviews: List<ReviewsItem> = emptyList(),

	@SerializedName("operating_hours")
	val operatingHours: OperatingHours? = null,

	@SerializedName("name")
	val name: String,

	@SerializedName("id")
	val id: Int,

	@SerializedName("neighborhood")
	val neighborhood: String? = null,

	@SerializedName("cuisine_type")
	val cuisineType: String,

	@SerializedName("latlng")
	val latlng: Latlng? = null
)

data class Latlng(

	@SerializedName("lng")
	val lng: Double? = null,

	@SerializedName("lat")
	val lat: Double? = null
)

data class OperatingHours(

	@SerializedName("Monday")
	val monday: String? = null,

	@SerializedName("Thursday")
	val thursday: String? = null,

	@SerializedName("Friday")
	val friday: String? = null,

	@SerializedName("Sunday")
	val sunday: String? = null,

	@SerializedName("Wednesday")
	val wednesday: String? = null,

	@SerializedName("Tuesday")
	val tuesday: String? = null,

	@SerializedName("Saturday")
	val saturday: String? = null
)

data class ReviewsItem(

	@SerializedName("date")
	val date: String? = null,

	@SerializedName("comments")
	val comments: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("rating")
	val rating: Int? = null
)
