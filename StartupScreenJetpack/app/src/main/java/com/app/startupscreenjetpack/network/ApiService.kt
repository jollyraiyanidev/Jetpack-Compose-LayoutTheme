package com.app.startupscreenjetpack.network



import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {


    //@GET(API_SERVICE_MODULE + "GetCollectionCenterDetails")
    suspend fun userLogin(@Query("Name") name:String, @Query("Password") password: String): ResponseBody


}

