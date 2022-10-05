package com.example.restaurantsearchapp.models

import com.google.gson.annotations.SerializedName

data class MenusModel(

    @SerializedName("menus")
    val menus: List<MenusItem> = emptyList()
)

data class MenusItem(

    @SerializedName("categories")
    val categories: List<CategoriesItem> = emptyList(),

    @SerializedName("restaurantId")
    val restaurantId: Int
)


data class CategoriesItem(

    @SerializedName("menu-items")
    val menuItems: List<MenuItemsItem> = emptyList(),

    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: String? = null
)

data class MenuItemsItem(

    @SerializedName("images")
    val images: List<Any?>? = null,

    @SerializedName("price")
    val price: String? = null,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("id")
    val id: String? = null
)


