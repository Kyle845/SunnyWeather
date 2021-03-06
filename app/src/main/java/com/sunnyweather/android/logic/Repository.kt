package com.sunnyweather.android.logic

import androidx.lifecycle.liveData

import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {
    fun searchPlaces(query:String)= liveData(Dispatchers.IO) {
        val result=try {
            val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }

        }catch (e:Exception){
        Result.failure<List<Place>>(e)
        }
        emit(result as Result<List<Place>>)
    }

}