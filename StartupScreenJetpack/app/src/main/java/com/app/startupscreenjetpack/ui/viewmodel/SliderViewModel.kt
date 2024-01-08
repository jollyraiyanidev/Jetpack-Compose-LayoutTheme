package com.app.startupscreenjetpack.ui.viewmodel

import android.app.Activity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.startupscreenjetpack.model.sliding.SlidingResponse
import com.app.startupscreenjetpack.network.ApiService
import com.app.startupscreenjetpack.network.model.State
import com.app.startupscreenjetpack.utils.AppUtils
import com.app.startupscreenjetpack.utils.Constant
import com.app.startupscreenjetpack.utils.hideProgressDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel  @Inject constructor(private val apiService: ApiService): ViewModel() {
     val sliders = MutableLiveData<State<SlidingResponse>>()
    //private val users : State<SlidingResponse> = _users

    fun fetchUsers(activity:Activity) {
        sliders.value = State.loading()

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                AppUtils.getSlider(activity,SlidingResponse::class.java) as SlidingResponse
               //                UserRepository().callApi(activity, Constant.LOGIN,body,null)
            }
            if(response.isNotEmpty()){
                sliders.value = State.success(response)}
            else{
                sliders.value = State.error("exception")

            }
        }
    }
}