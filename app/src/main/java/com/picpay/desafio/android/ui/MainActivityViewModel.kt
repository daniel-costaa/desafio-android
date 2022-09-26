package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.network.Response
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response as RetrofitResponse

class MainActivityViewModel(private val picPayRepository: PicPayRepository) : ViewModel() {
    private val _users = MutableLiveData<Response<List<User>>>()
    val users: LiveData<Response<List<User>>> = _users

    fun getUsers() {
        viewModelScope.launch {
            picPayRepository.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: RetrofitResponse<List<User>>) {
                    response.body()?.let {
                        _users.postValue(Response.Success(it))
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    _users.postValue(Response.Error(0))
                }
            })
        }
    }
}
