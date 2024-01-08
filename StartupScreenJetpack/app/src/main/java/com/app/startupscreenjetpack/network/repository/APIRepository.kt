package com.app.startupscreenjetpack.network.repository

import android.app.Activity
import com.app.startupscreenjetpack.R
import com.app.startupscreenjetpack.network.ApiService
import com.app.startupscreenjetpack.utils.Constant
import com.app.startupscreenjetpack.utils.hideProgressDialog
import com.app.startupscreenjetpack.utils.showToast
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiService: ApiService){

    suspend fun callApi(
        activity: Activity,
        isFrom:Int,
        id:String,
        requestBody: RequestBody?
    ): ResponseBody? {

        try {

            when (isFrom) {
                Constant.LOGIN -> {
                    val separate1 = id.split(" ")
                    return apiService.userLogin(separate1[0], separate1[1])
                }


            }

        } catch (e: Exception) {
            activity.hideProgressDialog()
            when (e) {
                is ConnectException, is UnknownHostException ->
                    handleException(
                    activity,
                    activity.getString(R.string.a_lbl_network_error),
                    activity.getString(R.string.a_net_message)
                )
                is IOException, is TimeoutException ->{
                    activity.showToast("")
                }else ->{
                    return null
                }


            }
        }
        return null

    }

    private fun handleException(
        activity: Activity, title: String, message: String) {
        try {
            activity.hideProgressDialog()
            /*if(!AppUtils.isDialogOpen) {
                CallDialog.errorDialog(
                    activity,
                    title,
                    message,
                    "","","",
                    null
                )
                AppUtils.isDialogOpen = true
            }else
            {
                //  AppUtils.isDialogOpen = false
            }
*/
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }





}