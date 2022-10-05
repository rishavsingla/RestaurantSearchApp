package com.example.restaurantsearchapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.restaurantsearchapp.models.MenusItem
import com.example.restaurantsearchapp.models.MenusModel
import com.example.restaurantsearchapp.models.RestaurantsItem
import com.example.restaurantsearchapp.models.RestaurantsModel
import com.google.gson.Gson

class MainActivityViewModel(private val app: Application) : AndroidViewModel(app) {

    private var restaurantsList = emptyList<RestaurantsItem>()
    private var menusList = emptyList<MenusItem>()
    private val searchedResultList = MutableLiveData<List<RestaurantsItem>>()
    fun getSearchedResultList(): LiveData<List<RestaurantsItem>> = searchedResultList

    fun readRestaurantsJson() {
        val restaurantJsonString = getJsonFromAssets("restaurants.json") ?: return
        val restaurantsModel = Gson().fromJson(restaurantJsonString, RestaurantsModel::class.java)
            ?: RestaurantsModel()
        restaurantsList = restaurantsModel.restaurants
    }

    fun readMenuJson() {
        val menuJsonString = getJsonFromAssets("menus.json") ?: return
        val menusModel = Gson().fromJson(menuJsonString, MenusModel::class.java)
            ?: MenusModel()
        menusList = menusModel.menus
    }


    private fun getJsonFromAssets(fileName: String): String? {
        return try {
            val inputStream = app.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (e: Exception) {
            null
        }
    }

    fun performSearch(searchedQuery: String) {

        if (restaurantsList.isEmpty() || searchedQuery.isEmpty()) {
            searchedResultList.postValue(emptyList())
            return
        }

        val restaurantList = mutableListOf<RestaurantsItem>()
        if (menusList.isNotEmpty()) {
            for (menusItem in menusList) {
                for (category in menusItem.categories) {
                    if (category.name.lowercase().contains(searchedQuery)) {
                        val restaurant = restaurantsList.find { it.id == menusItem.restaurantId }
                        if (restaurant != null) {
                            restaurantList.add(restaurant)
                        }
                        break;
                    }
                    val menuItemObj = category.menuItems.firstOrNull {
                        it.name.lowercase().contains(searchedQuery) || it.description.lowercase()
                            .contains(searchedQuery)
                    }
                    if (menuItemObj != null) {
                        val restaurant = restaurantsList.find { it.id == menusItem.restaurantId }
                        if (restaurant != null) {
                            restaurantList.add(restaurant)
                        }
                        break
                    }
                }
            }
        }

        val cuisineTypeList =
            restaurantsList.filter {
                it.cuisineType.lowercase().contains(searchedQuery)
            }

        val nameList = restaurantsList.filter {
            it.name.lowercase().contains(searchedQuery)
        }

        val unionResult = cuisineTypeList.union(nameList)
        searchedResultList.postValue(
            restaurantList.union(unionResult).toList()
        ) // return list based on order
    }
}